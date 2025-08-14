/*import java.awt.Dimension;
import java.awt.Font;*/

import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*

   Perfoming Tasks of input and output
 * Task 1: Input: Clicking Snake Game --- Output: Snake-Game Start
 * Task 2: Input: Clicking Mind Quiz --- Output: Mind Quiz Start
 * Task 3: Input: Clicking Menu Items --- Output: Information Messages Display
 * Task 4: INput: Clicking Back to Login ---Output: Login Form 
 */


public class Board extends JFrame implements ActionListener {
    JMenuBar barone = new JMenuBar() ; 
    JMenu menuone = new JMenu() ; // help -> personal-data , exit , description of Games
    
    JMenuItem itm1 = new JMenuItem() ; 
    JMenuItem itm2 = new JMenuItem() ; 
    //JMenuItem itm3 = new JMenuItem() ; 
    JMenu menutwo = new JMenu() ; // settings 
    JMenuItem itm4 = new JMenuItem() ; 
    JMenuItem itm5 = new JMenuItem() ; 
    JMenu menuthree = new JMenu() ; 
    JMenuItem itm6 = new JMenuItem() ; // VIP 
    JMenuItem itm7 = new JMenuItem() ; // BUYING GOLDS 

    JButton Btnone = new JButton("Mind-Quiz") ;
    JButton Btntwo = new JButton ("Snake-Game") ;
    JButton Btnthree = new JButton("Tic Tac Toe");

    private String username;


    public Board(String username)
    {
        this.username = username;
        this.setTitle("Dashboard");
        this.setSize(450,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(barone);

        menuone.setText("Help");
        itm1.setText("Account-info");
        itm2.setText("Exit");
       // itm3.setText("descriotion of Games");
        barone.add(menuone) ; 
        menuone.add(itm1) ; 
        menuone.add(itm2) ; 
       // menuone.add(itm3) ; 
        itm1.addActionListener(this);
        itm2.addActionListener(this);// كنا ممكن نعمل اراي نحط فيها الايتمز ونعمل لوب
       // itm3.addActionListener(this);

        menutwo.setText("Settings");
        barone.add(menutwo) ; 
        itm4.setText("Back to login page");
        itm5.setText("Control the Graphics ");
        menutwo.add(itm4) ; 
        menutwo.add(itm5) ; 
        itm4.addActionListener(this);
        itm5.addActionListener(this);

        menuthree.setText("Buy Our Products");
        barone.add(menuthree) ; 
        itm6.setText("VIP-Player");
        itm7.setText("Buying-Gold");
        menuthree.add(itm6) ; 
        menuthree.add(itm7) ; 
        itm6.addActionListener(this);
        itm7.addActionListener(this);

       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
       centerPanel.add(Btnone);
       centerPanel.add(Btntwo);
       centerPanel.add(Btnthree);
       add(centerPanel, BorderLayout.CENTER);
       this.setVisible(true);

        this.setLayout(null);
        this.add(Btnone) ; 
        this.add(Btntwo) ;
        this.add(Btnthree); 
        Btnone.setFocusable(false);
        Btntwo.setFocusable(false);
        Btnthree.setFocusable(false);
        Btnone.setPreferredSize(new Dimension(50,50));
        Btntwo.setPreferredSize(new Dimension(50,50));
        Btnthree.setPreferredSize(new Dimension(50,50));
       // Btnone.setBounds(150,250,150,30);
       // Btntwo.setBounds(150,125,150,30); // Manually Creating Bounds 
       // Btnthree.setBounds(150,350,150,30);
        Btnone.setBackground(new Color(100, 149, 237));
        Btntwo.setBackground(new Color(255, 165, 0)) ;
        Btnthree.setBackground(new Color(210, 255, 240));


        Btnone.addActionListener(this);
        Btntwo.addActionListener(this);
        Btnthree.addActionListener(this);


    }
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == itm2)
        {
            System.exit(0);
        }
        if (e.getSource() == itm4)
        {
            this.dispose(); 
            new loginform() ; 
        }
        if (e.getSource() == itm1)
        {
           JFrame personalinfo = new JFrame() ; 
            personalinfo.setTitle("Account info ");
            personalinfo.setSize(500,200);
            personalinfo.setResizable(true);
            personalinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel panelpersonalinfo = new JPanel() ; 
            panelpersonalinfo.setBackground(Color.GRAY);
            personalinfo.add(panelpersonalinfo) ; 

            JLabel labelpersonalinfo = new JLabel() ; 
            labelpersonalinfo.setText("You are Registered As : " + username );
            labelpersonalinfo.setFont(new Font("inc free" , Font.BOLD , 30));
            labelpersonalinfo.setForeground(Color.WHITE);
            panelpersonalinfo.add(labelpersonalinfo);
            personalinfo.setVisible(true);
        }

        if (e.getSource() == itm5)
        {
            JOptionPane.showMessageDialog(this , "This Feature is coming soon","titleone", JOptionPane.INFORMATION_MESSAGE);
        
        }
        if (e.getSource() == itm6)
        {
            JOptionPane.showMessageDialog(this , "This Feature is coming soon","titleone", JOptionPane.INFORMATION_MESSAGE);
        
        }
        if (e.getSource() == itm7)
        {
            JOptionPane.showMessageDialog(this , "This Feature is coming soon","titleone", JOptionPane.INFORMATION_MESSAGE);
        
        }

        if (e.getSource().equals(Btnone))
        {
            new MindQuiz() ; 
        }
        
        if (e.getSource().equals(Btntwo)) {
    SnakeGame.startGame();
}
        if(e.getSource().equals(Btnthree))
        {
            TicTacToe.startGame();
        }
        
    }
}