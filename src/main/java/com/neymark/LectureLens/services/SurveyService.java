package com.neymark.LectureLens.services;

import com.neymark.LectureLens.dto.AnswersSubmitRequestDTO;
import com.neymark.LectureLens.dto.KeyWordDTO;
import com.neymark.LectureLens.models.Answer;
import com.neymark.LectureLens.models.Question;
import com.neymark.LectureLens.models.Survey;
import com.neymark.LectureLens.models.User;
import com.neymark.LectureLens.repositories.AnswerRepository;
import com.neymark.LectureLens.repositories.QuestionRepository;
import com.neymark.LectureLens.repositories.SurveyRepository;
import com.neymark.LectureLens.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Service
public class SurveyService {

    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final KeyWordsService keyWordsService;

    @Transactional
    public void submitAnswers(AnswersSubmitRequestDTO request) {
        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getAnswers() != null) {
            for (AnswersSubmitRequestDTO.AnswerRequestDTO dto : request.getAnswers()) {
                Question question = questionRepository.findById(dto.getQuestionId())
                        .orElseThrow(() -> new RuntimeException("Question not found"));
                Answer answer = new Answer();
                answer.setSurvey(survey);
                answer.setUser(user);
                answer.setQuestion(question);
                answer.setAnswerValue(dto.getAnswerValue());
                String text = dto.getTextComment();
                Long subjectId = dto.getSubjectID();
                keyWordsService.extractKeywords(text, subjectId);
                answer.setTextComment(text);
                answer.setVoiceCommentUrl(dto.getVoiceCommentUrl());
                answer.setVoiceCommentText(dto.getVoiceCommentText());
                answer.setCreatedAt(LocalDateTime.now());
                List<KeyWordDTO> keywords = keyWordsService.extractKeywords(dto.getVoiceCommentText(), subjectId);
                keywords.forEach(k -> System.out.println(k.getWord() + " → " + k.getFrequency()));

                answerRepository.save(answer);
            }
        }
    }
}
