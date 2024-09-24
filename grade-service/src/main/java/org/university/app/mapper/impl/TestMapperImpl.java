package org.university.app.mapper.impl;

import org.springframework.stereotype.Component;
import org.university.app.dto.TestRequest;
import org.university.app.dto.TestResponse;
import org.university.app.exception.CreateDocumentException;
import org.university.app.mapper.TestMapper;
import org.university.app.model.Task;
import org.university.app.model.questions.TestAttribute;
import org.university.app.model.questions.TestTask;
import org.university.app.model.questions.TestType;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class TestMapperImpl implements TestMapper {
    @Override
    public Mono<TestTask> toModel(Mono<TestRequest> req, Task task) {
        return req.map(r -> switch (TestType.valueOf(r.testType())){
            case FILL_IN_BLANK -> mapToFillInTheBlankTest(r, task);
            case CHOICE_MANY -> mapToMultipleChoiceTest(r, task);
            case CHOICE_ONE -> mapToOneChoiceTest(r, task);
            case MATCHING -> mapMatchingTest(r, task);
        });
    }

    @Override
    public Mono<TestResponse> toResponseDto(Mono<TestTask> test) {
        return test.map(testTask -> switch (testTask.getTestType()) {
            case MATCHING -> getMatchingTest(testTask);
            case CHOICE_ONE, CHOICE_MANY -> new TestResponse(testTask.getId(), testTask.getTestType().name(), testTask.getQuestion(), null, testTask.getAllAnswers());
            case FILL_IN_BLANK -> new TestResponse(testTask.getId(), testTask.getTestType().name(), testTask.getQuestion(), null, null);
        });
    }
    private TestTask mapToFillInTheBlankTest(TestRequest testRequest, Task task){
        TestTask test = new TestTask();
        test.setQuestion(testRequest.question());
        if (testRequest.attributes().size() == 1){
            testRequest.attributes().forEach(this::checkValidAnswer);
            test.setAttributes(testRequest.attributes());
        } else {
            throw new CreateDocumentException("You must add only ONE correct answer!");
        }
        test.setTestType(TestType.FILL_IN_BLANK);
        test.setTask(task);
        return test;
    }
    private TestTask mapMatchingTest(TestRequest testRequest, Task task){
        TestTask test = new TestTask();
        test.setQuestion(testRequest.question());
        if (testRequest.attributes().size() > 2){
            testRequest.attributes().forEach(this::checkValidOption);
            testRequest.attributes().forEach(this::checkValidAnswer);
            test.setAttributes(testRequest.attributes());
        } else {
            throw new CreateDocumentException("You must add more than TWO pairs of option and question!");
        }
        test.setTestType(TestType.MATCHING);
        test.setTask(task);
        return test;

    }
    private TestTask mapToMultipleChoiceTest(TestRequest testRequest, Task task){
        TestTask test = new TestTask();
        test.setQuestion(testRequest.question());
        if (testRequest.attributes().size() > 2){
            testRequest.attributes().forEach(this::checkValidAnswer);
            test.setAttributes(testRequest.attributes());
        } else {
            throw new CreateDocumentException("You must add more than TWO correct answers!");
        }
        if (testRequest.allAnswers().size() < 2){
            throw new CreateDocumentException("You must add more than TWO incorrect answers!");
        } else if (testRequest.allAnswers().size() < testRequest.attributes().size()){
            throw new CreateDocumentException("Count of incorrect answers must be more than count of correct answers!");
        } else {
            testRequest.allAnswers().forEach(this::checkValidAnswerStr);
            test.setAllAnswers(testRequest.allAnswers());
        }
        test.setTestType(TestType.CHOICE_MANY);
        test.setTask(task);
        return test;

    }
    private TestTask mapToOneChoiceTest(TestRequest testRequest, Task task){
        TestTask test = new TestTask();
        test.setQuestion(testRequest.question());
        if (testRequest.attributes().size() == 1){
            testRequest.attributes().forEach(this::checkValidAnswer);
            test.setAttributes(testRequest.attributes());
        } else {
            throw new CreateDocumentException("You must add only ONE correct answer!");
        }
        if (testRequest.allAnswers().size() < 2){
            throw new CreateDocumentException("You must add more than TWO incorrect answers!");
        } else {
            testRequest.allAnswers().forEach(this::checkValidAnswerStr);
            test.setAllAnswers(testRequest.allAnswers());
        }
        test.setTestType(TestType.CHOICE_ONE);
        test.setTask(task);
        return test;
    }
    private TestResponse getMatchingTest(TestTask testTask){
        List<String> options = new ArrayList<>(testTask.getAttributes().stream().map(TestAttribute::getOption).toList());
        List<String> answers = new ArrayList<>(testTask.getAttributes().stream().map(TestAttribute::getAnswer).toList());
        Collections.shuffle(options);
        Collections.shuffle(answers);
        return new TestResponse(testTask.getId(), testTask.getTestType().name(), testTask.getQuestion(), options, answers);
    }


    private void checkValidOption(TestAttribute testAttribute){
        if (testAttribute.getOption() == null || testAttribute.getOption().isEmpty() || testAttribute.getOption().isBlank()){
            throw new CreateDocumentException("Option cant be empty!");
        }
    }
    private void checkValidAnswer(TestAttribute testAttribute){
        if (testAttribute.getAnswer() == null || testAttribute.getAnswer().isEmpty() || testAttribute.getAnswer().isBlank()){
            throw new CreateDocumentException("Answer cant be empty!");
        }
    }
    private void checkValidAnswerStr(String answer){
        if (answer == null || answer.isEmpty() || answer.isBlank()){
            throw new CreateDocumentException("Answer cant be empty!");
        }
    }
}
