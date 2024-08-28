package org.university.app.service;

import org.university.app.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<Course> findAll();
    Flux<Course> findAllStudentCourse(String studentId);
    Mono<Course> findOneById(String id);
    Mono<Course> findOneByLectorId(String id);
    Mono<Course> createNewCourse(Course courseDto);
    Mono<Course> updateExistCourse(String id, Course courseDto);
    Mono<Void> deleteCourse(String id);


}
