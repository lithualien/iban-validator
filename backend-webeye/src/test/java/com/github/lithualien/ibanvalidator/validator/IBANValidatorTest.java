package com.github.lithualien.ibanvalidator.validator;

import com.github.lithualien.ibanvalidator.exceptions.InvalidIbanException;
import com.github.lithualien.ibanvalidator.vo.DecomposedIbanVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IBANValidatorTest {

    private DecomposedIbanVO decomposedIbanVOIncorrect1;
    private DecomposedIbanVO decomposedIbanVOIncorrect2;
    private DecomposedIbanVO decomposedIbanVOIncorrect3;
    private DecomposedIbanVO decomposedIbanVOIncorrect4;
    private DecomposedIbanVO decomposedIbanVOCorrect1;
    private DecomposedIbanVO decomposedIbanVOCorrect2;
    private IBANValidator ibanValidator;

    @BeforeEach
    void setUp() {
        decomposedIbanVOIncorrect1
                = new DecomposedIbanVO("LT", "22", "70440", "77788877777");

        decomposedIbanVOIncorrect2
                = new DecomposedIbanVO("AA", "05", "12454", "45454552117989");

        decomposedIbanVOIncorrect3
                = new DecomposedIbanVO("CC", "05", "12454", "45454552117989");

        decomposedIbanVOIncorrect4
                = new DecomposedIbanVO("CC", "51", "70440", "77788877777");

        decomposedIbanVOCorrect1
                = new DecomposedIbanVO("LT", "51", "70440", "77788877777");

        decomposedIbanVOCorrect2
                = new DecomposedIbanVO("LT", "64", "70440", "01231465456");

        ibanValidator = new IBANValidator();
    }

    @AfterEach
    void tearDown() {
        decomposedIbanVOIncorrect1 = null;
        decomposedIbanVOCorrect1 = null;
        decomposedIbanVOCorrect2 = null;
        ibanValidator = null;
    }

    @Test
    void validateCorrectIBAN() {
        assertTrue(ibanValidator.validator(decomposedIbanVOCorrect1));
        assertTrue(ibanValidator.validator(decomposedIbanVOCorrect2));
    }

    @Test
    void validateIncorrectIBAN1() {
        Exception decomposedIBANIncorrect1Exception = assertThrows(InvalidIbanException.class, () -> {
            ibanValidator.validator(decomposedIbanVOIncorrect1);
        });

        assertTrue(decomposedIBANIncorrect1Exception.getMessage().contains("Invalid IBAN number."));

    }

    @Test
    void validateIncorrectIBAN2() {
        Exception decomposedIBANIncorrect2Exception = assertThrows(InvalidIbanException.class, () -> {
            ibanValidator.validator(decomposedIbanVOIncorrect2);
        });

        System.out.println(decomposedIBANIncorrect2Exception.getMessage());

        assertTrue(decomposedIBANIncorrect2Exception.getMessage().contains("IBAN country code is not equals to LT."));
    }

    @Test
    void validateIncorrectIBAN3() {
        Exception decomposedIBANIncorrect3Exception = assertThrows(InvalidIbanException.class, () -> {
            ibanValidator.validator(decomposedIbanVOIncorrect3);
        });

        assertTrue(decomposedIBANIncorrect3Exception.getMessage().contains("IBAN country code is not equals to LT."));
    }

    @Test
    void validateIncorrectIBAN4() {
        Exception decomposedIBANIncorrect4Exception = assertThrows(InvalidIbanException.class, () -> {
            ibanValidator.validator(decomposedIbanVOIncorrect4);
        });
        assertTrue(decomposedIBANIncorrect4Exception.getMessage().contains("IBAN country code is not equals to LT."));
    }

}