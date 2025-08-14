import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * Performing Tasks of Input/Output
 * Task 1: Input: User Registers ___ Output: "Registered and Saving Info"
 * Task 2: Input: User Logins with Same Registered Info ____ Output: Login Successful and Go to Board
 * Task 3: Input: User Logins using False Info ____ Output: Failed Login
 * Task 4: Input: User In-Acton(Leaving an Empty Field in Registering or Login)___Output: Error Message
 * Task 5: Input: User Hovers the Mouse Over the Two Buttons___Output: Color Changes
 */
public class loginform extends JFrame implements ActionListener {
    JPanel panelone = new JPanel();
    JLabel labelone = new JLabel("Login");
    JTextField textone = new JTextField(); 
    JLabel labeltwo = new JLabel("username"); 
    JPasswordField passwordone = new JPasswordField(); 
    JLabel labelthree = new JLabel("password"); 
    JButton btnone = new JButton(); 
    JButton btntwo = new JButton(); 

    loginform() {
        this.setTitle("Login Form");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1)); 

        // Panel 1 : Login Form
        panelone.setBackground(new Color(245, 245, 250));
        labelone.setFont(new Font("inc free", Font.BOLD, 50));
        panelone.add(labelone);
        this.add(panelone);

        // Panel 2: User Name
        JPanel paneltwo = new JPanel();
        paneltwo.setBackground(new Color(245, 245, 250));
        this.add(paneltwo);

        labeltwo.setFont(new Font(null , Font.BOLD , 20));
        paneltwo.add(labeltwo); 

        textone.setPreferredSize(new Dimension (220 , 33));
        textone.setFont(new Font("inc free" , Font.BOLD , 20));
        paneltwo.add(textone);

        // Panel 3 : Password
        JPanel panelthree = new JPanel();
        panelthree.setBackground(new Color(245, 245, 250));
        this.add(panelthree);

        labelthree.setFont(new Font ("inc free" , Font.BOLD , 20));
        panelthree.add(labelthree); 

        passwordone.setPreferredSize(new Dimension(220 ,33));
        passwordone.setFont(new Font("inc free" , Font.BOLD , 20));
        panelthree.add(passwordone); 

        // Panel 4 : Buttons
        JPanel panelfour = new JPanel(); 
        panelfour.setBackground(new Color(245, 245, 250));
        this.add(panelfour); 

        btnone.setText("login");
        btnone.setPreferredSize(new Dimension(100, 35));
        btnone.setFont(new Font("inc free" , Font.BOLD , 15));
        btnone.setFocusable(false);
        btnone.setBackground(new Color(0, 123, 255));
        btnone.addActionListener(this);
        btnone.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnone.setBackground(Color.MAGENTA); // Dark Blue
            }
            public void mouseExited(MouseEvent e) {
                btnone.setBackground(new Color(0, 102, 204)); // Light Blue
            }
        });
        panelfour.add(btnone); 

        btntwo.setText("Register");
        btntwo.setPreferredSize(new Dimension(100, 35));
        btntwo.setFont(new Font("inc free", Font.BOLD, 15));
        btntwo.setFocusable(false); 
        btntwo.setBackground(new Color(108, 117, 125));
        btntwo.addActionListener(this);
        btntwo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btntwo.setBackground(Color.ORANGE); 
            }
            public void mouseExited(MouseEvent e) {
                btntwo.setBackground(new Color(108, 117, 125)); // Grey
            }
        });
        panelfour.add(btntwo); 

        this.setVisible(true);
    }
    public void actionPerformed (ActionEvent e) {
        String username = textone.getText().trim(); 
        String password = new String(passwordone.getPassword()).trim();
        
       if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in both fields", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (e.getSource() == btntwo) { // Register Button
        if (registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "User Registered Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if (e.getSource() == btnone) { // Login Button
        if (checkLoginCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new Board(username);
            
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Incorrect credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

   
    private boolean registerUser(String username, String password) {
        try {
            File file = new File("users");
            if (!file.exists())
            {
                file.createNewFile() ; 
            }
    
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ":")) {
                    reader.close();
                    return false; 
                } 
            }
            reader.close();

            
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); 
            writer.write(username + ":" + password); // Adds New Line
            writer.newLine(); // Move to the next line
            writer.close();
            return true; // Signal Registration Success

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    private boolean checkLoginCredentials(String username, String password) {
        try {
            File file = new File("users");
            if (!file.exists()) return false; 

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(username + ":" + password)) {
                    reader.close();
                    return true; 
                }
            }
            reader.close();
        } catch (IOException e) { // it prints the stack of the error to the console, it doesn't stop the program it just logs it
            e.printStackTrace();
        }
        return false;
    }
}


