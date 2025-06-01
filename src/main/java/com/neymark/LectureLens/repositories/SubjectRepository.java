package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
