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

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}