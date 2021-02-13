package questions;

public class TrueFalse extends AbstractQuestion {
    private final String answer;

    public TrueFalse(String question, String answer){
        super(question);
        this.answer = answer;
    }

    @Override
    public String answer(String answer) {
        return null;
    }

    @Override
    public int compareTo(Question o) {
        if (o instanceof AbstractQuestion) {
            AbstractQuestion aQuestion = (AbstractQuestion) o;
            return aQuestion.compareToTrueFalse(this);
        }
        return 1;
    }

    @Override
    public int compareToTrueFalse(Question o) {
        return question.compareTo(o.getText());
    }
}
