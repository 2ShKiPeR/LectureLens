package com.neymark.LectureLens.dto;

import lombok.Data;

import java.util.List;



@Data
public class AnswersSubmitRequest {
    private Long surveyId;
    private Long userId;
    private List<AnswerRequestDTO> answers; // ответы к вопросам (массив)

    @Data
    public static class AnswerRequestDTO {
        private Long questionId;
        private String answerValue;      // если применимо
        private String textComment;      // развернутый текст
        private String voiceCommentUrl;  // опционально
        private String voiceCommentText; // опционально
    }
}

