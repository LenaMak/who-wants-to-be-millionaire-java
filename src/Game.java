public class Game {
    private String userName;
    private Question[] questions;

    public Hint[] getHints() {
        return hints;
    }

    private Hint[] hints;
    private boolean activeHint1 = true;
    private boolean activeHint2 = true;
    private boolean activeHint3 = true;

    private int prize;

    private int audience;
    private int numberOfQuestions;

    public void setCurrentQuestionNumber(int currentQuestion) {
        this.currentQuestionNumber = currentQuestion;
    }

    private int currentQuestionNumber;

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public int getAudience() {
        return audience;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Game (String name, int audience) {
        if(name.isEmpty()) {
        	this.userName = "User";
        } else {
        	this.userName = name;
        }
    //	this.userName = (name.isEmpty()) ? "User" : name; 
        this.audience = audience;
        createQuestions();
    }

    public boolean isActiveHint1() {
        return activeHint1;
    }

    public boolean isActiveHint2() {
        return activeHint2;
    }

    public boolean isActiveHint3() {
        return activeHint3;
    }

    public int getPrize() {
        return prize;
    }

    public void setActiveHint1(boolean activeHint1) {
        this.activeHint1 = activeHint1;
    }

    public void setActiveHint2(boolean activeHint2) {
        this.activeHint2 = activeHint2;
    }

    public void setActiveHint3(boolean activeHint3) {
        this.activeHint3 = activeHint3;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void updatePrize() {
        setPrize(questions[currentQuestionNumber].getCost());
    }
    //Answers to 5 questions
    private void createQuestions() {
        Answer q1a1 = new Answer("Tesla",false);
        Answer q1a2 = new Answer("SpaceX",false);
        Answer q1a3 = new Answer("Walmart",true);
        Answer q1a4 = new Answer("The Boring Company",false);
        Answer[] ques1 = {q1a1, q1a2, q1a3, q1a4};

        Answer q2a1 = new Answer("Eight", true);
        Answer q2a2 = new Answer("Seven",false);
        Answer q2a3 = new Answer("Ten",false);
        Answer q2a4 = new Answer("Six",false);
        Answer[] ques2 = {q2a1, q2a2, q2a3, q2a4};

        Answer q3a1 = new Answer("The Beatles", false);
        Answer q3a2 = new Answer("Queen",true);
        Answer q3a3 = new Answer("U2",false);
        Answer q3a4 = new Answer("Metallica",false);
        Answer[] ques3 = {q3a1, q3a2, q3a3, q3a4};

        Answer q4a1 = new Answer("Canberra", true);
        Answer q4a2 = new Answer("Sydney",false);
        Answer q4a3 = new Answer("Wellington",false);
        Answer q4a4 = new Answer("Melbourne",false);
        Answer[] ques4 = {q4a1, q4a2, q4a3, q4a4};

        Answer q5a1 = new Answer("Anne", false);
        Answer q5a2 = new Answer("Emily",true);
        Answer q5a3 = new Answer("Charlotte",false);
        Answer q5a4 = new Answer("Jane",false);
        Answer[] ques5 = {q5a1, q5a2, q5a3, q5a4};
        
        //5 questions with their cost and friend hint
        Question[] questions = new Question[5];
        Question question1 = new Question("Which of the following companies is not owned by Elon Musk?",
                ques1,"Elon Musk is a tech entrepreneur.", 100);
        questions[0] = question1;

        Question question2 = new Question("What is the number of planets that can be found in the solar system?   ",
                ques2,"Some planets might have lost their status.", 1000);
        questions[1] = question2;

        Question question3 = new Question("Who performed originally the single \"We will rock you\"?",
                ques3, "The band is British.", 10000);
        questions[2] = question3;

        Question question4 = new Question("Which of the following cities is the capital city of Australia?",
                ques4, "The most famous city in the country is not always the capital.", 100000);
        questions[3] = question4;

        Question question5 = new Question("What is the first name of the author of the book \"Wuthering Heights\"?",
                ques5, "This author is best known for her only one novel.", 1000000);
        questions[4] = question5;

        this.questions = questions;
        this.numberOfQuestions = questions.length;
    }

    public String getUserName() {
        return userName;
    }

    public Question[] getQuestions() {
        return questions;
    }

    //create 3 hints and store them in an array
    public void createHints() {
        Hint hint50 = new Hint50("50:50 hint", questions[currentQuestionNumber].getRightAnswerNumber(), questions[currentQuestionNumber].getNumberOfAnswers());
        Hint hintFriend = new HintFriend("Ask friend", questions[currentQuestionNumber].getHintFriend());
        Hint hintAudience = new HintAudience("Ask audience");
        Hint[] hints = {hint50, hintFriend, hintAudience};
        this.hints = hints;
    }

}
