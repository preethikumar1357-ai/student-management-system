package Internship;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class college {
	 public class Dashboard {

	}

	public college() {
	        JFrame frame = new JFrame("User Registration");
	        frame.setSize(1100, 600);
	        frame.setLayout(null);
	        frame.getContentPane().setBackground(Color.PINK);

	        JLabel heading = new JLabel("User Registration Form");
	        heading.setBounds(400, 50, 300, 30);
	        heading.setFont(new Font("Arial", Font.BOLD, 20));
	        heading.setForeground(new Color(138, 43, 226));
	        frame.add(heading);

	        JLabel nameLabel = new JLabel("Name:");
	        nameLabel.setBounds(250, 120, 100, 25);
	        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(nameLabel);

	        JTextField nameField = new JTextField();
	        nameField.setBounds(400, 120, 270, 25);
	        nameField.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(nameField);

	        JLabel userLabel = new JLabel("Email:");
	        userLabel.setBounds(250, 170, 100, 25);
	        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(userLabel);

	        JTextField userField = new JTextField();
	        userField.setBounds(400, 170, 270, 25);
	        userField.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(userField);

	        JLabel passLabel = new JLabel("Password:");
	        passLabel.setBounds(250, 220, 120, 25);
	        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(passLabel);

	        JPasswordField passField = new JPasswordField();
	        passField.setBounds(400, 220, 270, 25);
	        passField.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(passField);

	        JLabel genderLabel = new JLabel("Gender:");
	        genderLabel.setBounds(250, 270, 100, 25);
	        genderLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        frame.add(genderLabel);

	        JRadioButton male = new JRadioButton("Male");
	        male.setBounds(400, 270, 100, 25);
	        male.setFont(new Font("Arial", Font.BOLD, 16));
	        JRadioButton female = new JRadioButton("Female");
	        female.setBounds(520, 270, 100, 25);
	        female.setFont(new Font("Arial", Font.BOLD, 16));

	        ButtonGroup genderGroup = new ButtonGroup();
	        genderGroup.add(male);
	        genderGroup.add(female);
	        frame.add(male);
	        frame.add(female);

	        JButton submitButton = new JButton("Register");
	        submitButton.setBounds(400, 330, 150, 35);
	        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
	        submitButton.setBackground(Color.YELLOW);
	        frame.add(submitButton);

	        submitButton.addActionListener(e -> {
	            String name = nameField.getText();
	            String email = userField.getText();
	            String password = new String(passField.getPassword());
	            String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");

	            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "All fields are required!");
	                return;
	            }

	            try {
	                Connection conn = Student.getConnection();
	                if (conn == null) {
	                    JOptionPane.showMessageDialog(null, "Database connection failed!");
	                    return;
	                }

	                String query = "INSERT INTO students (name, email, password, gender) VALUES (?, ?, ?, ?)";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setString(1, name);
	                stmt.setString(2, email);
	                stmt.setString(3, password);
	                stmt.setString(4, gender);

	                int i = stmt.executeUpdate();
	                if (i > 0) {
	                    JOptionPane.showMessageDialog(null, "Registration Successful! Please login.");
	                    frame.dispose();
	                    new Login();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Registration Failed.");
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	            }
	        });

	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }

	    private static Connection getConnection() {
		return null;
	}

		public static void main(String[] args) {
	        new college();
	    }
	}


