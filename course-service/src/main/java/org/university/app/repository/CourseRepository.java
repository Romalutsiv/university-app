package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.university.app.model.Course;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
}
