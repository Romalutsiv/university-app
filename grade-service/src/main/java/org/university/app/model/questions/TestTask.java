package org.university.app.model.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.university.app.model.Task;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestTask {
    private String id;
    private String question;
    private Task task;
}
