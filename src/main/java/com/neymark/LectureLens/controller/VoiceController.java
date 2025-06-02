package com.neymark.LectureLens.controller;

import com.neymark.LectureLens.services.VoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VoiceController {

    private final VoiceService voiceService;

    @PostMapping("/voice-transcribe")
    public ResponseEntity<String> transcribe(@RequestParam("audio") MultipartFile audioFile) {
        String text = voiceService.transcribeWithPython(audioFile);
        return ResponseEntity.ok(text);
    }

    @PostMapping("/analyze-sentiment")
    public ResponseEntity<String> analyzeSentiment(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        String sentiment = voiceService.analyzeSentiment(text);
        return ResponseEntity.ok(sentiment);
    }
}
