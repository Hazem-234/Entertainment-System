import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[] cells = new JButton[9];
    private final JLabel status = new JLabel("Player X's turn");
    private final JButton resetBtn = new JButton("Reset");
    private char current = 'X';
    private boolean gameOver = false;

    public static void startGame() {
        SwingUtilities.invokeLater(() -> new TicTacToe().setVisible(true));
    }

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel board = new JPanel(new GridLayout(3, 3, 5, 5));
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font f = new Font("Arial", Font.BOLD, 48);
        for (int i = 0; i < 9; i++) {
            JButton b = new JButton(""); // At Start Button is Blank
            b.setFocusPainted(false); // Visual Style
            b.setFont(f);
            b.addActionListener(this); // Respond When User Clicks
            b.setPreferredSize(new Dimension(100, 100));
            cells[i] = b; // Save for Future Checking 
            board.add(b); // Visibility in the Window
        }
        // This part jandles the bootom part of the game such as status label and reset button
        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 16));
        resetBtn.addActionListener(e -> reset()); // even handler , calling reset method when clicked
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottom.add(status, BorderLayout.CENTER);
        bottom.add(resetBtn, BorderLayout.EAST);

        getContentPane().setLayout(new BorderLayout());
        add(board, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        pack(); // Resizew components so they fit perfectly in the window 
        setLocationRelativeTo(null); // Centers Game window in middle of screen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        JButton b = (JButton) e.getSource();
        if (!b.getText().isEmpty()) return; // already taken

        b.setText(String.valueOf(current));
        b.setForeground(current == 'X' ? new Color(220, 20, 60) : new Color(25, 118, 210)); // nice colors

        char winner = checkWinner();
        if (winner != '-') {
            status.setText("Player " + winner + " wins!"); // X or O
            gameOver = true;
            highlightWin(winner);
            return;
        }
        if (isDraw()) {
            status.setText("It's a draw!");
            gameOver = true;
            return;
        }

        // switch player
        current = (current == 'X') ? 'O' : 'X';
        status.setText("Player " + current + "'s turn");
    }

    private void reset() {
        for (JButton c : cells) {
            c.setText("");
            c.setBackground(null);
            c.setOpaque(false);
        }
        current = 'X';
        gameOver = false;
        status.setText("Player X's turn");
    }

    private boolean isDraw() {
        for (JButton c : cells) {
            if (c.getText().isEmpty()) return false;
        }
        return true;
    }

    private char checkWinner() {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // cols
            {0,4,8},{2,4,6}          // diagonals
        };
        for (int[] L : lines) {
            String a = cells[L[0]].getText();
            String b = cells[L[1]].getText();
            String c = cells[L[2]].getText();
            if (!a.isEmpty() && a.equals(b) && b.equals(c)) {
                return a.charAt(0);
            }
        }
        return '-';
    }

    private void highlightWin(char winner) {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] L : lines) {
            String a = cells[L[0]].getText();
            String b = cells[L[1]].getText();
            String c = cells[L[2]].getText();
            if (!a.isEmpty() && a.equals(b) && b.equals(c)) {
                for (int idx : L) {
                    cells[idx].setOpaque(true);
                    cells[idx].setBackground(new Color(255, 235, 59)); // highlight winning line
                }
                break;
            }
        }
        // disable further edits
    }

    public static void main(String[] args) {
        startGame();
    }
}

