package com.example.gardener.Repository;

import com.example.gardener.Entities.Favorite;
import com.example.gardener.Entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM grd.favorite WHERE favorite.id_plant = :idPlant")
    Optional<User> PopularityOfSpecificPlant(@Param("idPlant") String login);
}
