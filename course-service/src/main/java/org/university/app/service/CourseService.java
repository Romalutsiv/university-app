package org.university.app.service;

import org.university.app.dto.CourseDto;
import org.university.app.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<Course> findAll();
    Flux<Course> findAllStudentCourse(String studentId);
    Mono<Course> findOneById(String id);
    Mono<Course> findOneByCourseName(String name);
    Flux<Course> findOneByLectorId(String id);
    Mono<Course> createNewCourse(CourseDto courseRequest);
    Mono<Course> updateExistCourse(String id, CourseDto courseRequest);
    Mono<Void> deleteCourse(String id);


}
