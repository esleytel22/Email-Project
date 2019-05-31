
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

public class ComposeInbox extends JFrame {
	
	private JPanel contentPane;
	private JLabel subjectLabel;
	private JLabel toLabel;
	private JButton forwardButton;
	private JButton sentB;
	private JButton sentBdraft;
	
	private JTextField toField;
	private JTextField subjectField;
	private JTextArea textArea;
	
	private String userName;
	//private String input4;
	private String inboxDate;
	private String to;
	private String subject;
	private String content;
	
	private JButton discardButton;
	
	
	
	public ComposeInbox(String usr,String from, String subject, String msg,String date) {
		this.userName = usr;
		this.inboxDate = date;
		GUI();
			  
		
		try {
			toField.setText(from);
			subjectField.setText("Re: "+subject);
			textArea.setText("\n" +"On Date:"+ date+"."+ from +" wrote:\n"+msg);
			
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		setVisible(true);
	}

	private class sentBButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textArea.setEditable(false);
			inboxDate = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			to = toField.getText();
			subject = subjectField.getText();
			
		
				
		   content = textArea.getText();
			                                                    
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
						preparedStmt.setString(5, inboxDate);
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
	
	private class SPAMButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String subject = subjectField.getText();
			String to = toField.getText();

			String  content = textArea.getText();
			
		
			String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			
			
			
			
			
			
			
			
			
			
			try {
				text n = new text();
				n.getFrames();
				
				//(subject + to + content +date);
				
			} catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}

			dispose();
		}

	}

	private class forwardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Forward f = new Forward(userName,inboxDate);
			setVisible(false);

		}
	}
	

	private class sentBdraftButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String subject = subjectField.getText();
			String to = toField.getText();

			String  content = textArea.getText();
			
		
			String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			try {
				Connection conn = getConnection();

				String query = "INSERT INTO `user`.`draft` (`sender`, `receiver`, `subject`, `content`,`date`) VALUES (?, ?, ?,?, ?)";// "insert
											
   															
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, userName);
				preparedStmt.setString(2, to );
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
		textArea.setEditable(false);

		sentB = new JButton("Reply");
		sentB.setFont(new Font("Times New Roman", 1, 20));
		sentB.setBounds(200, 400, 120, 40);
		contentPane.add(sentB);
		sentB.addActionListener(new sentBButtonListener());

		// draft botton
		sentBdraft = new JButton("Save as Draft");
		sentBdraft.setFont(new Font("Times New Roman", 1, 20));
		sentBdraft.setBounds(20, 400, 150, 40);
		contentPane.add(sentBdraft);
		sentBdraft.addActionListener(new sentBdraftButtonListener());

		forwardButton = new JButton("Forward");
		forwardButton.setFont(new Font("Times New Roman", 1, 20));
		forwardButton.setBounds(350, 400, 120, 40);
		contentPane.add(forwardButton);
		forwardButton.addActionListener(new forwardButtonListener());

		JButton SPAMButton = new JButton("Spam");
		SPAMButton.setBounds(507, 33, 79, 73);
		contentPane.add(SPAMButton);
		SPAMButton.addActionListener(new SPAMButtonListener());
		
		
		discardButton = new JButton("Delete");
		discardButton.setFont(new Font("Times New Roman", 1, 20));
		discardButton.setBounds(500, 400, 120, 40);
		contentPane.add(discardButton);
		discardButton.addActionListener(new discardButtonListener());
		//getContentPane().setBackground(SystemColor.activeCaption);
		
	}
	private class discardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  ResultSet re = st.executeQuery("SELECT * FROM user.info2");
					 while(re.next()) {
						if(inboxDate.equals(re.getString("date"))) {
							String query1 = "DELETE FROM info2 WHERE date = '"+inboxDate+"' ";
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
	//HomePage hp=new HomePage(user);
		//	JBU
		}
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
