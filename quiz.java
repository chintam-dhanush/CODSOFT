import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class quiz implements ActionListener {

    JFrame frame;
    JPanel topPanel, bottomPanel;
    JLabel questionLabel, timerLabel;
    JRadioButton option1, option2, option3, option4;
    JButton submitButton;
    ButtonGroup group;

    int currentQuestionIndex = 0;
    int score = 0;
    int timeRemaining = 30;
    Timer timer;

    String[] questions = {
        "What is the capital of Japan?",
        "Which language runs in a web browser?",
        "Who invented the telephone?",
        "What is the square root of 64?",
        "What is the largest planet in the solar system?"
    };

    String[][] options = {
        {"Beijing", "Seoul", "Tokyo", "Bangkok"},
        {"Java", "Python", "JavaScript", "C++"},
        {"Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Albert Einstein"},
        {"6", "7", "8", "9"},
        {"Earth", "Mars", "Jupiter", "Saturn"}
    };

    String[] correctAnswers = {"Tokyo", "JavaScript", "Alexander Graham Bell", "8", "Jupiter"};

    public quiz() {
        createFrame();
        createPanels();
        createLabels();
        createRadioButtons();
        loadQuestion(currentQuestionIndex);
        startTimer();

        frame.setVisible(true);
    }

    public void createFrame() {
        frame = new JFrame("Quiz Application");
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createPanels() {
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 600, 100);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(101, 67, 33));  
    
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 100, 600, 400);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(245, 222, 179));  
    
        submitButton = new JButton("Submit");
        submitButton.setBounds(245, 250, 100, 40);
        submitButton.setBackground(new Color(210, 105, 30)); 
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
    
        bottomPanel.add(submitButton);
    
        frame.add(topPanel);
        frame.add(bottomPanel);
    }
    
    public void createLabels() {
        questionLabel = new JLabel();
        questionLabel.setBounds(20, 20, 550, 40);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        questionLabel.setForeground(new Color(255, 255, 240));  
    
        timerLabel = new JLabel("Time: " + timeRemaining + "s");
        timerLabel.setBounds(500, 30, 80, 50);
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timerLabel.setForeground(new Color(255, 255, 240)); 
    
        topPanel.add(questionLabel);
        topPanel.add(timerLabel);
    }

    public void createRadioButtons() {
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        
        styleRadioButton(option1, 50);
        styleRadioButton(option2, 100);
        styleRadioButton(option3, 150);
        styleRadioButton(option4, 200);
        
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);
        
        bottomPanel.add(option1);
        bottomPanel.add(option2);
        bottomPanel.add(option3);
        bottomPanel.add(option4);
    }
    
    private void styleRadioButton(JRadioButton radioButton, int yPosition) {
        radioButton.setBounds(50, yPosition, 500, 30);
        radioButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        radioButton.setForeground(new Color(54, 39, 39));  
        radioButton.setBackground(new Color(245, 222, 179));  
    }
    
    public void loadQuestion(int index) {
        if (index < questions.length) {
            questionLabel.setText((index + 1) + ". " + questions[index]);
            option1.setText(options[index][0]);
            option2.setText(options[index][1]);
            option3.setText(options[index][2]);
            option4.setText(options[index][3]);
        } else {
            endQuiz();
        }
    }

    public void submitAnswer() {
        String selectedAnswer = null;
        if (option1.isSelected()) selectedAnswer = option1.getText();
        if (option2.isSelected()) selectedAnswer = option2.getText();
        if (option3.isSelected()) selectedAnswer = option3.getText();
        if (option4.isSelected()) selectedAnswer = option4.getText();

        if (selectedAnswer != null && selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            score += 20;
        }

        group.clearSelection();
        timeRemaining = 30;
        loadQuestion(++currentQuestionIndex);
    }

    private void endQuiz() {
        timer.stop(); 
        frame.dispose();
        JOptionPane.showMessageDialog(null, "Quiz Over! Your Score: " + score);
    }

    public void startTimer() {
        timer = new Timer(1000, e -> {
            timeRemaining--;
            timerLabel.setText("Time: " + timeRemaining + "s");

            if (timeRemaining <= 0) {
                submitAnswer();
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submitAnswer();
        }
    }

    public static void main(String[] args) {
        new quiz();
    }
}
