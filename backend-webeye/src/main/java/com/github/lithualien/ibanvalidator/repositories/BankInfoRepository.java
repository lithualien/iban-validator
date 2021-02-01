package com.github.lithualien.ibanvalidator.repositories;

import com.github.lithualien.ibanvalidator.models.BankInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankInfoRepository extends CrudRepository<BankInfo, Long> {

    Optional<BankInfo> findByNationalIdAndCountry(Integer nationalId, String Country);
}
