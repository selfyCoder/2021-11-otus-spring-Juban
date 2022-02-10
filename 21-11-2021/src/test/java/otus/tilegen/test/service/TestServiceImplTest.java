package otus.tilegen.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import otus.tilegen.test.dao.QuestionDao;
import otus.tilegen.test.domain.Question;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private TestServiceImpl testService;

    @Test
    public void whenGetQuestions() {
        Question question = new Question();
        question.setId(1);
        question.setQuestionText("Some text");
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        when(questionDao.getQuestionsFromCsv()).thenReturn(questions);
        List<Question> questionsFromService = testService.getQuestions();
        assertEquals(questions.get(0), questionsFromService.get(0));
    }
}
