package org.university.app.service;

import org.university.app.model.UniversityClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UniversityClassService {
    Flux<UniversityClass> findAll();
    Mono<UniversityClass> findOneById(String id);
    Mono<UniversityClass> findOneByStudent(String studentId);
    Mono<UniversityClass> findOneByClassTeacher(String classTeacherId);
    Mono<UniversityClass> createNewClass(UniversityClass universityClass);
    Mono<UniversityClass> updateExistClass(String id, UniversityClass universityClass);
    Mono<Void> deleteClass(String id);

}
