package otus.tilegen.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import otus.tilegen.test.dao.QuestionDaoImpl;
import otus.tilegen.test.domain.Question;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImplTest {

    @Mock
    private QuestionDaoImpl questionDao;

    @InjectMocks
    private TestServiceImpl testService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

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
