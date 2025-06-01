package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.SurveyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyFeedbackRepository extends JpaRepository<SurveyFeedback, Long> {
}
