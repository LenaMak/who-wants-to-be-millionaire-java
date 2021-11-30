public class Hint50 extends Hint {
    private int numberOfAnswers;

    public Hint50(String hintName, int rightAnswerNumber, int numberOfAnswers) {
        super(hintName);
        this.rightAnswerNumber = rightAnswerNumber;
        this.numberOfAnswers = numberOfAnswers;
        calculateSecondAnswer();
    }

    private void calculateSecondAnswer() {
        //actions to select the second answer to be displayed
        int randomNumber = (int) ((Math.random()*(numberOfAnswers - 1)));
        while(randomNumber == this.rightAnswerNumber) {
            randomNumber = (int) ((Math.random()*(numberOfAnswers - 1)));
        }
        this.secondAnswer = randomNumber;
    }
}
