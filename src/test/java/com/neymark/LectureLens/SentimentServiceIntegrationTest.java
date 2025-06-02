package com.neymark.LectureLens;

import com.neymark.LectureLens.services.VoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SentimentServiceIntegrationTest {

    @Autowired
    private VoiceService voiceService;

    @Test
    void testAnalyzeSentimentFromPythonServer() {
        String text = "Лекции были очень непонятными и бесполезными. Несмотря на то, что преподаватель старался объяснять на простом языке!.";

        String sentiment = voiceService.analyzeSentiment(text);

        assertNotNull(sentiment, "Тональность не должна быть null");
        assertTrue(
                sentiment.equals("positive") || sentiment.equals("neutral") || sentiment.equals("negative"),
                "Ожидалась тональность: positive, neutral или negative"
        );

        System.out.println("➡ Результат анализа: " + sentiment);
    }
}
