package com.example.gardener.DTO;


import com.example.gardener.utils.validation.UniqueEmail;
import com.example.gardener.utils.validation.UniquePhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterDTO {
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @UniqueEmail(message = "Email уже используется")
    private String email;
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
    @NotBlank(message = "Подтверждение пароля не может быть пустым")
    private String passwordCheck;
    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Некорректный формат телефона")
    @UniquePhone(message = "Телефон уже используется")
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
