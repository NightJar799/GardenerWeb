package com.example.gardener.DTO;

import com.example.gardener.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    public String email;
    public String password;
    public Role role;
}
