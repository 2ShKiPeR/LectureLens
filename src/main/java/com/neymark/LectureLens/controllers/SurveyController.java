package com.neymark.LectureLens.controllers;


import com.neymark.LectureLens.dto.AnswersSubmitRequestDTO;
import com.neymark.LectureLens.services.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SurveyController {
    private final AnswerService answerService;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitAnswers(@RequestBody AnswersSubmitRequestDTO request) {
        answerService.submitAnswers(request);
        return ResponseEntity.ok().build();
    }
}
