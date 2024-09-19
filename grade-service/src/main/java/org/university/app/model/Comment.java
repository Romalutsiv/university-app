package org.university.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String authorId;
    private String title;
    private String text;
    private Comment answer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
