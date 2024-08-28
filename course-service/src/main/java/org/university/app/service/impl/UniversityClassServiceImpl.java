package org.university.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.app.model.UniversityClass;
import org.university.app.repository.UniversityClassRepository;
import org.university.app.service.UniversityClassService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class UniversityClassServiceImpl implements UniversityClassService {
    private final UniversityClassRepository universityClassRepository;
    @Override
    public Flux<UniversityClass> findAll() {
        return universityClassRepository.findAll();
    }

    @Override
    public Mono<UniversityClass> findOneById(String id) {
        return universityClassRepository.findById(id).switchIfEmpty(
                Mono.error(new RuntimeException("Class with id: " + id + " not found!"))
        );
    }

    @Override
    public Mono<UniversityClass> findOneByStudent(String studentId) {
        return universityClassRepository.findByStudentIdsContaining(studentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Class in which the student with id: " + studentId + " is enrolled not found")));
    }

    @Override
    public Mono<UniversityClass> findOneByClassTeacher(String classTeacherId) {
        return universityClassRepository.findByClassTeacherId(classTeacherId)
                .switchIfEmpty(Mono.error(new RuntimeException("Present teacher don't have any classes!")));
    }

    @Override
    public Mono<UniversityClass> createNewClass(UniversityClass universityClass) {
        return null;
    }

    @Override
    public Mono<UniversityClass> updateExistClass(String id, UniversityClass universityClass) {
        return null;
    }

    @Override
    public Mono<Void> deleteClass(String id) {
        return findOneById(id)
                .flatMap(universityClassRepository::delete);
    }
}
