package com.neymark.LectureLens;


import com.neymark.LectureLens.dto.KeyWordDTO;
import com.neymark.LectureLens.services.KeyWordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class KeyWordsServiceIntegrationTest {

    @Autowired
    private KeyWordsService keyWordsService;

    @Test
    void testExtractKeywordsFromPythonServer() {
        String text = "Обратная связь была отличной.";
        Long subjectId = 1L;

        List<KeyWordDTO> result = keyWordsService.extractKeywords(text, subjectId);

        assertNotNull(result, "Результат не должен быть null");
        assertFalse(result.isEmpty(), "Список не должен быть пустым");
    }
}
