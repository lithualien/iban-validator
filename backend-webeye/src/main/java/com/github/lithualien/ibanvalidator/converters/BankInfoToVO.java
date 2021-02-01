package com.github.lithualien.ibanvalidator.converters;

import com.github.lithualien.ibanvalidator.models.BankInfo;
import com.github.lithualien.ibanvalidator.vo.BankInfoVO;
import org.springframework.stereotype.Component;

@Component
public class BankInfoToVO {

    public BankInfoVO convert(BankInfo bankInfo, String accountNumber, Boolean isValid, Boolean isSeb) {
        BankInfoVO bankInfoVO = new BankInfoVO();

        bankInfoVO.setAddress(bankInfo.getAddress());
        bankInfoVO.setBICCode(bankInfo.getBICCode());
        bankInfoVO.setBranchName(bankInfo.getBranchName());
        bankInfoVO.setCity(bankInfo.getCity());
        bankInfoVO.setCountry(bankInfo.getCountry());
        bankInfoVO.setLocation(bankInfo.getLocation());
        bankInfoVO.setInstitutionName(bankInfo.getInstitutionName());
        bankInfoVO.setNationalId(bankInfo.getNationalId());
        bankInfoVO.setZipCode(bankInfo.getZipCode());
        bankInfoVO.setAccountNumber(accountNumber);
        bankInfoVO.setIsValid(isValid);
        bankInfoVO.setIsSeb(isSeb);

        return bankInfoVO;
    }

}
