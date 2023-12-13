package com.example.assessment.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CustomDataStorage {
    private final Map<Long, CustomData> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public long saveData(CustomData data) {
        long id = idGenerator.getAndIncrement();
        data.setId(id);
        storage.put(id, data);
        return id;
    }

    public CustomData getData(long id) {
        return storage.get(id);
    }

    public void updateData(long id, CustomData updatedData) {
        if (storage.containsKey(id)) {
            updatedData.setId(id);
            storage.put(id, updatedData);
        }
    }

    public void deleteData(long id) {
        storage.remove(id);
    }
}
