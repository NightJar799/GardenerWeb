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
    value = "SELECT id, login, phone, nickname, password FROM grd.users WHERE users.login = :login")
    User findUserIdByLogin(@Param("login") String login);

}
