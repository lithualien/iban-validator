package com.github.lithualien.ibanvalidator.services;

import com.github.lithualien.ibanvalidator.vo.AccountNumberVO;
import com.github.lithualien.ibanvalidator.vo.BankInfoVO;

import java.util.List;

public interface IBANService {

    BankInfoVO validateIBAN(AccountNumberVO accountNumberVO);

    List<BankInfoVO> validateIBANList(List<AccountNumberVO> accountNumberVOList);

}
