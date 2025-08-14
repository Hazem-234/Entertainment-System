
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MindQuiz extends JFrame implements ActionListener {

    JTextField answer = new JTextField() ; 


    String[] questions = {
        "What vulnerability allows an attacker to manipulate a web application's database" , 
        "What is the missing number (15,11,7,.....)"     , 
        "What is the output of the expression !!(1 & 2 == 2)  , ?" ,
        "What are The Products of a Chemical Reaction between Acid and Base" ,
        "What doesn't decrease according to the Seconf Law Of Thermodyanamics" ,
        "A clock shows 3:15. What is the angle between the hour and the minute hands" , 
        "Which notorious worm, released in 2017, exploited SMBv1 to spread rapidly across networks"
     } ; 

     String [] solutions = {
        "sqli" , 
        "3"    , 
        "0"    , 
        "salt and water"  , 
        "entropy" , 
        "7.5" , 
        "wannacry"
     } ;
         
     int currentquestion = 0 ; 
     int counter = 0 ;  

     JLabel feedbackLabel = new JLabel("");

     JLabel questionlabel = new JLabel(questions[currentquestion]) ; // Array Tracking the index of Questions

    public MindQuiz ()
    {

        this.setTitle("MindQuiz-Game");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,400);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        JPanel panelone = new JPanel() ; 
        panelone.setLayout(null);
        panelone.setBounds(0,0,600,400);
        panelone.setBackground(new Color(94, 129, 172));
        this.add(panelone) ;

         questionlabel.setBounds (50,50,500,30) ; 
         questionlabel.setFont(new Font("inc free" , Font.BOLD , 13));
         questionlabel.setForeground(Color.WHITE);

         JButton submitbtn = new JButton("Submit") ; 
         submitbtn.setBounds(270,100,100,30);
         submitbtn.addActionListener(this);
        
         answer.setBounds(50,100,200,30);
         answer.setPreferredSize(new Dimension(250 , 20));
       
        feedbackLabel.setBounds(50, 150, 500, 30);
        feedbackLabel.setForeground(Color.YELLOW);

         panelone.add(questionlabel) ; 
         panelone.add(answer) ; 
         panelone.add(feedbackLabel) ; 
         panelone.add(submitbtn) ; 
         getRootPane().setDefaultButton(submitbtn); 

    }
  
public void actionPerformed (ActionEvent e)
{
    String playeranswer = answer.getText().trim().toLowerCase() ; 
    if (playeranswer.equals(solutions[currentquestion]))
    {
        counter ++ ; 
        feedbackLabel.setText("Correct");  
    }
    else
    {
        feedbackLabel.setText("incorrect");
    }
    currentquestion ++ ; 

    if (currentquestion < questions.length) // can be questions < 5 directly
    {
        questionlabel.setText(questions[currentquestion]);
        answer.setText(""); // clearing the answer text field

    }
    else
    {
        showResult () ; 
    }
}

public void showResult() {
    JFrame resultFrame = new JFrame("Result");
    resultFrame.setSize(400, 200);
    resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    resultFrame.setLayout(new BorderLayout());

    String message = "Your Score: " + counter + "/7\n";
    if (counter == 7) message += "Genius! Your IQ is outstanding.";
    else if (counter >= 5) message += "Smart! You have good intelligence.";
    else message += "IQ = Nothing , :(, What a Dump Man!!";

    JTextArea resultArea = new JTextArea(message);
    resultArea.setFont(new Font("Arial", Font.BOLD, 16));
    resultArea.setEditable(false);
    resultArea.setBackground(new Color(230, 230, 250));

    resultFrame.add(resultArea);
    resultFrame.setVisible(true);
    dispose(); 
}

}