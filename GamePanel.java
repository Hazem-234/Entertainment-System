import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; // Generating Random Numbers (the food of the snake)
import java.util.ArrayList; // adding the new length of snake body
import javax.swing.Timer;

/*
 * Task 1: User input (WASD Movement Keys)--Output: Snake Movement
 * Task 2: User input ( Collision or No Movement ) --  Output: Window Game Over Display
 * Task 3: User input (R for Restart ) -- Output: Game Running after Game Over
 *  Three Input/Output Tasks Fulfilled
 * */
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 600, HEIGHT = 600, UNIT_SIZE = 25; 
    private int delay = 150; 
    private Timer timer; 

    private char direction = 'R'; 
    private boolean running = false;

    private ArrayList<Point> snake = new ArrayList<>(); 
    private Point food; 

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
        this.setBackground(Color.black);
        this.setFocusable(true); 
        this.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        snake.clear(); 
        snake.add(new Point(UNIT_SIZE * 4, UNIT_SIZE * 4)); 
        getFood();
        direction = 'R';
        delay = 150;
        running = true; 
        timer = new Timer(delay, this); 
        timer.start();
    }

    public void getFood() {
        Random rand = new Random();
        // getting the x and y coordinate 
        int x = rand.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE; 
        int y = rand.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        food = new Point(x, y);
    }

    public void move() {
        Point head = new Point(snake.get(0)); // get snake current position->copy->add
        
        switch (direction) {
            case 'U':
                head.y -= UNIT_SIZE;
                break;
            case 'D':
                head.y += UNIT_SIZE;
                break;
            case 'L':
                head.x -= UNIT_SIZE;
                break;
            case 'R':
                head.x += UNIT_SIZE;
                break;
        }

        
        if (head.equals(food)) { 
            snake.add(0, head);
            getFood();
            if (delay > 50) {
                delay -= 5;
                timer.setDelay(delay);
            }
        } else {
            snake.add(0, head);
            snake.remove(snake.size() - 1);
            
        }
    }


    public void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH ||  head.x >= WIDTH  || head.y < 0 || head.y >= HEIGHT) {
            running = false;
        }
        for (int i = 1; i < snake.size(); i++) { 
            if (head.equals(snake.get(i))) { 
                running = false;
                break;
            }
        } 

        if (!running) {
            timer.stop(); 
        }
    }

    public void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        draw(g); 
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Head
                } else {
                    g.setColor(new Color(45, 180, 0)); // Body: RGB--Draw it in a a darker green
                }
                Point p = snake.get(i);
                g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Score: " + (snake.size() - 1), 10, 20);
            if((snake.size() - 1)>15)
            {
              g.drawString("Venomous Snake", 10, 35);
            }
            else
            {
              g.drawString("Hungry Snake", 10, 35);
            }
        } else {
            gameOver(g); // Draw the Game Over Screen 
        }
    }
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", WIDTH / 2 - 120, HEIGHT / 2 - 20);
        if((snake.size() - 1)<10)
        {
          g.drawString("You Did Great!", WIDTH / 2-140, HEIGHT / 2 + 120);
        }
        else
        {
          g.drawString("You did Fantastic!",  WIDTH / 2 -140, HEIGHT / 2 + 120);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + (snake.size() - 1), WIDTH / 2 - 40, HEIGHT / 2 + 20);
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Press R to Restart", WIDTH / 2 - 80, HEIGHT / 2 + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        
        switch (key) {
            case 'a':
            case 'A':
                if (direction != 'R') direction = 'L';
                break;
            case 'd':
            case 'D':
                if (direction != 'L') direction = 'R';
                break;
            case 'w':
            case 'W':
                if (direction != 'D') direction = 'U';
                break;
            case 's':
            case 'S':
                if (direction != 'U') direction = 'D';
                break;
            case 'r':
            case 'R':
                if (!running) {
                    startGame();
                }
                break;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}