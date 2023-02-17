package com.klm.springvalidationdemo.web;

import com.klm.springvalidationdemo.model.Course;
import com.klm.springvalidationdemo.services.CourseServiceJpaImpl;
import com.klm.springvalidationdemo.services.CourseServiceMapImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceJpaImpl courseService;
    @PostMapping("")
    public ResponseEntity createCouese(@RequestBody @Valid Course course){
        courseService.createCourse(course);
        return ResponseEntity.created(URI.create("/course/" + course.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCoueseById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.getCourse(id));
    }
}
