package com.github.lithualien.ibanvalidator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecomposedIbanVO {

    private String countryCode;
    private String controlDigits;
    private String bankCode;
    private String accountNumber;

}
