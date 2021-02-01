package com.github.lithualien.ibanvalidator.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankInfoVO implements Serializable {

    private Integer nationalId;
    private String BICCode;
    private String institutionName;
    private String branchName;
    private String city;
    private String address;
    private String zipCode;
    private String location;
    private String country;
    private String accountNumber;
    private Boolean isValid;
    private Boolean isSeb;

}
