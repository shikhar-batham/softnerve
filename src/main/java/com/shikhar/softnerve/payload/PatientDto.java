package com.shikhar.softnerve.payload;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private Integer Id;
    private String name;
    private BigInteger contactDetails;
    private String address;
    private Integer pinCode;
}
