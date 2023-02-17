package com.klm.springvalidationdemo.services;

import com.klm.springvalidationdemo.exceptions.BusinessException;
import com.klm.springvalidationdemo.exceptions.NoSuchElementFoundException;
import com.klm.springvalidationdemo.model.Course;
import com.klm.springvalidationdemo.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceJpaImpl implements CourseService {

    private final CourseRepository courseRepository;
    @Override
    public void createCourse(Course course){
        if(!course.getLevel().equals("basic") &&
                !course.getLevel().equals("intermediate") &&
                !course.getLevel().equals("advanced"))
            throw new BusinessException("invalid course type");
        courseRepository.save(course);
    }

    @Override
    public Course getCourse(Integer id){
        return courseRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Course not found"));
    }

    @Override
    public void updateCourse(Integer id, Course course){
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Course not found"));
        existingCourse.setTitle(course.getTitle());
        existingCourse.setLevel(course.getLevel());
        existingCourse.setConstructorEmail(course.getConstructorEmail());
        courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Integer id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Course not found"));
        courseRepository.delete(course);
    }
}
