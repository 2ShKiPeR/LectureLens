package com.neymark.LectureLens.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue
    private Long id;

    private String role; // student, teacher, admin

    private String anonymousHash; // если нужно анонимизировать

    private LocalDateTime createdAt;

}

