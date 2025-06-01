package com.neymark.LectureLens.models;

import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Lesson lesson;

    private LocalDateTime createdAt;
    private boolean isActive;

    // getters, setters
}

