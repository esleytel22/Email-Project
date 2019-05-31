
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextPane;



public class NewUser extends JFrame {
	private JPanel contentPane;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JButton creAccButton;

	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField idField;
	private JPasswordField passwordtField;
	
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private ButtonGroup radioButtonGroup;
	String input4 ;
	
	public NewUser() {
		setTitle("Register");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 450);
		contentPane = new JPanel();
		//contentPane.setBackground(SystemColor.activeCaption);
		//contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  firstNameLabel = new JLabel("First Name:");
		  firstNameLabel.setFont(new Font("Times New Roman",1,18));
		  firstNameField = new JTextField(20);
		  
		  firstNameLabel.setBounds(40, 70, 180, 35);
		  firstNameField.setBounds(180, 70, 200, 35);
		  contentPane.add(firstNameLabel);
		  contentPane.add(firstNameField);
		  
		  lastNameLabel = new JLabel("Last Name:");
		  lastNameLabel.setFont(new Font("Times New Roman",1,18));
		  lastNameField = new JTextField(20);
		  
		  lastNameLabel.setBounds(40, 130, 180, 36);
		  lastNameField.setBounds(180, 130, 200, 35);
		  contentPane.add(lastNameLabel);
		  contentPane.add(lastNameField);
		  
		  idLabel = new JLabel("User Name:");
		  idLabel.setFont(new Font("Times New Roman",1,18));
		  idField = new JTextField(20);
		  
		 
		 
		  
		  idLabel.setBounds(40, 190, 175, 33);
		  idField.setBounds(180, 190, 200, 35);
		  contentPane.add(idLabel);
		  contentPane.add(idField);
		  
		  radio1 = new JRadioButton("@yg.com");
		  radio1.setBackground(SystemColor.activeCaption);
		  radio2 = new JRadioButton("@qc.edu");
		  radio2.setBackground(SystemColor.activeCaption);
		  radio3 = new JRadioButton("@lnb.gov");
		  radio3.setBackground(SystemColor.activeCaption);
		  radio1.setFont(new Font("Times New Roman",1,18));
		  radio2.setFont(new Font("Times New Roman",1,18));
		  radio3.setFont(new Font("Times New Roman",1,18));
		  radio1.setBounds(417, 165, 120, 25);
		  radio2.setBounds(417, 193, 120, 25);
		  radio3.setBounds(417, 222, 120, 25);
		  radioButtonGroup =  new ButtonGroup();
		  radioButtonGroup.add(radio1);
		  radioButtonGroup.add(radio2);
		  radioButtonGroup.add(radio3);
		
		  contentPane.add(radio1);
		  contentPane.add(radio2);
		  contentPane.add(radio3);
		  
		  
		  
		  
		  passwordLabel = new JLabel("Password:");
		  passwordLabel.setFont(new Font("Times New Roman",1,18));
		  passwordtField = new JPasswordField(20);
		 

		  
		  passwordLabel.setBounds(40, 250, 180, 33);
		  passwordtField.setBounds(180, 250, 200, 35);
		  contentPane.add(passwordLabel);
		  contentPane.add(passwordtField);

		  
		  creAccButton = new JButton("Enter Account ");
		  creAccButton.setFont(new Font("Times New Roman",1,20));
		  
		  creAccButton.setBounds(82, 320, 400, 45);
		  contentPane.add(creAccButton);
		  
		  JLabel lblNewLabel = new JLabel("Pleace Enter A New Account");
		  lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		  lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		  lblNewLabel.setBounds(82, 11, 400, 35);
		  contentPane.add(lblNewLabel);
		  
		  JTextPane txtpnPleaseEneterA = new JTextPane();
		  txtpnPleaseEneterA.setForeground(SystemColor.inactiveCaption);
		  txtpnPleaseEneterA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  txtpnPleaseEneterA.setBackground(SystemColor.activeCaption);
		  txtpnPleaseEneterA.setEditable(false);
		  txtpnPleaseEneterA.setEnabled(false);
		  txtpnPleaseEneterA.setText("Please Enter a minimum of 4-20 characters");
		  txtpnPleaseEneterA.setBounds(417, 70, 78, 70);
		  contentPane.add(txtpnPleaseEneterA);
		  
		  JButton btnExit = new JButton("Exit");
		  btnExit.setBounds(34, 18, 89, 23);
		  contentPane.add(btnExit);
		  btnExit.addActionListener((ActionEvent event) -> {
	            Email n = new Email();
	            n.setVisible(false);
	            setVisible(false);
	            
	        });
  
		  creAccButton.addActionListener(new newAccButtonListener());
		  radio1.addActionListener(new RadioButtonListener());
		  radio2.addActionListener(new RadioButtonListener());
		  radio3.addActionListener(new RadioButtonListener());
		 setVisible(true);
	}
	
	private class RadioButtonListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	    	  if(e.getSource() == radio1)
				  input4 = "@yg.com";
			  else if(e.getSource() == radio2)
				  input4 = "@qc.edu";
			  else
				  input4 = "@lnb.gov";
	    	  
	      }
	}

	private class newAccButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			  String input1 =  firstNameField.getText();
			  String input2 =  lastNameField.getText();
			  String input3 =  idField.getText(); 
			  String input5 =  passwordtField.getText();
			  if(input3.length()>13 ) {
				  JOptionPane.showMessageDialog(null, "User name should be 4- 20 characters, try again!");
				  return;
			  }
			  if(input5.length()<4 ||input5.length()>12) {
				  JOptionPane.showMessageDialog(null, "Password shoule be 4 to 12 characters, try again!");
				  return;
			  }
			  Connection conn =null;
			  Statement st = null;
			  ResultSet re =null;
			  try {
				    conn = getConnection();
				    st = conn.createStatement();
					re = st.executeQuery("SELECT * FROM user.info");
					 while(re.next()) {
					
						 if(input3.equals(re.getString("userName")) && input4.equals(re.getString("domain"))) {
							// showLabel2.setVisible(true);
							 JOptionPane.showMessageDialog(null, "Sorry,the user name and domain is taken. Try again!");
							 return;
							   
						 }		 
					
					 }
				    
				    String query = " insert into info (firstName,lastName,userName,domain,password)"
					        + " values (?, ?, ?, ?, ?)";		  
					      PreparedStatement preparedStmt = conn.prepareStatement(query);
					      preparedStmt.setString (1, input1);
					      preparedStmt.setString (2, input2);	
					      preparedStmt.setString (3, input3);
					      preparedStmt.setString (4, input4);
					      preparedStmt.setString (5, input5);	
					      preparedStmt.execute();
					 	 conn.close();
					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
			  finally {
				    
				    if (st != null) {
				        try {
				            st.close();
				        } catch (SQLException e1) { /* ignored */}
				    }
				    if (re != null) {
				        try {
				            re.close();
				        } catch (SQLException e2) { /* ignored */}
				    }
				    if (conn != null) {
				        try {
				            conn.close();
				        } catch (SQLException e2) { /* ignored */}
				    }
				}
			  
		
			  JOptionPane.showMessageDialog(null, "Congrats, you registered a account!");
			    dispose();
			 // showLabel1.setVisible(true);
		  }
	}
	public static Connection getConnection() {
		  try
		  {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root", "267731354Cb");
		 return conn;
		 }
		  catch(Exception ex)
		  {
		  System.out.println("ERROR: " + ex.getMessage());
		  }
		  return null;
	
	    }
   }		


