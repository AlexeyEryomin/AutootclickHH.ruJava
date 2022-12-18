package com.example.absolutelyfinalautootclickwork;

import java.net.URI;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPRequestHHVacancies {
    static String vacancy_name;
    static String per_page;
    static String page;
    static ArrayList<Integer> id_vacancies = new ArrayList<>();


    public static void GetJobsID() {
        // метод 2 GET запроса

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("https://api.hh.ru/vacancies?text=%s&per_page=%s&page=%s", vacancy_name, per_page, page))).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(HTTPRequestHHVacancies::parse)
                .join();
    }
    public static String parse(String responseBody) {
        JSONObject album = new JSONObject(responseBody);
        // System.out.println(album); Здесь я узнаю что мой ДЖЭЙсон(Стэтхэм вообще создан и получен album - это потомучто так написал чувак в ютубе я оставил так же чтобы ничего не поломать у себя
        JSONArray jsonArray = (JSONArray) album.get("items");
        // System.out.println(jsonArray); ЗДесь я узнаю что я смог его превратить в массив
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject vacancy_list = jsonArray.getJSONObject(i);
            int id = vacancy_list.getInt("id");
            String name = vacancy_list.getString("name");
            // System.out.println(id + " " + name); напечатать название вакансии и ее id
            id_vacancies.add(id);
        }
        return null;
    }

}