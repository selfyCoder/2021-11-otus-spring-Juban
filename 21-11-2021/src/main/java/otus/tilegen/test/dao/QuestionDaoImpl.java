package otus.tilegen.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import otus.tilegen.test.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@PropertySource("classpath:config.properties")
public class QuestionDaoImpl implements QuestionDao{
    private OptionDao optionDao;
    private String questionFileName;

    public QuestionDaoImpl(@Value("${question.file.name}") String questionFileName, @Autowired OptionDao optionDao) {
        this.optionDao = optionDao;
        this.questionFileName = questionFileName;
    }

    @Override
    public List<Question> getQuestionsFromCsv() {
        Resource resource = new ClassPathResource(questionFileName);
        List<Question> questions = new ArrayList<>();
        try (Scanner scanner = new Scanner(resource.getFile())) {
            if(scanner.hasNext()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                questions.add(getQuestionFromLine(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private Question getQuestionFromLine(String line) throws IOException {
        Question question = new Question();
        String[] values = line.split(";");
        if(values.length == 2) {
            question.setQuestionText(values[0]);
            question.setId(Integer.parseInt(values[1]));
        }
        else {
            throw new IOException("question reading failed");
        }
        question.setOptions(optionDao.getTestOptions(question.getId()));
        return question;
    }


}
