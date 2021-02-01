package com.github.lithualien.ibanvalidator.converters;

import com.github.lithualien.ibanvalidator.models.BankInfo;
import com.github.lithualien.ibanvalidator.vo.BankInfoVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankInfoToVOTest {

    private BankInfo bankInfo;
    private BankInfoToVO bankInfoToVO;
    private final boolean isValid = true;
    private final boolean isSeb = true;
    private final String accountNumber = "LT647044001231465456";

    @BeforeEach
    void setUp() {
        bankInfo
                = new BankInfo(1L, 70440, "CBVILT2X", "AB SEB bankas",
                "", "Vilnius", "Gedimino Ave. 12", "1103", "", "Lithuania");
        bankInfoToVO = new BankInfoToVO();
    }

    @AfterEach
    void tearDown() {
        bankInfo = null;
        bankInfoToVO = null;
    }

    @Test
    void convert() {
        BankInfoVO bankInfoVO = bankInfoToVO.convert(bankInfo, accountNumber, isValid, isSeb);

        assertEquals(bankInfoVO.getAccountNumber(), accountNumber);
        assertEquals(bankInfoVO.getAddress(), bankInfo.getAddress());
        assertEquals(bankInfoVO.getBICCode(), bankInfo.getBICCode());
        assertEquals(bankInfoVO.getCity(), bankInfo.getCity());
        assertEquals(bankInfoVO.getBranchName(), bankInfo.getBranchName());
        assertEquals(bankInfoVO.getCountry(), bankInfo.getCountry());
        assertEquals(bankInfoVO.getInstitutionName(), bankInfo.getInstitutionName());
        assertEquals(bankInfoVO.getLocation(), bankInfo.getLocation());
        assertEquals(bankInfoVO.getNationalId(), bankInfo.getNationalId());
        assertEquals(bankInfoVO.getZipCode(), bankInfo.getZipCode());
        assertEquals(bankInfoVO.getIsSeb(), isSeb);
        assertEquals(bankInfoVO.getIsValid(), isValid);

    }
}