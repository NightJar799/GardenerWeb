package com.example.gardener.Entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "grd")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "password", unique = true, nullable = false, length = 100)
    private String password;

    @Column(name = "nickname", unique = false, nullable = false, length = 30)
    private String nickName;

    @Column(name = "phone", unique = true, nullable = false, length = 15)
    private String phone;

    public User() {}

    public User(String login, String password, String nickName, String phone) {
        this.login = login;
        this.password = password;
        this.nickName = nickName;
        this.phone = phone;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}