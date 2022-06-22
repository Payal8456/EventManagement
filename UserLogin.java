import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class UserLogin extends JFrame {
    private JTextField username;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public UserLogin() {
        setBounds(450, 190, 500, 597);
        setResizable(false);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblNewUserRegister = new JLabel("User Login");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(200, 52, 325, 50);
        contentPane.add(lblNewUserRegister);


        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(0, 159, 99, 29);
        contentPane.add(lblUsername);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
        username.setBounds(200, 151, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(0, 245, 99, 24);
        contentPane.add(lblPassword);


        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(200, 235, 228, 50);
        contentPane.add(passwordField);
    

        btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = username.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagement", "root", "");
                    System.out.println("Connection ok ok status is = "+connection);
                    System.out.println(email); //for demo 
                    System.out.println(password); //for demo
                    String query = "SELECT * FROM customer where email='"+email+"' AND password='"+password+"'";
                    
                    Statement sta = connection.createStatement();
                    
                    int total = sta.executeUpdate(query);
                    System.out.println("check "+total);
                    if (total == 1) {
                        JOptionPane.showMessageDialog(btnNewButton, "Login and redirect");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Login Error");
                    }
                    connection.close();
                } 
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(200, 447, 259, 74);
        contentPane.add(btnNewButton);
    }
}