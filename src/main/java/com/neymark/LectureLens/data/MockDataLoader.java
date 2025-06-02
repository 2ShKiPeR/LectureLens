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

//
//        // --- 6. ANSWERS от студента 1 (разная окраска и часть — только текст) ---
//        Answer a1s1 = new Answer();
//        a1s1.setSurvey(survey);
//        a1s1.setQuestion(questions.get(0));
//        a1s1.setUser(student1);
//        a1s1.setAnswerValue("5");
//        a1s1.setTextComment("Материал был объяснён очень понятно, примеры помогли усвоить тему.");
//        // Нет голосового комментария
//        a1s1.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a1s1);
//
//        Answer a2s1 = new Answer();
//        a2s1.setSurvey(survey);
//        a2s1.setQuestion(questions.get(1));
//        a2s1.setUser(student1);
//        a2s1.setAnswerValue("4");
//        a2s1.setTextComment("Было немного быстро, но в целом справился.");
//        a2s1.setVoiceCommentUrl("https://voice.mock/answer1q2.mp3");
//        a2s1.setVoiceCommentText("Голосом: хотелось бы чуть помедленнее, но в целом понятно.");
//        a2s1.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a2s1);
//
//        Answer a3s1 = new Answer();
//        a3s1.setSurvey(survey);
//        a3s1.setQuestion(questions.get(2));
//        a3s1.setUser(student1);
//        a3s1.setAnswerValue("5");
//        a3s1.setTextComment("Спасибо за подробные ответы на вопросы в конце лекции!");
//        // Нет голосового комментария
//        a3s1.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a3s1);
//
//        // --- 7. ANSWERS от студента 2 (разная окраска и часть — только голос, часть — только текст) ---
//        Answer a1s2 = new Answer();
//        a1s2.setSurvey(survey);
//        a1s2.setQuestion(questions.get(0));
//        a1s2.setUser(student2);
//        a1s2.setAnswerValue("3");
//        a1s2.setTextComment("Сложно было следить за ходом рассуждений, мало визуальных схем.");
//        // Нет голосового комментария
//        a1s2.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a1s2);
//
//        Answer a2s2 = new Answer();
//        a2s2.setSurvey(survey);
//        a2s2.setQuestion(questions.get(1));
//        a2s2.setUser(student2);
//        a2s2.setAnswerValue("2");
//        // Текст отсутствует, только голосовой фидбек
//        a2s2.setVoiceCommentUrl("https://voice.mock/answer2q2.mp3");
//        a2s2.setVoiceCommentText("Голосом: объяснения были неполными, хотелось бы больше подробностей.");
//        a2s2.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a2s2);
//
//        Answer a3s2 = new Answer();
//        a3s2.setSurvey(survey);
//        a3s2.setQuestion(questions.get(2));
//        a3s2.setUser(student2);
//        a3s2.setAnswerValue("4");
//        a3s2.setTextComment("Заключительная часть лекции понравилась, интересные примеры.");
//        // Нет голосового комментария
//        a3s2.setCreatedAt(LocalDateTime.now());
//        answerRepository.save(a3s2);

        // --- 5. QUESTIONS для отчета 5 ---
        List<Question> questions = new ArrayList<>();

// Вопрос 1
        Question q1 = new Question();
        q1.setSurvey(survey);
        q1.setOrderNumber(1);
        q1.setText("Оцените по шкале проведенное занятие");
        q1.setType("rating");
        q1.setMaxValue(10);
        questionRepository.save(q1);
        questions.add(q1);

// Вопрос 2
        Question q2 = new Question();
        q2.setSurvey(survey);
        q2.setOrderNumber(2);
        q2.setText("Хотите продолжить?");
        q2.setType("single-choice");
        q2.setMaxValue(1);
        questionRepository.save(q2);
        questions.add(q2);

// Вопрос 3
        Question q3 = new Question();
        q3.setSurvey(survey);
        q3.setOrderNumber(3);
        q3.setText("Все ли вам было понятно на занятии?");
        q3.setType("2");
        q3.setMaxValue(2);
        questionRepository.save(q3);
        questions.add(q3);

// Вопрос 4
        Question q4 = new Question();
        q4.setSurvey(survey);
        q4.setOrderNumber(4);
        q4.setText("Рассказывал ли вам преподаватель о том, как применять  материал на практике (на месте вашей будущей работы)?");
        q4.setType("3");
        q4.setMaxValue(1);
        questionRepository.save(q4);
        questions.add(q4);

// Вопрос 5
        Question q5 = new Question();
        q5.setSurvey(survey);
        q5.setOrderNumber(5);
        q5.setText("Было ли вам интересно на занятии?");
        q5.setType("4");
        q5.setMaxValue(2);
        questionRepository.save(q5);
        questions.add(q5);

// Вопрос 6
        Question q6 = new Question();
        q6.setSurvey(survey);
        q6.setOrderNumber(6);
        q6.setText("Легко ли вам было воспринимать преподаваемый материал(темп речи, интонация, наглядность подачи материала)?");
        q6.setType("5");
        q6.setMaxValue(2);
        questionRepository.save(q6);
        questions.add(q6);

// Вопрос 7
        Question q7 = new Question();
        q7.setSurvey(survey);
        q7.setOrderNumber(7);
        q7.setText("Представляете ли вы, как применить полученные знания на практике (на месте вашей будущей работы)?");
        q7.setType("3");
        q7.setMaxValue(1);
        questionRepository.save(q7);
        questions.add(q7);

// Вопрос 8
        Question q8 = new Question();
        q8.setSurvey(survey);
        q8.setOrderNumber(8);
        q8.setText("Выстраивается ли у вас с преподавателем конструктивный диалог?");
        q8.setType("10");
        q8.setMaxValue(1);
        questionRepository.save(q8);
        questions.add(q8);

// Вопрос 9
        Question q9 = new Question();
        q9.setSurvey(survey);
        q9.setOrderNumber(9);
        q9.setText("Соответствует ли внешний вид преподавателя академическому стилю?");
        q9.setType("10");
        q9.setMaxValue(1);
        questionRepository.save(q9);
        questions.add(q9);

// Вопрос 10 (комментарий)
        Question q10 = new Question();
        q10.setSurvey(survey);
        q10.setOrderNumber(10);
        q10.setText("Ваш комментарий\nЕсли хотите, можете написать что-то, что выходит за рамки предыдущих вопросов.");
        q10.setType("feedback");
        q10.setMaxValue(null);
        questionRepository.save(q10);
        questions.add(q10);

// Вопрос 11
        Question q11 = new Question();
        q11.setSurvey(survey);
        q11.setOrderNumber(11);
        q11.setText("Оцените качество преподавания курса в течение прошедшего времени?");
        q11.setType("rating");
        q11.setMaxValue(10);
        questionRepository.save(q11);
        questions.add(q11);

// Вопрос 12
        Question q12 = new Question();
        q12.setSurvey(survey);
        q12.setOrderNumber(12);
        q12.setText("Как вы оцениваете свои знания по прохождению курса в настоящее время?");
        q12.setType("2");
        q12.setMaxValue(2);
        questionRepository.save(q12);
        questions.add(q12);

// Вопрос 13
        Question q13 = new Question();
        q13.setSurvey(survey);
        q13.setOrderNumber(13);
        q13.setText("Содержал ли изученный материал научную составляющую (термины, теории, учения, историю научных открытий)?");
        q13.setType("1");
        q13.setMaxValue(1);
        questionRepository.save(q13);
        questions.add(q13);

// Вопрос 14
        Question q14 = new Question();
        q14.setSurvey(survey);
        q14.setOrderNumber(14);
        q14.setText("Можете ли вы самостоятельно осваивать новый материал на основе уже полученных знаний?");
        q14.setType("6");
        q14.setMaxValue(2);
        questionRepository.save(q14);
        questions.add(q14);

// Вопрос 15
        Question q15 = new Question();
        q15.setSurvey(survey);
        q15.setOrderNumber(15);
        q15.setText("Получаете ли вы от преподавателя доступные ответы на заданные вопросы?");
        q15.setType("7");
        q15.setMaxValue(1);
        questionRepository.save(q15);
        questions.add(q15);

// Вопрос 16
        Question q16 = new Question();
        q16.setSurvey(survey);
        q16.setOrderNumber(16);
        q16.setText("Считаете ли вы, что получаемые знания являются полезными для вашей будущей работы?");
        q16.setType("8");
        q16.setMaxValue(2);
        questionRepository.save(q16);
        questions.add(q16);

// Вопрос 17
        Question q17 = new Question();
        q17.setSurvey(survey);
        q17.setOrderNumber(17);
        q17.setText("Считаете ли вы, что объем самостоятельной работы оптимален? (Уровень затраченных усилий соответствует получаемым знаниям)");
        q17.setType("4");
        q17.setMaxValue(2);
        questionRepository.save(q17);
        questions.add(q17);

// Вопрос 18
        Question q18 = new Question();
        q18.setSurvey(survey);
        q18.setOrderNumber(18);
        q18.setText("Проводит ли преподаватель интерактивные коллективные занятия? (Дискуссии, открытые столы, дебаты, деловые игры и тд)");
        q18.setType("9");
        q18.setMaxValue(1);
        questionRepository.save(q18);
        questions.add(q18);

// Вопрос 19
        Question q19 = new Question();
        q19.setSurvey(survey);
        q19.setOrderNumber(19);
        q19.setText("Доверяете ли вы вашему преподавателю?");
        q19.setType("10");
        q19.setMaxValue(1);
        questionRepository.save(q19);
        questions.add(q19);

// Вопрос 20
        Question q20 = new Question();
        q20.setSurvey(survey);
        q20.setOrderNumber(20);
        q20.setText("Считаете ли вы, что преподаватель к вам справедлив?");
        q20.setType("10");
        q20.setMaxValue(1);
        questionRepository.save(q20);
        questions.add(q20);

// Вопрос 21 (комментарий)
        Question q21 = new Question();
        q21.setSurvey(survey);
        q21.setOrderNumber(21);
        q21.setText("Ваш комментарий\nЕсли хотите, можете написать что-то, что выходит за рамки предыдущих вопросов");
        q21.setType("feedback");
        q21.setMaxValue(null);
        questionRepository.save(q21);
        questions.add(q21);

    }
}
