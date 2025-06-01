package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("""
           select a from Answer a
           join a.survey s
           join s.lesson l
           where l.teacher.id = :teacherId
           """)
    List<Answer> findAllByTeacherId(@Param("teacherId") Long teacherId);

}
