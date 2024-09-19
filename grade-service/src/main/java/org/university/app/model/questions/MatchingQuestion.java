package org.university.app.model.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchingQuestion extends TestTask{
    private Map<String, String> optionsAndAnswers;
}
