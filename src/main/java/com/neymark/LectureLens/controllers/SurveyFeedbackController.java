package com.neymark.LectureLens.controllers;

import com.neymark.LectureLens.models.SurveyFeedback;
import com.neymark.LectureLens.services.SurveyFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class SurveyFeedbackController {

    private final SurveyFeedbackService feedbackService;

    @GetMapping("/teacher/{teacherId}")
    public List<SurveyFeedback> getFeedbacksByTeacher(@PathVariable Long teacherId) {
        return feedbackService.getFeedbacksByTeacher(teacherId);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<SurveyFeedback> getFeedbacksByLesson(@PathVariable Long lessonId) {
        return feedbackService.getFeedbacksByLesson(lessonId);
    }

    @GetMapping("/subject/{subjectId}")
    public List<SurveyFeedback> getFeedbacksBySubject(@PathVariable Long subjectId) {
        return feedbackService.getFeedbacksBySubject(subjectId);
    }
}

