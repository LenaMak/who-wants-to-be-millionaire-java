
public class Question {
	private String questionName;
    private int numberOfAnswers;

    public Answer[] answers;
    private int rightAnswerNumber;
	private String hintFriendText;
	private int cost;

	public int getCost() {
		return cost;
	}

	public String getQuestion() {
		return questionName;
	}

	public void setHintFriend(String hintFriend) {
		this.hintFriendText=hintFriend;
	}

	public String getHintFriend() {
		return hintFriendText;
	}

	public Question(String qN, Answer[] answers, String hintFriend, int cost) {
		this.questionName=qN;
		this.answers=answers;
		this.numberOfAnswers = answers.length;
		this.hintFriendText=hintFriend;
		this.cost = cost;
		this.rightAnswerNumber = calculateRightAnswerNumber();
	}

    public int getRightAnswerNumber() {
        return rightAnswerNumber;
    }


    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public int calculateRightAnswerNumber() {
		int rightAnswerNumber = -1;
		for (int i = 0; i < numberOfAnswers; i++) {
			if (this.answers[i].getRightAnswer() == true){
				rightAnswerNumber = i;
				break;
			}
		}
		return rightAnswerNumber;
	}


}
