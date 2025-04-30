package com.kemalakcicek.e_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.e_learning.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
