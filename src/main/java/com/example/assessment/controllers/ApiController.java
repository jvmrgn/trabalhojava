package com.example.assessment.controllers;
import com.example.assessment.model.CustomData;
import com.example.assessment.service.ApiService;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/post")
    public ResponseEntity<String> handlePostRequest(@RequestBody CustomData requestObject) {
        if (requestObject == null || !isValidData(requestObject)) {
            return ResponseEntity.ok("Erro 400, bad request.");
        }

        long id = apiService.saveData(requestObject);
        log.info("Status Code: 200");
        return ResponseEntity.ok("Postado com sucesso! Número do ID: " + id);
    }
    @GetMapping("/get")
    public ResponseEntity<CustomData> handleGetRequest(
            @RequestParam(required = false, name = "id") Long id,
            @RequestParam(required = false, name = "param2") String param2
    ) {
        CustomData data = apiService.getData(id, param2);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> handlePutRequest(@PathVariable Long id, @RequestBody CustomData updatedData) {
        boolean updated = apiService.updateData(id, updatedData);
        if (updated) {
            return ResponseEntity.ok("Modificado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> handleDeleteRequest(@PathVariable Long id) {
        boolean deleted = apiService.deleteData(id);
        if (deleted) {
            return ResponseEntity.ok("Apagado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean isValidData(CustomData data) {
        return data != null &&
                data.getStringValue() != null &&
                !data.getStringValue().isEmpty() &&
                data.getIntValue() != 0 &&
                data.getArrayValue() != null &&
                !data.getArrayValue().isEmpty();
    }
}
