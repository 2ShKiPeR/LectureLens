package com.neymark.LectureLens.services;

import com.neymark.LectureLens.models.SurveyFeedback;
import com.neymark.LectureLens.repositories.SurveyFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyFeedbackService {
    private final SurveyFeedbackRepository surveyFeedbackRepository;

    public List<SurveyFeedback> getFeedbacksByTeacher(Long teacherId) {
        return surveyFeedbackRepository.findAllByTeacherId(teacherId);
    }

    public List<SurveyFeedback> getFeedbacksByLesson(Long lessonId) {
        return surveyFeedbackRepository.findAllByLessonId(lessonId);
    }

    public List<SurveyFeedback> getFeedbacksBySubject(Long subjectId) {
        return surveyFeedbackRepository.findAllBySubjectId(subjectId);
    }
}

