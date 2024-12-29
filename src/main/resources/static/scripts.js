document.getElementById("analysis-form").addEventListener("submit", async (event) => {
    event.preventDefault(); // Sayfa yenilenmesini engeller

    // Form verilerini al
    const region = document.getElementById("region").value;

    // API anahtarı ve URL
    const apiKey = "500f1ec828a948e3aa9194629241812"; // WeatherAPI API anahtarını kullanıyoruz
    const url = `https://api.weatherapi.com/v1/current.json?key=${apiKey}&q=${region}`;

    try {
        // API isteği gönder
        const response = await fetch(url);

        // API isteği başarısızsa hata fırlat
        if (!response.ok) {
            throw new Error("Hava durumu API isteği başarısız oldu.");
        }

        // JSON verisini al
        const responseData = await response.json();

        console.log(responseData);

        // Sonuçları sayfada görüntüle
        displayResults(responseData);

        // Backend'e veri gönderme
        await sendWeatherDataToBackend(responseData);

    } catch (error) {
        console.error("Hata:", error.message);
        alert("Bir hata oluştu. Lütfen tekrar deneyin...\n" + error.message);
    }
});

// API yanıtını sayfada görüntüleme fonksiyonu
function displayResults(data) {
    console.log(data);

    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = `
        <p><strong>Bölge:</strong> ${data.location.name}, ${data.location.country}</p>
        <p><strong>Hava Durumu:</strong> ${data.current.condition.text}</p>
        <p><strong>Sıcaklık:</strong> ${data.current.temp_c}&#8451;</p>
        <p><strong>Nem:</strong> ${data.current.humidity}%</p>
        <p><strong>Rüzgar Hızı:</strong> ${data.current.wind_kph} km/s</p>
    `;
    resultsDiv.style.display = "block";
}

// Backend'e hava durumu verilerini gönderme fonksiyonu
async function sendWeatherDataToBackend(weatherData) {
    const backendUrl = "http://localhost:8080/api/weather"; // Backend URL'sini doğru ayarlayın

    const dataToSend = {
        region: `${weatherData.location.name}, ${weatherData.location.country}`,
        temperature: weatherData.current.temp_c,
        humidity: weatherData.current.humidity,
        condition: weatherData.current.condition.text,
        windSpeed: weatherData.current.wind_kph
    };

    try {
        const response = await fetch(backendUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dataToSend)
        });

        if (!response.ok) {
            throw new Error("Backend'e veri gönderilemedi.");
        }

        const responseData = await response.json();
        console.log("Backend'den yanıt:", responseData);
        alert("Veri başarıyla backend'e gönderildi!");
    } catch (error) {
        console.error("Backend Hatası:", error.message);
        alert("Backend'e veri gönderilirken bir hata oluştu.");
    }
}
