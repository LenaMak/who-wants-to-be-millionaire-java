public class Hint {

    protected String hintText;
    protected String hintName;

    protected int rightAnswerNumber = -1;
    protected int secondAnswer = -1 ;


    public String getHintName() {
        return hintName;
    }

    public Hint(String hintName) {
        this.hintName = hintName;
    }


    public int getSecondAnswer() {
        return secondAnswer;
    }
    public String getHintText() {
        return hintText;
    }
    public String getHintText(Game game, int numberOfAnswers, String[] answers) {
        return hintText;
    }
    public int getRightAnswerNumber() {
        return rightAnswerNumber;
    }

}
