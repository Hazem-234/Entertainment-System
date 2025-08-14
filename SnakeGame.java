import javax.swing.*;
public class SnakeGame {

  public static void startGame() {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel()); 
        frame.pack(); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

  public static void main(String[] args) {
    
    startGame();

  }

}