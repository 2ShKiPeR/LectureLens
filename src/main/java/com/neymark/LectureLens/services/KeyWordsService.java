package com.neymark.LectureLens.services;

import com.neymark.LectureLens.dto.KeyWordDTO;
import com.neymark.LectureLens.models.KeyWord;
import com.neymark.LectureLens.models.Subject;
import com.neymark.LectureLens.repositories.KeyWordRepository;
import com.neymark.LectureLens.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeyWordsService {

    private final KeyWordRepository keyWordRepository;
    private final SubjectRepository subjectRepository;

    public List<KeyWordDTO> extractKeywords(String text, Long subjectId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8001/extract-keywords";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<List<KeyWordDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<>() {}
            );

            List<KeyWordDTO> keywords = response.getBody();

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            if (keywords != null) {
                for (KeyWordDTO dto : keywords) {
                    KeyWord keyword = new KeyWord();
                    keyword.setPhrase(dto.getWord());
                    keyword.setFrequency(dto.getFrequency());
                    keyword.setSubject(subject);
                    keyWordRepository.save(keyword);
                }
            }

            return keywords; // ✅ Вернём результат

        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // ❗ Лучше пустой список, чем null
        }
    }

}
