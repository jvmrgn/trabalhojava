package com.example.assessment;

import com.example.assessment.model.CustomData;
import com.example.assessment.model.CustomDataStorage;
import com.example.assessment.service.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiServiceTests {

    private CustomDataStorage storage;
    private ApiService apiService;

    @BeforeEach
    void setUp() {
        storage = mock(CustomDataStorage.class);
        apiService = new ApiService(storage);
    }

    @Test
    void testSaveDataWithNullData() {
        assertThrows(NullPointerException.class, () -> {
            apiService.saveData(null);
        });
    }


    @Test
    void testSaveData() {
        CustomData validData = new CustomData();
        when(storage.saveData(validData)).thenReturn(1L);

        long result = apiService.saveData(validData);

        assertEquals(1L, result);
    }

    @Test
    void testUpdateData() {
        CustomData existingData = new CustomData();
        when(storage.getData(1L)).thenReturn(existingData);

        CustomData updatedData = new CustomData();
        boolean result = apiService.updateData(1L, updatedData);

        assertTrue(result);
        verify(storage).updateData(1L, updatedData);
    }

    @Test
    void testUpdateDataNonExistent() {
        when(storage.getData(1L)).thenReturn(null);

        CustomData updatedData = new CustomData();
        boolean result = apiService.updateData(1L, updatedData);

        assertFalse(result);
        verify(storage, never()).updateData(anyLong(), any());
    }

    @Test
    void testDeleteData() {
        CustomData existingData = new CustomData();
        when(storage.getData(1L)).thenReturn(existingData);

        boolean result = apiService.deleteData(1L);

        assertTrue(result);
        verify(storage).deleteData(1L);
    }

    @Test
    void testDeleteDataNonExistent() {
        when(storage.getData(1L)).thenReturn(null);

        boolean result = apiService.deleteData(1L);

        assertFalse(result);
        verify(storage, never()).deleteData(anyLong());
    }
}

