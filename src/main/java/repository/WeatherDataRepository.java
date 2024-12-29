package com.example.plantanalysisapp.repository;

import com.example.plantanalyzer.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    // Özel sorgular gerekiyorsa burada tanımlanabilir
}
