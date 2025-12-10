package com.example.gardener.DTO;

import com.example.gardener.utils.validation.UniqueEmail;
import com.example.gardener.utils.validation.UniquePhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @UniqueEmail
    private String email;
    private String password;
    private String passwordCheck;
    @UniquePhone
    private String phone;
    private String nickname;


}
