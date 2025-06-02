package com.neymark.LectureLens.controller;

import com.neymark.LectureLens.dto.KeyWordDTO;
import com.neymark.LectureLens.services.KeyWordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KeyWordsController {
    private final KeyWordsService keyWordsService;
    @PostMapping("/extract-keywords")
    public ResponseEntity<List<KeyWordDTO>> extractKeywords(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        Long subjectID = Long.parseLong(payload.get("subjectID"));
        List<KeyWordDTO> keywords = keyWordsService.extractKeywords(text, subjectID);
        return ResponseEntity.ok(keywords);
    }
}
