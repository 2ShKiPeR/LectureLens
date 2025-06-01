package com.neymark.LectureLens.repositories;

import com.neymark.LectureLens.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
