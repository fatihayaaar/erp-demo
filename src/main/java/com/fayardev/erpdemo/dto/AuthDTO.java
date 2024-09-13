package com.fayardev.erpdemo.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthDTO {

    private String username;
    private String password;
}
