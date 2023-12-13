package com.example.assessment.service;
import com.example.assessment.model.CustomData;
import com.example.assessment.model.CustomDataStorage;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final CustomDataStorage storage;

    public ApiService(CustomDataStorage storage) {
        this.storage = storage;
    }

    public long saveData(CustomData data) {
        if (data == null) {
            throw new NullPointerException("O argumento 'data' não pode ser nulo");
        }
        return storage.saveData(data);
    }


    public CustomData getData(Long id, String param2) {
        if (id != null) {
            return storage.getData(id);
        } else if (param2 != null) {
            // Lógica para lidar com param2, se necessário
        }
        return null;
    }

    public boolean updateData(Long id, CustomData updatedData) {
        CustomData existingData = storage.getData(id);
        if (existingData != null) {
            storage.updateData(id, updatedData);
            return true;
        }
        return false;
    }

    public boolean deleteData(Long id) {
        CustomData existingData = storage.getData(id);
        if (existingData != null) {
            storage.deleteData(id);
            return true;
        }
        return false;
    }
}
