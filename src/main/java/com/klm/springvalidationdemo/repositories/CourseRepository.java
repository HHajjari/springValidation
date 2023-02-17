package com.klm.springvalidationdemo.repositories;

import com.klm.springvalidationdemo.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

}
