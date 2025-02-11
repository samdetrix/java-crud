package com.crudfunctionality.crud_functionality.service;

import com.crudfunctionality.crud_functionality.entity.Merchant;
import org.springframework.http.ResponseEntity;


//public interface MerchantService {
//    Merchant registerMerchant(Merchant merchant);
//}


public interface MerchantService {
    ResponseEntity<?> registerMerchant(Merchant merchant);
}
