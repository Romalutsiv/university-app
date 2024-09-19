package org.university.app.mapper.impl;

import org.springframework.stereotype.Component;
import org.university.app.dto.TaskRequest;
import org.university.app.mapper.TaskMapper;
import org.university.app.model.AnswerType;
import org.university.app.model.Task;
import org.university.app.model.TaskStatus;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Mono<Task> toTask(Mono<TaskRequest> request, String courseId) {
        return request.map(req -> {
            Task task = new Task();
            task.setTaskName(req.taskName());
            task.setTeacherId(req.teacherId());
            task.setIncludeFilesUrls(req.includeFilesUrls());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime closedDate = LocalDateTime.parse(req.closedDate(), formatter);
            task.setClosedDate(closedDate);
            task.setCreatedDate(LocalDateTime.now());
            task.setAnswerType(AnswerType.valueOf(req.answerType()));
            task.setTaskStatus(TaskStatus.CREATED);
            task.setTimeToCompleteInSec(req.timeToCompleteInSec());
            task.setCourseId(courseId);
            return task;
        });
    }
}
