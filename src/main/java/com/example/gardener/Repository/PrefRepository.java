package com.example.gardener.Repository;

import com.example.gardener.Entities.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefRepository extends JpaRepository<Preferences, Integer> {
}
