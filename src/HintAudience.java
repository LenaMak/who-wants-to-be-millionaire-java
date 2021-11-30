public class HintAudience extends Hint {
    public HintAudience(String hintName) {
        super(hintName);
    }

    public String calculate(int audienceNumber, int questionNumber, int numberOfQuestions, int answersNumber, int rightAnswerNumber, String[] answers) {
        int randomNumber;

        double[] counters = new double[answersNumber];

        for(int i = 0; i<audienceNumber; i++) {
            randomNumber = (int) (Math.random()*answersNumber);
            counters[randomNumber] ++;
        }

        double defaultCoeff = 1;
        double rightCoeff = defaultCoeff + (numberOfQuestions - questionNumber + 1 + 1.0) / numberOfQuestions / answersNumber;
        double wrongCoeff =   1 - ((rightCoeff - defaultCoeff ) / (answersNumber - 1));
        
        double sum = 0;
        for(int i = 0; i < answersNumber; i++){
            if(i == rightAnswerNumber) {
                counters[i] *= rightCoeff;
            } else {
                counters[i] *= wrongCoeff;
            }
            sum += counters[i];
        }


        int sumM1 = 0;
        for(int i = 0; i < answersNumber-1; i++){
            counters[i] = Math.floor(counters[i]*1.0/sum *audienceNumber);
            sumM1 += counters[i];
        }
        counters[answersNumber - 1] = Math.round(audienceNumber - sumM1);
        String resultString = "";
        //to display vote results
        for(int i = 0; i < answersNumber; i++){
            resultString += answers[i] + ": " + counters[i] + " people ";
        }

        return resultString;
    }


    public String getHintText(Game game, int numberOfAnswers, String[] answers) {
        int audienceNumber = game.getAudience();
        int questionNumber = game.getCurrentQuestionNumber();
        int numberOfQuestions = game.getNumberOfQuestions();
        int answersNumber = numberOfAnswers;
        int rightAnswerNumber = game.getQuestions()[questionNumber].getRightAnswerNumber();
        
        if((answersNumber  == 2) && (rightAnswerNumber != 0)){
       		rightAnswerNumber = 1; 
       	}

        return calculate(audienceNumber, questionNumber, numberOfQuestions, answersNumber, rightAnswerNumber, answers);
    }
}
