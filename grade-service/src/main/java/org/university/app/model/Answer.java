package org.university.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.university.app.model.questions.TestTask;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private String id;
    private String studentId;
    private Task task;
    private LocalDateTime startedAT;
    private LocalDateTime finishedAt;
    private int mark;
    private Set<TestTask> testing;
    private StudentAnswers answers;
    private List<String> includeFilesUrls;
    private Comment comment;
}
