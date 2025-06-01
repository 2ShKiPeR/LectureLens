package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
