const express = require('express');
const app = express();
const port = 3000;

// JSON verilerini dönen basit bir API
app.get('/api/data', (req, res) => {
    res.json({ message: "API başarılı bir şekilde çalışıyor!" });
});

// Sunucuyu başlat
app.listen(port, () => {
    console.log(`Sunucu http://localhost:${3000} adresinde çalışıyor.`);

    const express = require("express");
    const app = express();
    const port = 3000;

// JSON verisi döndüren örnek endpoint
    app.get("/api/data", (req, res) => {
        res.json({
            success: true,
            message: "API başarıyla çalışıyor!",
            data: [
                { id: 1, name: "Ali", age: 25 },
                { id: 2, name: "Ayşe", age: 30 },
                { id: 3, name: "Mehmet", age: 28 }
            ]
        });
    });

// Root endpoint
    app.get("/", (req, res) => {
        res.send("Merhaba! Bu, basit bir API uygulamasıdır.");
    });

// Sunucuyu başlat
    app.listen(port, () => {
        console.log(`API http://localhost:${port} adresinde çalışıyor.`);
    });

});