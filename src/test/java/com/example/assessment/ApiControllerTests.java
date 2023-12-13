package com.example.assessment;

import com.example.assessment.controllers.ApiController;
import com.example.assessment.model.CustomData;
import com.example.assessment.service.ApiService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiControllerTests {

    @Mock
    private ApiService apiService;

    @InjectMocks
    private ApiController apiController;

    @Test
    void testHandlePostRequestWithValidData() {
        CustomData validData = new CustomData();
        validData.setStringValue("Exemplo de valor");
        validData.setIntValue(10);
        validData.setArrayValue(Arrays.asList("item1", "item2", "item3"));
        when(apiService.saveData(any())).thenReturn(1L);

        ResponseEntity<String> response = apiController.handlePostRequest(validData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Postado com sucesso! Número do ID: 1", response.getBody());
    }

    @Test
    void testHandlePostRequestWithInvalidData() {
        CustomData invalidData = new CustomData();
        invalidData.setStringValue("Teste");
        invalidData.setIntValue(3);
        ResponseEntity<String> response = apiController.handlePostRequest(invalidData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Erro 400, bad request.", response.getBody());
    }


}
