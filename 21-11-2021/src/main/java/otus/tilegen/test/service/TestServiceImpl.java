package otus.tilegen.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.tilegen.test.dao.QuestionDao;
import otus.tilegen.test.domain.Option;
import otus.tilegen.test.domain.Question;
import otus.tilegen.test.domain.Student;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class TestServiceImpl implements TestService {

    private QuestionDao questionDao;
    @Value("${success.answer.count}")
    private Integer rightAnswerForSuccess;




    TestServiceImpl(@Autowired QuestionDao questionDao) {
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

    @Override
    public void quiz() {

        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("Welcome to quiz");
        printStream.println("Enter student first name =");
        student.setFirstName(scanner.nextLine());
        printStream.println("Enter student Second name =");
        student.setSecondName(scanner.nextLine());
        printStream.println("Let go. Right answer separate by comma ','");
        int rightAnswerCount = 0;
        for (Question question: getQuestions()) {
            printStream.println(question.getQuestionText());
            List<Integer> rightAnswers = new ArrayList<>();
            for (Option option: question.getOptions()) {
                printStream.println("№" + (question.getOptions().indexOf(option)+1) + " - " + option.getOptionText());
                if(option.isRightAnswer()) {
                    rightAnswers.add(question.getOptions().indexOf(option)+1);
                }
            }
            rightAnswerCount += checkAnswers(rightAnswers, scanner.nextLine(), ',');
        }
        printStream.println(student.getFirstName() + " " + student.getSecondName() + "you are " + (this.rightAnswerForSuccess<= rightAnswerCount? "success" : "failure") + " passed");
    }

    @Override
    public int checkAnswers(List<Integer> rightAnswers, String answersString, char separator) {
        List<Integer> answers = Arrays.stream(answersString.split(String.valueOf(separator)))
                .map(n -> Integer.parseInt(n.trim())).sorted().collect(Collectors.toList());

        if(rightAnswers.stream().sorted().collect(Collectors.toList()).equals(answers)) {
           return 1;
        }
        return 0;
    }
}
