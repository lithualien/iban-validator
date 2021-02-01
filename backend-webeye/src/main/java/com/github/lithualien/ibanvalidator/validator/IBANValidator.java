package com.github.lithualien.ibanvalidator.validator;

import com.github.lithualien.ibanvalidator.exceptions.InvalidIbanException;
import com.github.lithualien.ibanvalidator.vo.DecomposedIbanVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Log4j2
@Component
public class IBANValidator {

    public Boolean validator(DecomposedIbanVO decomposedIbanVO) {

        BigInteger numericIBANNumber = rearrangeIban(decomposedIbanVO);

        if(!numericIBANNumber.mod(new BigInteger("97")).equals(new BigInteger("1"))) {
            throw new InvalidIbanException("Invalid IBAN number.");
        }

        return true;
    }

    private BigInteger rearrangeIban(DecomposedIbanVO decomposedIBANVO) {

        String countryCodeWithControlDigits
                = convertCountryCodeToISO7064(decomposedIBANVO.getCountryCode()) + decomposedIBANVO.getControlDigits();

        String convertedIBAN
                = decomposedIBANVO.getBankCode() + decomposedIBANVO.getAccountNumber() + countryCodeWithControlDigits;

        return new BigInteger(convertedIBAN);
    }

    private String convertCountryCodeToISO7064(String countryCode) {

        if(!countryCode.equalsIgnoreCase("LT")) {
            throw new InvalidIbanException("IBAN country code is not equals to LT.");
        }

        char[] countryCodeArray = countryCode.toCharArray();

        int firstDigit;
        int secondDigit;

        if(Character.isLowerCase(countryCodeArray[0])) {
            firstDigit = countryCodeArray[0] - 87;
        } else {
            firstDigit = countryCodeArray[0] - 55;
        }

        if(Character.isLowerCase(countryCodeArray[1])) {
            secondDigit = countryCodeArray[1] - 87;
        } else {
            secondDigit = countryCodeArray[1] - 55;
        }

        return firstDigit + "" + secondDigit;
    }

}
