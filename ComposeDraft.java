
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class ComposeDraft extends JFrame {
	private String userName;
	private JPanel contentPane;
	private JLabel subjectLabel;
	private JLabel toLabel;
	private JButton discardButton;
	private JButton sentB;
	private JButton sentBdraft;
	
	private JTextField toField;
	private JTextField subjectField;
	private JTextArea textArea;
	private String From;
	//private String input4;
	//private String date;
	private String draftDate;
	

	public ComposeDraft(String usr) {
		this.userName = usr;
		GUI();
		
		try {
			//toField.setText(to);
			//subjectField.setText(subject);
			//textArea.setText(msg);
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		setVisible(true);
	}

	public ComposeDraft(String usr, String to,String from, String subject, String msg,String date) {
		this.userName = usr;
		this.draftDate = date;
		System.out.println("draftDate:"+draftDate);
		
		From=from;
		GUI();
		
		try {
			toField.setText(to);
			subjectField.setText(subject);
			textArea.setText(msg);
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		setVisible(true);
	}

	private class sentBButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  ResultSet re = st.executeQuery("SELECT * FROM user.draft");
					 while(re.next()) {
						if(draftDate.equals(re.getString("date"))) {
							String query1 = "DELETE FROM draft WHERE date = '"+draftDate+"' ";
			                PreparedStatement ps1 = conn.prepareStatement(query1);
			                ps1.execute();

						}
	 
					 }
						
					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	

			String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			String to = toField.getText();
			String subject = subjectField.getText();
			String content = textArea.getText();//.substring(0, 15);// Assume reply length is less than 15, this will get text before line "------"
			                                                     //since the content now has whole information from send
			try {
				Connection conn = getConnection();
				Statement st = conn.createStatement();
				ResultSet re = st.executeQuery("SELECT * FROM user.info");
				while (re.next()) {

					//if (to.equals(re.getString("userName") + re.getString("domain"))) {
						String query = "INSERT INTO `user`.`info2` (`sender`, `receiver`, `subject`, `content`,`date`) VALUES (?, ?, ?,?, ?)";// "insert

						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, userName);
						preparedStmt.setString(2, to);
						preparedStmt.setString(3, subject);
						preparedStmt.setString(4, content);
						preparedStmt.setString(5, date);
						preparedStmt.execute();
						JOptionPane.showMessageDialog(null, "Congrats, the item has been sent!");
						dispose();
						return;

					//}

				}
				JOptionPane.showMessageDialog(null, "Receiver email address does not exist. Please try again!");

			} catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}

		}
	}

	private class sentBdraftButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  ResultSet re = st.executeQuery("SELECT * FROM user.draft");
					 while(re.next()) {
						if(draftDate.equals(re.getString("date"))) {
							String query1 = "DELETE FROM draft WHERE date = '"+draftDate+"' ";
			                PreparedStatement ps1 = conn.prepareStatement(query1);
			                ps1.execute();

						}
	 
					 }
						
					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
			

			String subject = subjectField.getText();
			String to = toField.getText();
			String content = textArea.getText();
			String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			
			
			
			try {
				Connection conn = getConnection();

				String query = "INSERT INTO `user`.`draft` (`sender`, `receiver`, `subject`, `content`,`date`) VALUES (?, ?, ?,?, ?)";// "insert
											
   															
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, userName);
				preparedStmt.setString(2, to);
				preparedStmt.setString(3, subject);
				preparedStmt.setString(4, content);
				preparedStmt.setString(5, date);
				preparedStmt.execute();
				JOptionPane.showMessageDialog(null, "Congrats, you have saved it as draft!");
			} catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}

			dispose();
		}

	}

	private class discardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  ResultSet re = st.executeQuery("SELECT * FROM user.draft");
					 while(re.next()) {
						if(draftDate.equals(re.getString("date"))) {
							String query1 = "DELETE FROM draft WHERE date = '"+draftDate+"' ";
			                PreparedStatement ps1 = conn.prepareStatement(query1);
			                ps1.execute();

						}
	 
					 }
						
					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
			dispose();
		}
	}
	

	private void GUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("New email");
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		toLabel = new JLabel("To:");
		toLabel.setFont(new Font("Times New Roman", 1, 18));
		toField = new JTextField(80);
		toLabel.setBounds(50, 50, 150, 30);
		toField.setBounds(180, 50, 300, 30);
		contentPane.add(toLabel);
		contentPane.add(toField);

		// label
		subjectLabel = new JLabel("Subject:");
		subjectLabel.setFont(new Font("Times New Roman", 1, 18));
		subjectField = new JTextField(80);
		subjectLabel.setBounds(50, 100, 150, 30);
		subjectField.setBounds(180, 100, 300, 30);
		contentPane.add(subjectLabel);
		contentPane.add(subjectField);

		textArea = new JTextArea(10, 40);
		textArea.setBounds(50, 150, 530, 211);
		contentPane.add(textArea);

		sentB = new JButton("Send");
		sentB.setFont(new Font("Times New Roman", 1, 20));
		sentB.setBounds(240, 400, 150, 40);
		contentPane.add(sentB);
		sentB.addActionListener(new sentBButtonListener());

		// draft botton
		sentBdraft = new JButton("Save as Draft");
		sentBdraft.setFont(new Font("Times New Roman", 1, 20));
		sentBdraft.setBounds(30, 400, 150, 40);
		contentPane.add(sentBdraft);
		sentBdraft.addActionListener(new sentBdraftButtonListener());

		discardButton = new JButton("Delete");
		discardButton.setFont(new Font("Times New Roman", 1, 20));
		discardButton.setBounds(450, 400, 150, 40);
		contentPane.add(discardButton);
		discardButton.addActionListener(new discardButtonListener());
		//getContentPane().setBackground(SystemColor.activeCaption);
		
	}


	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root", "267731354Cb");
			return conn;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	

}
