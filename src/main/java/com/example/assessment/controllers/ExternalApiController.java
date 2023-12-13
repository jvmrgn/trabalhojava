package com.example.assessment.controllers;

import com.example.assessment.model.ResponseExternalApi;
import com.example.assessment.service.ExternalApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    @Autowired
    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/external/{id}")
    public ResponseEntity<ResponseExternalApi> getExternalById(@PathVariable String id) {
        ResponseExternalApi response = externalApiService.getExternalById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/external/all")
    public ResponseEntity<List<ResponseExternalApi>> getAllExternal() {
        List<ResponseExternalApi> responses = externalApiService.getAllExternal();
        if (!responses.isEmpty()) {
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/external")
    public ResponseEntity<String> consumeExternalAPI() {
        String result = externalApiService.consumeExternalAPI();
        if (result.startsWith("Salvo com o id: ")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(500).body(result);
        }
    }
}
