package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {

}
