import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		int audience = 400;
        String name=JOptionPane.showInputDialog(null,"Enter Name");

        Game game = new Game(name, audience);
        JGame frame = new JGame(game);
        frame.setSize(530, 200);
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int numberOfQuestions = game.getNumberOfQuestions();

		for(int a = 0; a < numberOfQuestions; a++) {
			game.setCurrentQuestionNumber(a);
			game.createHints();
			frame.setFrame(game.getCurrentQuestionNumber());

			while(!frame.updateJGame) {
				if(frame.isCloseJGame()) {
					break;
				}
			}
			System.out.println(a);
		}
		frame.winGame();
	   
	}
}
