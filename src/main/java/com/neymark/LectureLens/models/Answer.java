package com.neymark.LectureLens.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Survey survey;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    private String answerValue; // выбранный вариант (например, "4" для рейтинга)

    private String textComment; // текстовый комментарий

    private String voiceCommentUrl; // ссылка на аудио-файл (если есть)
    private String voiceCommentText; // распознанный текст из голосового комментария

    private LocalDateTime createdAt;

}

