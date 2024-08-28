package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.university.app.model.Course;
import org.university.app.model.UniversityClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
    Flux<Course> findAllByClassesContaining(UniversityClass universityClass);
    Flux<Course> findAllByLectorId(String lectorId);
    Mono<Course> findByCourseName(String courseName);
}
