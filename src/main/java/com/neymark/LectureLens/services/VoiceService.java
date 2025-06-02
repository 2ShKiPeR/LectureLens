package com.neymark.LectureLens.services;

import com.neymark.LectureLens.utils.MultipartInputStreamFileResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoiceService {

    public String transcribeWithPython(MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("audio", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8001/transcribe";

            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
            return response.getBody().get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "[Ошибка расшифровки]";
        }
    }

    public String analyzeSentiment(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8001/analyze-sentiment";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            return response.getBody().get("sentiment").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "[Ошибка анализа тональности]";
        }
    }
}
