package com.example.gardener.Repository;

import com.example.gardener.Entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(nativeQuery = true,
    value = "SELECT id, login, phone, nickname, password, role_of_user FROM grd.users WHERE users.login = :login")
    User findUserIdByLogin(@Param("login") String login);

    @Query(nativeQuery = true,
            value = "SELECT id, login, phone, nickname, password, role_of_user FROM grd.users WHERE users.phone = :phone")
    Optional<User> findUserByPhone(@Param("phone") String phone);

    @Query(nativeQuery = true,
            value = "SELECT id, login, phone, nickname, password, role_of_user FROM grd.users WHERE users.login = :login")
    Optional<User> findUserByEmail(@Param("login") String login);

    @Query(nativeQuery = true,
            value = "SELECT id, login, phone, nickname, password, role_of_user FROM grd.users WHERE users.nickname = :name")
    User findUserByName(@Param("name") String name);
}
