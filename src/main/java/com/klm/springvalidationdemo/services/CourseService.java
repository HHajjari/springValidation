package com.klm.springvalidationdemo.services;

import com.klm.springvalidationdemo.model.Course;

public interface CourseService {
    void createCourse(Course course);

    Course getCourse(Integer id);

    void updateCourse(Integer id, Course course);

    void deleteCourse(Integer id);
}
