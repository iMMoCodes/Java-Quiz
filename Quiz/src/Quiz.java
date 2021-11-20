import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener {
	
	// -------------------- Make Questions --------------------
	String[] questions = {
			"Which company developed Java?",
			"Which year was Java created?",
			"What was Java originally called?",
			"Who is credited with creating Java?"
	};
	
	// -------------------- Give Answer Options --------------------
	String[][] options = {
			{"Sun Microsystems","Starbucks","Microsoft","Alphabet"},
			{"1989","1996","1972","1492"},
			{"Apple","Latte","Oak","Koffing"},
			{"Steve Jobs","Bill Gates","James Gosling","Mark Zuckerberg"}
	};
	
	// -------------------- Right Answers --------------------
	char[] answers = {
			'A',
			'B',
			'C',
			'C'
	};
	
	// -------------------- Variables --------------------
	char guess;
	char answer;
	int index;
	int correctGuesses = 0;
	int totalQuestions = questions.length;
	int result;
	int seconds = 10;
	
	// -------------------- GUI Components --------------------
	JFrame quizFrame = new JFrame();
	JTextField textField = new JTextField();
	JTextArea textArea = new JTextArea();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answerLabelA = new JLabel();
	JLabel answerLabelB = new JLabel();
	JLabel answerLabelC = new JLabel();
	JLabel answerLabelD = new JLabel();
	JLabel timeLabel = new JLabel();
	JLabel secondsLeft = new JLabel();
	JTextField numberRight = new JTextField();
	JTextField percentage = new JTextField();
	
	// -------------------- Countdown Timer  --------------------
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Subtract second every second
			seconds--;
			secondsLeft.setText(String.valueOf(seconds));
			// Display answer when time ends
			if(seconds <= 0) {
				displayAnswer();
			}
		}
	});
	
	
	// -------------------- Constructor --------------------
	public Quiz() {
		quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quizFrame.setSize(650,650);
		quizFrame.getContentPane().setBackground(new Color(50,50,50));
		quizFrame.setTitle("Quiz App");
		quizFrame.setLayout(null);
		quizFrame.setResizable(false);
		
		// -------------------- Question Number --------------------
		textField.setBounds(0,0,650,50);
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free",Font.BOLD,30));
		textField.setBorder(BorderFactory.createBevelBorder(1));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);
		
		// -------------------- Question Area --------------------
		textArea.setBounds(0,50,650,50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(25,25,25));
		textArea.setForeground(new Color(25,255,0));
		textArea.setFont(new Font("MV Boli",Font.BOLD,25));
		textArea.setBorder(BorderFactory.createBevelBorder(1));
		textArea.setEditable(false);
		
		// -------------------- Button A --------------------
		buttonA.setBounds(0,100,100,100);
		buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");
		
		// -------------------- Button B --------------------	
		buttonB.setBounds(0,200,100,100);
		buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");
		
		// -------------------- Button C --------------------
		buttonC.setBounds(0,300,100,100);
		buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");
		
		// -------------------- Button D --------------------
		buttonD.setBounds(0,400,100,100);
		buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");
		
		// -------------------- Answer Label A --------------------
		answerLabelA.setBounds(125,100,500,100);
		answerLabelA.setBackground(new Color(50,50,50));
		answerLabelA.setForeground(new Color(25,255,0));
		answerLabelA.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		// -------------------- Answer Label B --------------------
		answerLabelB.setBounds(125,200,500,100);
		answerLabelB.setBackground(new Color(50,50,50));
		answerLabelB.setForeground(new Color(25,255,0));
		answerLabelB.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		// -------------------- Answer Label C --------------------
		answerLabelC.setBounds(125,300,500,100);
		answerLabelC.setBackground(new Color(50,50,50));
		answerLabelC.setForeground(new Color(25,255,0));
		answerLabelC.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		// -------------------- Answer Label D --------------------
		answerLabelD.setBounds(125,400,500,100);
		answerLabelD.setBackground(new Color(50,50,50));
		answerLabelD.setForeground(new Color(25,255,0));
		answerLabelD.setFont(new Font("MV Boli",Font.PLAIN,35));
		
		// -------------------- Seconds Left --------------------
		secondsLeft.setBounds(535,510,100,100);
		secondsLeft.setBackground(new Color(25,25,25));
		secondsLeft.setForeground(new Color(255,0,0));
		secondsLeft.setFont(new Font("Ink Free",Font.BOLD,60));
		secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
		secondsLeft.setOpaque(true);
		secondsLeft.setHorizontalAlignment(JTextField.CENTER);
		secondsLeft.setText(String.valueOf(seconds));
		
		// -------------------- Timer Label --------------------
		timeLabel.setBounds(535,475,100,25);
		timeLabel.setBackground(new Color(50,50,50));
		timeLabel.setForeground(new Color(255,0,0));
		timeLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		timeLabel.setText("Timer");
		
		// -------------------- Right Amount Of Answers --------------------
		numberRight.setBounds(225,225,200,100);
		numberRight.setBackground(new Color(25,25,25));
		numberRight.setForeground(new Color(25,255,0));
		numberRight.setFont(new Font("Ink Free",Font.BOLD,50));
		numberRight.setBorder(BorderFactory.createBevelBorder(1));
		numberRight.setHorizontalAlignment(JTextField.CENTER);
		numberRight.setEditable(false);
		
		// -------------------- Percentage Of Questions Right --------------------
		percentage.setBounds(225,325,200,100);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(25,255,0));
		percentage.setFont(new Font("Ink Free",Font.BOLD,50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		// -------------------- Add To Frame --------------------
		quizFrame.add(timeLabel);
		quizFrame.add(secondsLeft);
		quizFrame.add(answerLabelA);
		quizFrame.add(answerLabelB);
		quizFrame.add(answerLabelC);
		quizFrame.add(answerLabelD);
		quizFrame.add(buttonA);
		quizFrame.add(buttonB);
		quizFrame.add(buttonC);
		quizFrame.add(buttonD);
		quizFrame.add(textArea);
		quizFrame.add(textField);
		quizFrame.setVisible(true);
		
		// -------------------- Start Game --------------------
		nextQuestion();
	}
	
	public void nextQuestion() {
		// Check if there are more questions left
		if(index >= totalQuestions) {
			results();
		}
		// Set question and answers
		else {
			textField.setText("Question " + (index+1));
			textArea.setText(questions[index]);
			answerLabelA.setText(options[index][0]);
			answerLabelB.setText(options[index][1]);
			answerLabelC.setText(options[index][2]);
			answerLabelD.setText(options[index][3]);
			// Start countdown timer
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		// -------------------- User Presses A Button --------------------
		if(e.getSource() == buttonA) {
			// Set answer to variable
			answer = 'A';
			// Check if matches with right answer
			if(answer == answers[index]) {
				// Update score
				correctGuesses++;
			}
		}
		
		// -------------------- User Presses B Button --------------------
		if(e.getSource() == buttonB) {
			// Set answer to variable
			answer = 'B';
			// Check if matches with right answer
			if(answer == answers[index]) {
				// Update score
				correctGuesses++;
			}
		}
		// -------------------- User Presses C Button --------------------
		if(e.getSource() == buttonC) {
			// Set answer to variable
			answer = 'C';
			// Check if matches with right answer
			if(answer == answers[index]) {
				// Update score
				correctGuesses++;
			}
		}
		// -------------------- User Presses D Button --------------------
		if(e.getSource() == buttonD) {
			// Set answer to variable
			answer = 'D';
			// Check if matches with right answer
			if(answer == answers[index]) {
				// Update score
				correctGuesses++;
			}
		}
		
		// -------------------- Display The Right Answer --------------------
		displayAnswer();
		
	}
	
	public void displayAnswer() {
		// Stop the countdown timer
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		// Check answer
		if(answers[index] != 'A')
			// Change font color
			answerLabelA.setForeground(new Color(255,0,0));
		if(answers[index] != 'B')
			answerLabelB.setForeground(new Color(255,0,0));
		if(answers[index] != 'C')
			answerLabelC.setForeground(new Color(255,0,0));
		if(answers[index] != 'D')
			answerLabelD.setForeground(new Color(255,0,0));
		
		// Create pause before next question
		Timer pause = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Change font colors back to green
				answerLabelA.setForeground(new Color(25,255,0));
				answerLabelB.setForeground(new Color(25,255,0));
				answerLabelC.setForeground(new Color(25,255,0));
				answerLabelD.setForeground(new Color(25,255,0));
				// Reset answer
				answer = ' ';
				// Reset time
				seconds = 10;
				secondsLeft.setText(String.valueOf(seconds));
				// Enable buttons
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				// Increase index for the next question
				index++;
				// Get next question
				nextQuestion();
			}
		});
		
		// Only pause once
		pause.setRepeats(false);
		// Start pause
		pause.start();
		
	}
	
	public void results() {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		// Count percentage of right answers
		result = (int)((correctGuesses / (double)totalQuestions) * 100);
		// Empty the labels
		textField.setText("Results !");
		textArea.setText("");
		textArea.setBackground(new Color(50,50,50));
		textArea.setBorder(null);
		answerLabelA.setText("");
		answerLabelB.setText("");
		answerLabelC.setText("");
		answerLabelD.setText("");
		// Show how many questions out of all questions
		numberRight.setText(correctGuesses + " / " + totalQuestions);
		// Show percentage
		percentage.setText(result+ "%");
		// Add to frame
		quizFrame.add(numberRight);
		quizFrame.add(percentage);
	}
	
}
