package com.neymark.LectureLens.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Survey survey;

    private Integer orderNumber; // 1-18
    private String text;
    private String type; // например, single-choice, rating и т.д.
    private Integer maxPoint;
    // getters, setters
}

