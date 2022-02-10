package otus.tilegen.test.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import otus.tilegen.test.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoImpl implements QuestionDao{
    private List<Question> questions;
    private OptionDao optionDao;
    @Override
    public List<Question> getQuestionsFromCsv() {
        return questions;
    }

    public QuestionDaoImpl(String questionFileName, OptionDao optionDao) {
           this.optionDao = optionDao;
           init(questionFileName);
    }

    private void init(String questionFileName) {
        Resource resource = new ClassPathResource(questionFileName);
        questions = new ArrayList<>();
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
