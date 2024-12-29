package com.example.plantanalysisapp.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AgricultureController {

    private static final Map<String, List<WeatherData>> weatherDatabase = new HashMap<>();

    static {
        weatherDatabase.put("region1", Arrays.asList(
                new WeatherData("2024-12-01", 25.0, 60.0),
                new WeatherData("2024-12-02", 27.0, 55.0)
        ));
    }

    @GetMapping("/getWeatherData")
    public List<WeatherData> getWeatherData(@RequestParam String region) {
        return weatherDatabase.getOrDefault(region, Collections.emptyList());
    }

    @GetMapping("/getPlantProductivity")
    public Map<String, Double> getPlantProductivity(@RequestParam double humidity, @RequestParam double temperature) {
        double productivity = (humidity / 100) * temperature;
        return Map.of("productivity", productivity);
    }
}

class WeatherData {
    private String date;
    private double temperature;
    private double humidity;

    public WeatherData(String date, double temperature, double humidity) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getDate() { return date; }
    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
}