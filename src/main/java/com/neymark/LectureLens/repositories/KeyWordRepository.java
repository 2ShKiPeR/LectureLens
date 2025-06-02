package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.KeyWord;
import com.neymark.LectureLens.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {

    List<KeyWord> findAllBySubject(Subject subject);
    List<KeyWord> findTop10BySubjectOrderByFrequencyDesc(Subject subject);

}
