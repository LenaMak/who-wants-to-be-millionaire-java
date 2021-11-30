import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JGame extends JFrame implements ActionListener, ItemListener{
	private JLabel questionlabel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel hintLabel;
    private  JLabel label;
    boolean closeJGame= false;
    boolean updateJGame = false;

	public boolean isCloseJGame() {
		return closeJGame;
	}

	private JCheckBox hintbox1;
	private JCheckBox hintbox2;
	private JCheckBox hintbox3;
	private ButtonGroup hintgroup;
	private Question question;
	
	private JLabel continueGame;
	private JButton buttonGame; 
	private JButton buttonMoney;
	private JButton buttonExit;
    private JLabel decision;

    private Game game;

    // constructor
	public JGame(Game game) {
		super("Who wants to be a millionaire?");
        this.game = game;
        setQuestionFrame();
    }

    public void setQuestionFrame() {
    	 // extra labels and buttons
        questionlabel = new JLabel();
        label = new JLabel(); //displayed when user lost YOU LOSE
        decision = new JLabel();
        continueGame = new JLabel();
        hintLabel = new JLabel();

        add(questionlabel);
        add(label);
        add(continueGame);

        questionlabel.setFont(new Font("Arial", Font.ITALIC, 16));
        setLayout(new FlowLayout());

        // everything for answer buttons
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        add(button1);
        add(button2);
        add(button3);
        add(button4);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        
        // hintboxes
        hintbox1 = new JCheckBox();
        hintbox2 = new JCheckBox();
        hintbox3 = new JCheckBox(); 
        hintgroup = new ButtonGroup();

        hintgroup.add(hintbox1);
        hintgroup.add(hintbox2);
        hintgroup.add(hintbox3);

        hintbox1.addItemListener(this);
        hintbox2.addItemListener(this);
        hintbox3.addItemListener(this);

        add(hintbox1);
        add(hintbox2);
        add(hintbox3);
        
        add(hintLabel);
        add(decision);

        //some extra buttons
        buttonGame = new JButton("Continue the game");
        buttonMoney = new JButton("Take money");
        buttonExit = new JButton("Exit the game");

        add(buttonGame);
        add(buttonMoney);
        add(buttonExit);

        buttonGame.addActionListener(this);
        buttonMoney.addActionListener(this);
        buttonExit.addActionListener(this);

    }
	public void setFrame(int questionNumber) {

        this.question = game.getQuestions()[questionNumber];
        System.out.println(question.answers[0].getAnswerName());
        questionlabel.setText(question.getQuestion());
        button1.setText("A. "+question.answers[0].getAnswerName());
        button2.setText("B. "+question.answers[1].getAnswerName());
        button3.setText("C. "+question.answers[2].getAnswerName());
        button4.setText("D. "+question.answers[3].getAnswerName());

        decision.setVisible(true);

        continueGame.setVisible(false);
        buttonGame.setVisible(false);
        buttonMoney.setVisible(false);

        button1.setBackground(null);
        button2.setBackground(null);
        button3.setBackground(null);
        button4.setBackground(null);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        hintbox1.setText(game.getHints()[0].getHintName());
        hintbox2.setText(game.getHints()[1].getHintName());
        hintbox3.setText(game.getHints()[2].getHintName());

        hintbox1.setEnabled(game.isActiveHint1());
        hintbox2.setEnabled(game.isActiveHint2());
        hintbox3.setEnabled(game.isActiveHint3());

        hintLabel.setVisible(false);

        this.updateJGame = false;

    }
	public void itemStateChanged(ItemEvent check) {
        Object source = check.getItem();
        if ((source == hintbox1) && (hintbox1.isSelected())) {
        	
            //50:50 hint
            hintLabel.setText(game.getHints()[0].getHintText());

            int firstGoodNumberAnswer = game.getHints()[0].getRightAnswerNumber();
            int secondGoodNumberAnswer = game.getHints()[0].getSecondAnswer();

            if(firstGoodNumberAnswer !=0 && secondGoodNumberAnswer !=0) {
            	button1.setEnabled(false);	
            }
            if (firstGoodNumberAnswer != 0 && secondGoodNumberAnswer != 0) button1.setEnabled(false);
            if (firstGoodNumberAnswer != 1 && secondGoodNumberAnswer != 1) button2.setEnabled(false);
            if (firstGoodNumberAnswer != 2 && secondGoodNumberAnswer != 2) button3.setEnabled(false);
            if (firstGoodNumberAnswer != 3 && secondGoodNumberAnswer != 3) button4.setEnabled(false);

            hintbox1.setEnabled(false);
            game.setActiveHint1(false); //makes hint unavailable


        } else if ((source == hintbox2) && (hintbox2.isSelected())) {
        	
            //friend hint
            hintLabel.setText(game.getHints()[1].getHintText());
            hintLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            hintLabel.setVisible(true);
            hintbox2.setEnabled(false);
            game.setActiveHint2(false);
        } else if ((source == hintbox3) && (hintbox3.isSelected())) {
        	
            //audience hint
            String[] buttonText = new String[4];
            	
            // 4 expressions with ternary operators instead of
            //traditional if...else statements
            //gets text from all buttons, if the button is not active, empty text is assigned
            //transfers this info to hint and later displays on the screen
            buttonText[0] = button1.isEnabled()? button1.getText() : "";            
            buttonText[1] = button2.isEnabled()? button2.getText() : "";
            buttonText[2] = button3.isEnabled()? button3.getText() : "";
            buttonText[3] = button4.isEnabled()? button4.getText() : "";
            
            int numberOfAnswers = (button1.isEnabled()? 1: 0) + (button2.isEnabled()? 1: 0) + (button3.isEnabled()? 1: 0) + (button4.isEnabled()? 1: 0);
            String[] answers = new String[numberOfAnswers];
            int j = 0;
            for(int i = 0; i < 4; i++) {
                if(buttonText[i] != "") {
                    answers[j] = buttonText[i];
                    j++;
                }
            }           
            String hintResult = game.getHints()[2].getHintText(game, numberOfAnswers, answers);
            hintLabel.setFont(new Font("Arial", Font.ITALIC, 11));
            hintLabel.setText(hintResult);
            hintLabel.setVisible(true);
            hintbox3.setEnabled(false);

            game.setActiveHint3(false);
        }
    }

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		boolean correct=false;
        int rightNumber = question.getRightAnswerNumber();
        
        //turns selected answer button red or green and finds the correct answer
		if(source == button1 && rightNumber == 0 ) {
			button1.setBackground(Color.GREEN);
			correct = true;
		} else if(source == button1 && rightNumber != 0) {
			button1.setBackground(Color.RED);
		} else if(source == button2 && rightNumber == 1) {
			button2.setBackground(Color.GREEN);
			correct =true;
		} else if(source == button2 && rightNumber != 1) {
			button2.setBackground(Color.RED);
		} else if(source == button3 && rightNumber == 2) {
			button3.setBackground(Color.GREEN);
			correct =true;
		} else if(source == button3 && rightNumber != 2) {
			button3.setBackground(Color.RED);
		} else if(source == button4 && rightNumber == 3) {
			button4.setBackground(Color.GREEN);
			correct =true;
		} else if(source == button4 && rightNumber != 3) {
			button4.setBackground(Color.RED);
		}
		//executes if the correct answer has been selected
		if((source == button1 || source == button2 || source == button3 || source == button4 ) && correct) {
            game.updatePrize();
            buttonMoney.setVisible(true);
            buttonGame.setVisible(true);
            decision.setVisible(true);
            
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);

            hintbox1.setEnabled(false);
            hintbox2.setEnabled(false);
            hintbox3.setEnabled(false);
            
            //executes if the wrong answer has been selected
        } else if((source == button1 || source == button2 || source == button3 || source == button4 )&& !correct) {
            label.setText("You lost");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(Color.RED);
            label.setVisible(true);

            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);

            hintbox1.setEnabled(false);
            hintbox2.setEnabled(false);
            hintbox3.setEnabled(false);
        }

		//executes when user chooses to take money
		if (source == buttonMoney) {
			decision.setText("Thank you for playing, " + game.getUserName() + "! Here is your money: $" +
                    game.getPrize());
			decision.setVisible(true);
			buttonGame.setVisible(false);
            buttonMoney.setVisible(false);
            System.out.println("money");
         //executes if the user decides to continue the game
		} else if (source == buttonGame) {
			decision.setVisible(true);
			System.out.println("game");
            updateJGame = true;
		} else if (source == buttonExit) {
            dispose();
            closeJGame = true;
        }
	}
	//displayed at the end of the game in case of victory
	public void winGame() {
        decision.setText("Congratulations, " + game.getUserName() + "! You won $" +
                game.getPrize() + "! Thank you for playing!");

        decision.setVisible(true);
        
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);

        questionlabel.setVisible(false);
        buttonGame.setVisible(false);
        buttonMoney.setVisible(false);

    }
} 