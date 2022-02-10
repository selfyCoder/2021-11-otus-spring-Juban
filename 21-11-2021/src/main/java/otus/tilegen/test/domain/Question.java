package otus.tilegen.test.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Question {

    private String questionText;
    private int id;
    private List<Option> options;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(questionText, question.questionText) && Objects.equals(options, question.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, id, options);
    }
}
