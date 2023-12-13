package com.example.assessment.service;

import com.example.assessment.model.ResponseExternalApi;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
public class ExternalApiService {

    private final Map<String, ResponseExternalApi> externalApiResponseMap = new HashMap<>();
    private HttpClient httpClient = HttpClient.newHttpClient();

    public ResponseExternalApi getExternalById(String id) {
        return externalApiResponseMap.get(id);
    }

    public List<ResponseExternalApi> getAllExternal() {
        return new ArrayList<>(externalApiResponseMap.values());
    }

    public String consumeExternalAPI() {
        String apiUrl = "https://api.kanye.rest";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                String responseBody = response.body();
                ResponseExternalApi responseObject = new ResponseExternalApi(responseBody);
                String id = responseObject.getId();
                externalApiResponseMap.put(id, responseObject);
                return "Salvo com o ID: " + id;
            } else {
                return "Erro ao consumir a API externa. Status code: " + response.statusCode();
            }
        } catch (Exception e) {
            return "Erro ao consumir a API externa: " + e.getMessage();
        }
    }
}
