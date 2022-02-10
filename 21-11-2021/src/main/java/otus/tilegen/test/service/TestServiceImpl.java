package otus.tilegen.test.service;

import otus.tilegen.test.dao.QuestionDao;
import otus.tilegen.test.domain.Option;
import otus.tilegen.test.domain.Question;

import java.util.List;

public class TestServiceImpl implements TestService {

    private QuestionDao questionDao;

    TestServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void printTest() {
        System.out.println("Otus Test");
        for (Question question: getQuestions() ) {
            System.out.println("№" + question.getId() +" " + question.getQuestionText());
            System.out.println("options:");
            for (Option option: question.getOptions()) {
                System.out.println(option.getOptionText() + " answer is right " + (option.isRightAnswer() ? "YES" : "NO") );
            }
        }
    }

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestionsFromCsv();
    }
}
