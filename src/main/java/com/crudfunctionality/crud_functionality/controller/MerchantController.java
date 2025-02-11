package com.crudfunctionality.crud_functionality.controller;

import com.crudfunctionality.crud_functionality.entity.Merchant;
import com.crudfunctionality.crud_functionality.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMerchant(@RequestBody Merchant merchant) {
        return merchantService.registerMerchant(merchant);
    }
}
