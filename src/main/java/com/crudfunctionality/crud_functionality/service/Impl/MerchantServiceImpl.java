package com.crudfunctionality.crud_functionality.service.Impl;

import com.crudfunctionality.crud_functionality.entity.Merchant;
import com.crudfunctionality.crud_functionality.repository.MerchantRepository;
import com.crudfunctionality.crud_functionality.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
    private final RestTemplate restTemplate;
    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(RestTemplate restTemplate, MerchantRepository merchantRepository) {
        this.restTemplate = restTemplate;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public ResponseEntity<?> registerMerchant(Merchant merchant) {
        String laravelUrl = "http://localhost:8000/api/register";

        // Prepare request payload for Laravel
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("merchant_id", merchant.getMerchantId());
        requestPayload.put("name", merchant.getFullName());
        requestPayload.put("full_name", merchant.getFullName());
        requestPayload.put("phone_number", merchant.getPhoneNumber());
        requestPayload.put("id_number", merchant.getIdNumber());
        requestPayload.put("gender", merchant.getGender().toLowerCase()); // Laravel expects lowercase
        requestPayload.put("kra_pin", merchant.getKraPin());
        requestPayload.put("email", merchant.getEmail());
        requestPayload.put("password", "password123");
        requestPayload.put("password_confirmation", "password123");

        try {
            // Send request to Laravel API
            ResponseEntity<String> laravelResponse = restTemplate.postForEntity(laravelUrl, requestPayload, String.class);

            if (laravelResponse.getStatusCode() == HttpStatus.CREATED) {
                logger.info("✅ Laravel registration successful. Saving merchant in Spring Boot DB...");

                // Save merchant in Spring Boot DB
                Merchant savedMerchant = merchantRepository.save(merchant);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of("message", "Merchant registered successfully!", "merchant", savedMerchant));
            } else {
                logger.error("⚠️ Unexpected response from Laravel: {}", laravelResponse.getBody());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Unexpected response from Laravel", "details", laravelResponse.getBody()));
            }

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
                logger.error(" Laravel Validation Error: {}", e.getResponseBodyAsString());
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("error", "Laravel validation failed", "details", e.getResponseBodyAsString()));
            }
            logger.error(" Error connecting to Laravel: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to connect to Laravel", "details", e.getMessage()));
        }
    }
}
