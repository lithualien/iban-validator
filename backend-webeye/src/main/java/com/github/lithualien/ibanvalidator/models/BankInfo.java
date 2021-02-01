package com.github.lithualien.ibanvalidator.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "iban_info", schema = "bank_info")
public class BankInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 0)
    private Integer nationalId;

    @CsvBindByPosition(position = 1)
    @Column(name = "bic_code")
    private String BICCode;

    @CsvBindByPosition(position = 2)
    private String institutionName;

    @CsvBindByPosition(position = 3)
    private String branchName;

    @CsvBindByPosition(position = 4)
    private String city;

    @CsvBindByPosition(position = 5)
    private String address;

    @CsvBindByPosition(position = 6)
    private String zipCode;

    @CsvBindByPosition(position = 7)
    private String location;

    @CsvBindByPosition(position = 8)
    private String country;

}
