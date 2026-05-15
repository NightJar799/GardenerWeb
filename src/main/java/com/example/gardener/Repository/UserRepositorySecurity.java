package com.example.gardener.Repository;

import com.example.gardener.Entities.User;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepositorySecurity extends CrudRepository<User, Integer> {

    @NativeQuery("SELECT id, login, phone, nickname, password, role_of_user FROM grd.users WHERE users.nickname = :username")
    User findByUsername(@Param("username") String username);
}
