package questions;

import java.util.List;

public class MultipleChoice extends AbstractQuestion{
    private final String correctAnswer;
    private final List<String> options;


    public MultipleChoice(String question,String correctAnswer, List<String> options) {
        super(question);
        this.correctAnswer = correctAnswer;
        this.options = options;
    }


    @Override
    public String answer(String answer) {
        return null;
    }

    @Override
    public int compareTo(Question o) {
        if (o instanceof AbstractQuestion) {
            AbstractQuestion aQuestion = (AbstractQuestion) o;
            return aQuestion.compareToMultipleChoice(this);
        }
        return 1;
    }

    @Override
    public int compareToMultipleChoice(Question o) {
        return question.compareTo(o.getText());
    }
}
