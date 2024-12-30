package com.example.plantanalysisapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

// RestController annotation, bu sınıfın bir Spring RESTful web servisi olduğunu belirtir.
@RestController
@RequestMapping("/api") // Tüm istekler "/api" alt yolundan başlayacak.
public class AgricultureController {

    // Simüle edilmiş bir veri tabanı, "region" (bölge) adlarını ve ilgili hava durumu verilerini tutar.
    private static final Map<String, List<WeatherData>> weatherDatabase = new HashMap<>();

    // Statik blok: weatherDatabase'in başlangıç verilerini oluşturur.
    static {
        weatherDatabase.put("region1", Arrays.asList(
                new WeatherData("2024-12-01", 25.0, 60.0), // İlk veri: tarih, sıcaklık ve nem
                new WeatherData("2024-12-02", 27.0, 55.0)  // İkinci veri: tarih, sıcaklık ve nem
        ));
    }

    // Bu endpoint, belirli bir bölgenin hava durumu verilerini döndürür.
    @GetMapping("/getWeatherData")
    public List<WeatherData> getWeatherData(@RequestParam String region) {
        // Eğer bölge bulunamazsa, boş bir liste döndürür.
        return weatherDatabase.getOrDefault(region, Collections.emptyList());
    }

    // Bu endpoint, verilen nem ve sıcaklık değerlerine göre bitki verimliliği hesaplar.
    @GetMapping("/getPlantProductivity")
    public Map<String, Double> getPlantProductivity(@RequestParam double humidity, @RequestParam double temperature) {
        // Verimlilik hesaplama formülü: (nem oranı / 100) * sıcaklık
        double productivity = (humidity / 100) * temperature;
        return Map.of("productivity", productivity); // Hesaplanan verimliliği bir map olarak döndürür.
    }
}

// Hava durumu verilerini temsil eden sınıf.
class WeatherData {
    private String date; // Tarih bilgisi
    private double temperature; // Sıcaklık bilgisi
    private double humidity; // Nem oranı bilgisi

    // Constructor: WeatherData nesnesini oluşturur.
    public WeatherData(String date, double temperature, double humidity) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Getter: Tarih bilgisini döndürür.
    public String getDate() { return date; }

    // Getter: Sıcaklık bilgisini döndürür.
    public double getTemperature() { return temperature; }

    // Getter: Nem oranını döndürür.
    public double getHumidity() { return humidity; }
}
