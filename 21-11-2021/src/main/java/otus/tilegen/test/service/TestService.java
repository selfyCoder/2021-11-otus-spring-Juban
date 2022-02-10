package otus.tilegen.test.service;

import otus.tilegen.test.domain.Question;

import java.util.List;

public interface TestService {

    void printTest();
    List<Question> getQuestions();

}
