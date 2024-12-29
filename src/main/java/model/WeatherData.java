package com.example.plantanalyzer.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data // Getter, Setter, ToString gibi metotları otomatik oluşturur
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID otomatik artacak
    private Long id;

    private String region; // Bölge adı
    private String date; // Tarih (ör. 2024-01-01)
    private double temperature; // Sıcaklık (Celsius)
    private double humidity; // Nem oranı (%)
}