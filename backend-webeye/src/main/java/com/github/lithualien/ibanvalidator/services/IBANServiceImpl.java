package com.github.lithualien.ibanvalidator.services;

import com.github.lithualien.ibanvalidator.converters.BankInfoToVO;
import com.github.lithualien.ibanvalidator.exceptions.InvalidIbanException;
import com.github.lithualien.ibanvalidator.models.BankInfo;
import com.github.lithualien.ibanvalidator.repositories.BankInfoRepository;
import com.github.lithualien.ibanvalidator.validator.IBANValidator;
import com.github.lithualien.ibanvalidator.vo.AccountNumberVO;
import com.github.lithualien.ibanvalidator.vo.BankInfoVO;
import com.github.lithualien.ibanvalidator.vo.DecomposedIbanVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBANServiceImpl implements IBANService {

    private final IBANValidator IBANValidator;
    private final String SEB_NATIONAL_ID = "70440";
    private final String COUNTRY = "Lithuania";
    private final BankInfoRepository bankInfoRepository;
    private final BankInfoToVO bankInfoToVO;
    boolean isValid = false;

    public IBANServiceImpl(IBANValidator IBANValidator, BankInfoRepository bankInfoRepository, BankInfoToVO bankInfoToVO) {
        this.IBANValidator = IBANValidator;
        this.bankInfoRepository = bankInfoRepository;
        this.bankInfoToVO = bankInfoToVO;
    }

    @Override
    public BankInfoVO validateIBAN(AccountNumberVO accountNumberVO) {
        String accountNumber = accountNumberVO.getNumber().replaceAll("\\s", "");
        try {
            checkIbanSum(accountNumber);
            DecomposedIbanVO decomposedIbanVO = getDecomposedIban(accountNumber);
            isValid = IBANValidator.validator(decomposedIbanVO);

            int nationalId = Integer.parseInt(getBankCode(accountNumber));
            boolean isSeb = isSeb(accountNumber);
            BankInfo bankInfo = getBankInfo(nationalId);

            return bankInfoToVO.convert(bankInfo, accountNumber, isValid, isSeb);
        } catch (InvalidIbanException ex) {
            setInvalid();
            return bankInfoToVO.convert(new BankInfo(), accountNumber, false, false);
        }
    }

    @Override
    public List<BankInfoVO> validateIBANList(List<AccountNumberVO> accountNumberVOList) {
        List<BankInfoVO> bankInfoVOList = new ArrayList<>();

        for(AccountNumberVO accountNumberVO : accountNumberVOList) {
            if (accountNumberVO != null && !accountNumberVO.getNumber().isEmpty()) {
                String accountNumber = accountNumberVO.getNumber().replaceAll("\\s", "");

                try {
                    checkIbanSum(accountNumber);
                    DecomposedIbanVO decomposedIbanVO = getDecomposedIban(accountNumber);

                    isValid = IBANValidator.validator(decomposedIbanVO);
                    int nationalId = Integer.parseInt(getBankCode(accountNumber));

                    BankInfo bankInfo = getBankInfo(nationalId);
                    boolean isSeb = isSeb(accountNumber);

                    BankInfoVO bankInfoVO = bankInfoToVO.convert(bankInfo, accountNumber, isValid, isSeb);
                    bankInfoVOList.add(bankInfoVO);
                } catch (InvalidIbanException ex) {
                    setInvalid();
                    BankInfoVO bankInfoVO = bankInfoToVO.convert(new BankInfo(), accountNumber, false, false);
                    bankInfoVOList.add(bankInfoVO);
                }
            }
        }
        return bankInfoVOList;
    }

    private DecomposedIbanVO getDecomposedIban(String IBANNumber) {

        return new DecomposedIbanVO(
                getCountryCode(IBANNumber),
                getControlDigits(IBANNumber),
                getBankCode(IBANNumber),
                getAccountNumber(IBANNumber)
        );

    }

    private String getCountryCode(String accountNumber) {
        return accountNumber.substring(0, 2);
    }

    private String getControlDigits(String accountNumber) {
        return accountNumber.substring(2, 4);
    }

    private String getBankCode(String accountNumber) {
        return accountNumber.substring(4, 9);
    }

    private String getAccountNumber(String accountNumber) {
        return accountNumber.substring(9);
    }

    private boolean isSeb(String accountNumber) {
        return getBankCode(accountNumber).equals(SEB_NATIONAL_ID);
    }

    private void setInvalid() {
        isValid = false;
    }

    private BankInfo getBankInfo(int nationalId) {
        return bankInfoRepository
                .findByNationalIdAndCountry(nationalId, COUNTRY)
                .orElseGet(() -> {
                    setInvalid();
                    return new BankInfo();
                });
    }

    private void checkIbanSum(String accountNumber) {
        if(accountNumber.length() != 20) {
            throw new InvalidIbanException("IBAN length is not equal to 20.");
        }
    }
}
