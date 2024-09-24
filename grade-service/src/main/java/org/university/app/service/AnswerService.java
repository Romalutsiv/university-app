package org.university.app.service;

import org.university.app.model.Answer;
import org.university.app.model.questions.StudentTests;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AnswerService {
    Flux<Answer> getTaskAnswers(String taskId);
    Mono<Answer> matchTests(String taskId, List<StudentTests> answers);
}
