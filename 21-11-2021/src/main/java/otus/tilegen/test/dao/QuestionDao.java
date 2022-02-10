package otus.tilegen.test.dao;

import otus.tilegen.test.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestionsFromCsv();

}
