package com.crudfunctionality.crud_functionality.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "merchants")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchantId;
    private String fullName;
    private String phoneNumber;
    private String idNumber;
    private String gender;
    private String kraPin;
    private String email;

    private String responseCode;
    private String responseMessage;
    private String errorMessage;
    @Setter
    private String accessToken;
    @Setter
    private String refreshToken;

}
