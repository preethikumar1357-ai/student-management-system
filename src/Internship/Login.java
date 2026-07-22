package Internship;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login {
	public <addActionListener> Login() {
        JFrame frame = new JFrame("Login Form");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(224, 247, 250));

        JLabel heading = new JLabel("Login");
        heading.setBounds(250, 30, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        frame.add(heading);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setBounds(100, 100, 100, 30);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 18));
        emailField.setBounds(220, 100, 250, 30);
        frame.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passLabel.setBounds(100, 150, 120, 30);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.BOLD, 18));
        passField.setBounds(220, 150, 250, 30);
        frame.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBounds(150, 250, 100, 30);
        frame.add(loginBtn);

        JButton regBtn = new JButton("Register");
        regBtn.setFont(new Font("Arial", Font.BOLD, 16));
        regBtn.setBounds(300, 250, 120, 30);
        frame.add(regBtn);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter email and password.");
                return;
            }

            try {
                Connection conn = Student.getConnection();
                String sql = "SELECT * FROM students WHERE email=? AND password=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    frame.dispose();
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        regBtn.addActionListener(e -> {
            frame.dispose();
            new college();
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



