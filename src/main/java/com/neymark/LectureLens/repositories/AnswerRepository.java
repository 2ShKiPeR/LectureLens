package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // 1. Все ответы по преподавателю (по всем опросам занятий этого преподавателя)
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        where l.teacher.id = :teacherId
    """)
    List<Answer> findAllByTeacherId(@Param("teacherId") Long teacherId);

    // 2. Все ответы по предмету (по всем занятиям и опросам этого предмета)
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        where l.subject.id = :subjectId
    """)
    List<Answer> findAllBySubjectId(@Param("subjectId") Long subjectId);

    // 3. Все ответы по занятию (lesson)
    @Query("""
        select a from Answer a
        join a.survey s
        where s.lesson.id = :lessonId
    """)
    List<Answer> findAllByLessonId(@Param("lessonId") Long lessonId);

    // 4. Все ответы по конкретному типу вопроса (например, только 'rating')
    @Query("""
        select a from Answer a
        join a.question q
        where q.type = :type
    """)
    List<Answer> findAllByQuestionType(@Param("type") String type);

    // 5. Все ответы по конкретному преподавателю и типу вопроса
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        join a.question q
        where l.teacher.id = :teacherId and q.type = :type
    """)
    List<Answer> findAllByTeacherIdAndQuestionType(@Param("teacherId") Long teacherId, @Param("type") String type);

    // 6. Все ответы по конкретному предмету и типу вопроса
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        join a.question q
        where l.subject.id = :subjectId and q.type = :type
    """)
    List<Answer> findAllBySubjectIdAndQuestionType(@Param("subjectId") Long subjectId, @Param("type") String type);

    // 7. Все ответы по конкретному занятию и типу вопроса
    @Query("""
        select a from Answer a
        join a.survey s
        join a.question q
        where s.lesson.id = :lessonId and q.type = :type
    """)
    List<Answer> findAllByLessonIdAndQuestionType(@Param("lessonId") Long lessonId, @Param("type") String type);

    // Все фидбеки по конкретному занятию
    @Query("""
        select a from Answer a
        join a.survey s
        join a.question q
        where s.lesson.id = :lessonId and q.type = 'feedback'
    """)
    List<Answer> findFeedbackByLessonId(@Param("lessonId") Long lessonId);

    // По преподавателю
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        join a.question q
        where l.teacher.id = :teacherId and q.type = 'feedback'
    """)
    List<Answer> findFeedbackByTeacherId(@Param("teacherId") Long teacherId);

    // По предмету
    @Query("""
        select a from Answer a
        join a.survey s
        join s.lesson l
        join a.question q
        where l.subject.id = :subjectId and q.type = 'feedback'
    """)
    List<Answer> findFeedbackBySubjectId(@Param("subjectId") Long subjectId);

}
