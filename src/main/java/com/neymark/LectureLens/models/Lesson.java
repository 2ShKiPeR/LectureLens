package com.neymark.LectureLens.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Subject subject;

    private LocalDate date;

    @ManyToOne
    private User teacher;

    private String description;

    // getters, setters
}

