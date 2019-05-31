
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.SystemColor;

public class Email extends JFrame {
	private JPanel contentPane;
	private JButton loginButton;
	private JButton creatButton;

		 
		  
	  public Email() {
		 
			    setTitle("Register for new account");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 500, 400);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				creatButton = new JButton("Register");
				creatButton.setFont(new Font("Times New Roman",1,20));
				creatButton.setBounds(130, 100, 200, 55);
				contentPane.add(creatButton);
				creatButton.addActionListener(new newAccButtonListener());
				
				loginButton = new JButton("Log in");
				loginButton.setFont(new Font("Times New Roman",1,20));
				loginButton.setBounds(130, 180, 200, 55);
				contentPane.add(loginButton);
				loginButton.addActionListener(new loginButtonListener());
				
				setVisible(true);
	  }
	  
	  
	  private class newAccButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			 
			  NewUser n = new NewUser();
		  }  
	  }
	  private class loginButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			 
			  Login l = new Login();
		
		    }	 
		  }
	  
	   public static void main(String[] args){
		   
		   Email t = new Email();
		
   }	
}


