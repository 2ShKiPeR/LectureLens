package com.neymark.LectureLens.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "survey_feedbacks")
public class SurveyFeedback {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Survey survey;

    @ManyToOne
    private User user;

    private String textFeedback; // текстовый общий фидбек

    private String voiceFeedbackUrl; // ссылка на аудиофайл (если есть)
    private String voiceFeedbackText; // распознанный текст из аудио
    private LocalDateTime createdAt;

    // getters, setters
}

