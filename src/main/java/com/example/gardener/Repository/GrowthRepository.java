package com.example.gardener.Repository;

import com.example.gardener.Entities.Growth;
import com.example.gardener.Entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Integer> {
}
