package com.kemalakcicek.e_learning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.DtoCourse;
import com.kemalakcicek.e_learning.dto.DtoLesson;
import com.kemalakcicek.e_learning.model.Lesson;
import com.kemalakcicek.e_learning.repository.LessonRepository;
import com.kemalakcicek.e_learning.service.ILessonService;

@Service
public class LessonServiceImpl implements ILessonService {

	@Autowired
	private LessonRepository lessonRepository;

	public List<DtoLesson> convertListLesson(List<Lesson> lessonList) {

		List<DtoLesson> dtoLessons = new ArrayList<>();

		for (Lesson temp : lessonList) {

			DtoLesson dtoLesson = new DtoLesson();

			BeanUtils.copyProperties(temp, dtoLesson);

			DtoCourse dtoCourse = new DtoCourse();

			BeanUtils.copyProperties(temp.getCourse(), dtoCourse);

			dtoLesson.setCourse(dtoCourse);

			dtoLessons.add(dtoLesson);

		}

		return dtoLessons;
	}

	@Override
	public List<DtoLesson> findAllLessons() {

		List<Lesson> lessonList = lessonRepository.findAll();

		return convertListLesson(lessonList);
	}

	@Override
	public Page<Lesson> findAllLesson(Pageable pageable) {

		return lessonRepository.findAll(pageable);
	}

	@Override
	public List<DtoLesson> toDtoList(List<Lesson> lessons) {

		List<DtoLesson> listDtoLessons = new ArrayList<>();

		for (Lesson temp : lessons) {

			DtoLesson dtoLesson = new DtoLesson();
			DtoCourse dtoCourse = new DtoCourse();

			BeanUtils.copyProperties(temp, dtoLesson);
			BeanUtils.copyProperties(temp.getCourse(), dtoCourse);

			dtoLesson.setCourse(dtoCourse);

			listDtoLessons.add(dtoLesson);

		}

		return listDtoLessons;
	}

}
