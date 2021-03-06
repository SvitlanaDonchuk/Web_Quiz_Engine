package engine.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.answer.SolvedQuiz;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serializer implements AttributeConverter<List<SolvedQuiz>, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<SolvedQuiz> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @Override
    public List<SolvedQuiz> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
