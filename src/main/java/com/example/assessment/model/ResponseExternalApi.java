package com.example.assessment.model;

import java.util.concurrent.atomic.AtomicLong;

public class ResponseExternalApi {
    private static AtomicLong idGenerator = new AtomicLong(1);

    private long id;
    private String content;

    public ResponseExternalApi(String content) {
        this.id = idGenerator.getAndIncrement();
        this.content = content;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
