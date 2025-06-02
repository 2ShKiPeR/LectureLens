package com.neymark.LectureLens.dto;

import lombok.Data;

@Data
public class KeyWordDTO {
    private String word;
    private Integer frequency;
    private Long subjectId;
}

