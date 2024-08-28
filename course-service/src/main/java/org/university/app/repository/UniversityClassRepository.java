package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.university.app.model.UniversityClass;
import reactor.core.publisher.Mono;

public interface UniversityClassRepository extends ReactiveMongoRepository<UniversityClass, String> {
    Mono<UniversityClass> findByStudentIdsContaining(String studentId);
    Mono<UniversityClass> findByClassTeacherId(String teacherId);
}
