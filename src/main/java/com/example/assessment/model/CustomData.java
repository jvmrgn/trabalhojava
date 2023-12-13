package com.example.assessment.model;
import lombok.*;
import java.util.List;


@Getter
@Setter

@Data@AllArgsConstructor@NoArgsConstructor
public class CustomData {
        private long id;
        private String stringValue;
        private int intValue;
        private List<Object> arrayValue;

}