package org.university.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.app.dto.CourseDto;
import org.university.app.model.Course;
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
    public Mono<Course> createNewCourse(CourseDto courseRequest) {
        return courseRepository.existsByCourseName(courseRequest.courseName())
                .flatMap(exist -> {
                    if (exist) return Mono.error(new RuntimeException("Course with name: " + courseRequest.courseName() + " is already exist!"));
                    else {
                        return Flux.fromIterable(courseRequest.classIds())
                                .flatMap(universityClassService::findOneById)
                                .collectList()
                                .flatMap(universityClasses -> {
                                    Course course = Course.builder()
                                            .courseName(courseRequest.courseName())
                                            .lectorId(courseRequest.lectorId())
                                            .classes(universityClasses)
                                            .build();

                                    return courseRepository.save(course);
                                });
                    }
                });
    }

    @Override
    public Mono<Course> updateExistCourse(String id, CourseDto courseRequest) {
        return findOneById(id)
                .flatMap(existCourse -> {
                    if (!id.equals(existCourse.getId()) && !existCourse.getId().equals(courseRequest.id())){
                        return Mono.error(new RuntimeException("Ids doesn't matches!"));
                    }
                    if (existCourse.getCourseName().equals(courseRequest.courseName())){
                        return update(existCourse, courseRequest);
                    } else {
                        return findOneByCourseName(courseRequest.courseName())
                                .flatMap(course ->
                                {
                                    if (!course.getId().equals(existCourse.getId())) {
                                        return update(existCourse, courseRequest);
                                    } else {
                                        return Mono.error(new RuntimeException("Course with present name is already exist!"));
                                    }
                                });
                    }
                });
    }

    @Override
    public Mono<Void> deleteCourse(String id) {
        return findOneById(id)
                .flatMap(courseRepository::delete);
    }

    private Mono<Course> update(Course existCourse, CourseDto courseDto){
        return Flux.fromIterable(courseDto.classIds())
                .flatMap(universityClassService::findOneById)
                .collectList()
                .flatMap(classes -> {
                    existCourse.setCourseName(courseDto.courseName());
                    existCourse.setLectorId(courseDto.lectorId());
                    existCourse.setClasses(classes);
                    return courseRepository.save(existCourse);
                });
    }

}
