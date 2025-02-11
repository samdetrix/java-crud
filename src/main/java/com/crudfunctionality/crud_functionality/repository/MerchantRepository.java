package com.crudfunctionality.crud_functionality.repository;

import com.crudfunctionality.crud_functionality.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdNumber(String idNumber);
    boolean existsByKraPin(String kraPin);
}
