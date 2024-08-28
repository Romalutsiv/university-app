package org.university.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.app.model.Course;
import org.university.app.model.UniversityClass;
import org.university.app.repository.CourseRepository;
import org.university.app.service.CourseService;
import org.university.app.service.UniversityClassService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UniversityClassService universityClassService;
    @Override
    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Flux<Course> findAllStudentCourse(String studentId) {
        return universityClassService.findOneByStudent(studentId)
                .flatMapMany(courseRepository::findAllByClassesContaining);
    }

    @Override
    public Mono<Course> findOneById(String id) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Course with id: " + id + " not found!")));
    }

    @Override
    public Mono<Course> findOneByCourseName(String name) {
        return courseRepository.findByCourseName(name)
                .switchIfEmpty(Mono.error(new RuntimeException("Course with name: " + name + " not found")));
    }

    @Override
    public Flux<Course> findOneByLectorId(String id) {
        return courseRepository.findAllByLectorId(id);
    }

    @Override
    public Mono<Course> createNewCourse(Course courseDto) {
        return null;
    }

    @Override
    public Mono<Course> updateExistCourse(String id, Course courseDto) {
        return null;
    }

    @Override
    public Mono<Void> deleteCourse(String id) {
        return findOneById(id)
                .flatMap(courseRepository::delete);
    }
}
