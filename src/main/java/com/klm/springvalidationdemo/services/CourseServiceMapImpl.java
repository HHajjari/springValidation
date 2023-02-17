package com.klm.springvalidationdemo.services;

import com.klm.springvalidationdemo.exceptions.BusinessException;
import com.klm.springvalidationdemo.exceptions.NoSuchElementFoundException;
import com.klm.springvalidationdemo.model.Course;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseServiceMapImpl implements CourseService {

    Map<Integer, Course> list = new HashMap<>();
    @Override
    public void createCourse(Course course){
        if(!course.getLevel().equals("basic") && !course.getLevel().equals("intermediate") && !course.getLevel().equals("advanced"))
            throw new BusinessException("invalid course type");
        course.setId(list.size() + 1);
        list.put(course.getId(), course);
    }

    @Override
    public Course getCourse(Integer id){
        Course course = list.get(id);
        if(course == null)
            throw new NoSuchElementFoundException("Course not found");
        return course;
    }

    @Override
    public void updateCourse(Integer id, Course course){
        Course existingCourse = list.get(id);
        if(existingCourse == null)
            throw new NoSuchElementFoundException("Course not found");
        existingCourse.setTitle(course.getTitle());
        existingCourse.setLevel(course.getLevel());
        existingCourse.setConstructorEmail(course.getConstructorEmail());
    }

    @Override
    public void deleteCourse(Integer id){
        Course course = list.get(id);
        if(course == null)
            throw new NoSuchElementFoundException("Course not found");
        list.remove(id);
    }
}
