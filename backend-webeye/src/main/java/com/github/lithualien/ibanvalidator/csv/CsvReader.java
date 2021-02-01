package com.github.lithualien.ibanvalidator.csv;

import com.github.lithualien.ibanvalidator.models.BankInfo;
import com.github.lithualien.ibanvalidator.repositories.BankInfoRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Deprecated
public class CsvReader {

    private final BankInfoRepository bankInfoRepository;

    public CsvReader(BankInfoRepository bankInfoRepository) {
        this.bankInfoRepository = bankInfoRepository;
    }

    public void readCsvFile() {
        try {
            List<BankInfo> bankInfoList = new CsvToBeanBuilder<BankInfo>(
                    new FileReader(getCsvFile()))
                    .withType(BankInfo.class)
                    .withSkipLines(1)
                    .build()
                    .parse();
            bankInfoRepository.saveAll(bankInfoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getCsvFile() {
        try {
            return ResourceUtils.getFile("classpath:static/LT-20200929-en.csv").getAbsolutePath();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
