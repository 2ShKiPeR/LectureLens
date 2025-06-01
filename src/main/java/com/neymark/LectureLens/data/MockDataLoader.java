package com.neymark.LectureLens.data;

import com.neymark.LectureLens.models.*;
import com.neymark.LectureLens.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MockDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SurveyFeedbackRepository surveyFeedbackRepository;

    @Override
    public void run(String... args) {
        // --- 1. USERS ---
        User teacher = new User();
        teacher.setRole("teacher");
        teacher.setAnonymousHash("t1");
        teacher.setCreatedAt(LocalDateTime.now());
        userRepository.save(teacher);

        User student1 = new User();
        student1.setRole("student");
        student1.setAnonymousHash("s1");
        student1.setCreatedAt(LocalDateTime.now());
        userRepository.save(student1);

        User student2 = new User();
        student2.setRole("student");
        student2.setAnonymousHash("s2");
        student2.setCreatedAt(LocalDateTime.now());
        userRepository.save(student2);

        // --- 2. SUBJECT ---
        Subject math = new Subject();
        math.setName("Математика");
        math.setDescription("Базовый курс математики");
        subjectRepository.save(math);

        // --- 3. LESSON ---
        Lesson lesson = new Lesson();
        lesson.setSubject(math);
        lesson.setTeacher(teacher);
        lesson.setDate(LocalDate.now());
        lesson.setDescription("Лекция по линейной алгебре");
        lessonRepository.save(lesson);

        // --- 4. SURVEY ---
        Survey survey = new Survey();
        survey.setLesson(lesson);
        survey.setCreatedAt(LocalDateTime.now());
        survey.setActive(true);
        surveyRepository.save(survey);

        // --- 5. QUESTIONS ---
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Question q = new Question();
            q.setSurvey(survey);
            q.setOrderNumber(i);
            q.setText("Вопрос №" + i + " по теме лекции");
            q.setType("rating");
            q.setMaxValue(5);
            questionRepository.save(q);
            questions.add(q);
        }

        // --- 6. ANSWERS от студента 1 (разная окраска и часть — только текст) ---
        Answer a1s1 = new Answer();
        a1s1.setSurvey(survey);
        a1s1.setQuestion(questions.get(0));
        a1s1.setUser(student1);
        a1s1.setAnswerValue("5");
        a1s1.setTextComment("Материал был объяснён очень понятно, примеры помогли усвоить тему.");
        // Нет голосового комментария
        a1s1.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a1s1);

        Answer a2s1 = new Answer();
        a2s1.setSurvey(survey);
        a2s1.setQuestion(questions.get(1));
        a2s1.setUser(student1);
        a2s1.setAnswerValue("4");
        a2s1.setTextComment("Было немного быстро, но в целом справился.");
        a2s1.setVoiceCommentUrl("https://voice.mock/answer1q2.mp3");
        a2s1.setVoiceCommentText("Голосом: хотелось бы чуть помедленнее, но в целом понятно.");
        a2s1.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a2s1);

        Answer a3s1 = new Answer();
        a3s1.setSurvey(survey);
        a3s1.setQuestion(questions.get(2));
        a3s1.setUser(student1);
        a3s1.setAnswerValue("5");
        a3s1.setTextComment("Спасибо за подробные ответы на вопросы в конце лекции!");
        // Нет голосового комментария
        a3s1.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a3s1);

        // --- 7. ANSWERS от студента 2 (разная окраска и часть — только голос, часть — только текст) ---
        Answer a1s2 = new Answer();
        a1s2.setSurvey(survey);
        a1s2.setQuestion(questions.get(0));
        a1s2.setUser(student2);
        a1s2.setAnswerValue("3");
        a1s2.setTextComment("Сложно было следить за ходом рассуждений, мало визуальных схем.");
        // Нет голосового комментария
        a1s2.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a1s2);

        Answer a2s2 = new Answer();
        a2s2.setSurvey(survey);
        a2s2.setQuestion(questions.get(1));
        a2s2.setUser(student2);
        a2s2.setAnswerValue("2");
        // Текст отсутствует, только голосовой фидбек
        a2s2.setVoiceCommentUrl("https://voice.mock/answer2q2.mp3");
        a2s2.setVoiceCommentText("Голосом: объяснения были неполными, хотелось бы больше подробностей.");
        a2s2.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a2s2);

        Answer a3s2 = new Answer();
        a3s2.setSurvey(survey);
        a3s2.setQuestion(questions.get(2));
        a3s2.setUser(student2);
        a3s2.setAnswerValue("4");
        a3s2.setTextComment("Заключительная часть лекции понравилась, интересные примеры.");
        // Нет голосового комментария
        a3s2.setCreatedAt(LocalDateTime.now());
        answerRepository.save(a3s2);

        // --- 8. SURVEY FEEDBACK от студента 1 (позитив) ---
        SurveyFeedback feedback1 = new SurveyFeedback();
        feedback1.setSurvey(survey);
        feedback1.setUser(student1);
        feedback1.setTextFeedback("Лекция очень понравилась, объясняли доступно, с примерами. Жду следующую!");
        // Нет голосового фидбека
        feedback1.setCreatedAt(LocalDateTime.now());
        surveyFeedbackRepository.save(feedback1);

        // --- 9. SURVEY FEEDBACK от студента 2 (нейтрально-негативный, только голос) ---
        SurveyFeedback feedback2 = new SurveyFeedback();
        feedback2.setSurvey(survey);
        feedback2.setUser(student2);
        // Только голосовой фидбек, текстовое поле пустое
        feedback2.setVoiceFeedbackUrl("https://voice.mock/feedback2.mp3");
        feedback2.setVoiceFeedbackText("Голосом: хотелось бы больше наглядных примеров и медленнее объяснение.");
        feedback2.setCreatedAt(LocalDateTime.now());
        surveyFeedbackRepository.save(feedback2);
    }
}
