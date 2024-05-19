package com.hackaton.bagginscoffee.codes;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostJSON {

    public static String getPrediction(int temp, String pressure, String humidity) throws Exception {
        // URL, куда отправляется запрос
        String url = "http://localhost:5000/predict";

        // Создание объекта URL и открытие соединения
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Установка метода запроса и заголовков
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        // JSON данные для отправки
        String jsonInputString = "{\"Temperature00\": " + temp*0.9 + ", " +
                "\"Temperature03\": " + temp*0.5 + ", " +
                "\"Temperature06\": " + temp*0.6 + ", " +
                "\"Temperature09\": " + temp*0.7 + ", " +
                "\"Temperature12\": " + temp*0.8 + ", " +
                "\"Temperature15\": " + temp*0.9 + ", " +
                "\"Temperature18\": " + temp + ", " +
                "\"Temperature21\": " + temp*0.8 + ", " +
                "\"Atmospheric pressure normalized to mean sea level00\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level03\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level06\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level09\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level12\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level15\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level18\": " + pressure + ", " +
                "\"Atmospheric pressure normalized to mean sea level21\": " + pressure + ", " +
                "\"Relative humidity00\": " + humidity + ", " +
                "\"Relative humidity03\": " + humidity + ", " +
                "\"Relative humidity06\": " + humidity + ", " +
                "\"Relative humidity09\": " + humidity + ", " +
                "\"Relative humidity12\": " + humidity + ", " +
                "\"Relative humidity15\": " + humidity + ", " +
                "\"Relative humidity18\": " + humidity + ", " +
                "\"Relative humidity21\": " + humidity + ", " +
                "\"Snow depth09\": 29}";

        System.out.println(jsonInputString);
        // Отправка данных
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Получение кода ответа
        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Чтение JSON-ответа
        StringBuilder response = new StringBuilder();
        try (InputStream inputStream = con.getInputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        // Вывод JSON-ответа
        String[] answ = new String[3];
        answ = response.toString().split(" ");
        String a = answ[3].replaceAll("[^0-9.]", "");

        return a;
    }

}
