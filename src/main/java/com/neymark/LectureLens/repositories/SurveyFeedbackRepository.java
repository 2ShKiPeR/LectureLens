package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.SurveyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyFeedbackRepository extends JpaRepository<SurveyFeedback, Long> {
    // Все отзывы, связанные с занятиями определённого преподавателя
    @Query("""
           select f from SurveyFeedback f
           join f.survey s
           join s.lesson l
           where l.teacher.id = :teacherId
           """)
    List<SurveyFeedback> findAllByTeacherId(@Param("teacherId") Long teacherId);

    // Отзывы для определённого занятия
    @Query("""
           select f from SurveyFeedback f
           join f.survey s
           where s.lesson.id = :lessonId
           """)
    List<SurveyFeedback> findAllByLessonId(@Param("lessonId") Long lessonId);

    // Отзывы по предмету (через занятия)
    @Query("""
           select f from SurveyFeedback f
           join f.survey s
           join s.lesson l
           where l.subject.id = :subjectId
           """)
    List<SurveyFeedback> findAllBySubjectId(@Param("subjectId") Long subjectId);
}

