package com.neymark.LectureLens.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "key_words")
public class KeyWord {
    @Id
    @GeneratedValue
    private Long id;

    private String phrase;

    private Integer frequency;

    @ManyToOne
    private Subject subject;
}
