package com.kemalakcicek.e_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.e_learning.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
