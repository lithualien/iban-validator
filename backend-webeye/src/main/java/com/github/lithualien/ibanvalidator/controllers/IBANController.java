package com.github.lithualien.ibanvalidator.controllers;

import com.github.lithualien.ibanvalidator.services.IBANService;
import com.github.lithualien.ibanvalidator.vo.AccountNumberVO;
import com.github.lithualien.ibanvalidator.vo.BankInfoVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/iban/validate")
public class IBANController {

    private final IBANService ibanService;

    public IBANController(IBANService ibanService) {
        this.ibanService = ibanService;
    }

    @PostMapping
    public ResponseEntity<BankInfoVO> validateIban(@RequestBody AccountNumberVO number) {
        return new ResponseEntity<>(ibanService.validateIBAN(number), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<List<BankInfoVO>> validateIbanList(@RequestBody List<AccountNumberVO> numbers) {
        return new ResponseEntity<>(ibanService.validateIBANList(numbers), HttpStatus.OK);
    }

}
