package com.example.gardener.DTO;

import com.example.gardener.utils.validation.UniqueEmail;
import com.example.gardener.utils.validation.UniquePhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegisterDTO {
    @UniqueEmail
    private String email;
    private String password;
    private String passwordCheck;
    @UniquePhone
    private String phone;
    private String nickname;

    public RegisterDTO() {}

    public RegisterDTO(String email, String password, String passwordCheck, String phone, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.phone = phone;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
