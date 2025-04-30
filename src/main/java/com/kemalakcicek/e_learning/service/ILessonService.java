package com.kemalakcicek.e_learning.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.e_learning.dto.DtoLesson;
import com.kemalakcicek.e_learning.model.Lesson;

public interface ILessonService {

	public List<DtoLesson> findAllLessons();

	public Page<Lesson> findAllLesson(Pageable pageable);

	public List<DtoLesson> toDtoList(List<Lesson> lessons);

}
