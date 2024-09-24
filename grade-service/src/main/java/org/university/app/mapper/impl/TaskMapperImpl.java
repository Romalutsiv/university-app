package org.university.app.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.university.app.dto.TaskRequest;
import org.university.app.dto.TaskResponse;
import org.university.app.mapper.TaskMapper;
import org.university.app.model.AnswerType;
import org.university.app.model.Task;
import org.university.app.model.TaskStatus;
import org.university.app.util.DateTimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {
    private final DateTimeUtil dateTimeUtil;
    @Override
    public Mono<Task> toTask(Mono<TaskRequest> request, String courseId) {
        return request.map(req -> {
            Task task = new Task();
            task.setTaskName(req.taskName());
            task.setTeacherId(req.teacherId());
            task.setIncludeFilesUrls(req.includeFilesUrls());
            LocalDateTime closedDate = dateTimeUtil.stringToLocalDateTime(req.closedDate());
            task.setClosedDate(closedDate);
            task.setCreatedDate(LocalDateTime.now());
            task.setAnswerType(AnswerType.valueOf(req.answerType()));
            task.setTaskStatus(TaskStatus.CREATED);
            task.setTimeToCompleteInSec(req.timeToCompleteInSec());
            task.setCourseId(courseId);
            return task;
        });
    }

    @Override
    public Flux<TaskResponse> toTaskResponseFlux(Flux<Task> task) {
        return task.
                flatMap(t -> toTaskResponseMono(Mono.just(t)));
    }

    @Override
    public Mono<TaskResponse> toTaskResponseMono(Mono<Task> task) {
        return task.map(t -> new TaskResponse(t.getId(), t.getTaskName(), t.getIncludeFilesUrls(), dateTimeUtil.dateTimeToStr(t.getCreatedDate()), dateTimeUtil.dateTimeToStr(t.getUpdatedDate()), dateTimeUtil.dateTimeToStr(t.getClosedDate()), t.getAnswerType().name(), t.getTaskStatus().name() ));
    }
}
