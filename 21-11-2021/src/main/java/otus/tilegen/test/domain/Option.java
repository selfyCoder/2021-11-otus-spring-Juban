package otus.tilegen.test.domain;

import java.util.Objects;

public class Option {

    private String optionText;
    private int idQuestion;
    private boolean rightAnswer;

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return idQuestion == option.idQuestion && rightAnswer == option.rightAnswer && Objects.equals(optionText, option.optionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionText, idQuestion, rightAnswer);
    }
}
