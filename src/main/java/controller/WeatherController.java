package com.example.plantanalysisapp.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.plantanalyzer.model.WeatherData;
import com.example.plantanalysisapp.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") // Frontend portu
@RestController

@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Tüm hava durumu verilerini getirir
    @GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherService.getAllData();
    }

    // Yeni bir hava durumu kaydı ekler
    @PostMapping
    public WeatherData addWeatherData(@RequestBody WeatherData weatherData) {
        return weatherService.saveWeatherData(weatherData);
    }

    // Belirli bir kaydı siler
    @DeleteMapping("/{id}")
    public String deleteWeatherData(@PathVariable Long id) {
        weatherService.deleteWeatherData(id);
        return "Veri silindi!";
    }

    // Yeni bir hava durumu verisini hesaplayan endpoint
    @GetMapping("/calculate")
    public String calculateWeatherData() {
        // Bu kısıma hesaplama mantığı eklenebilir
        return "Hesaplama tamamlandı!";
    }
}