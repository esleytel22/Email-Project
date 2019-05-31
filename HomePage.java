
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.TextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import java.awt.List;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Choice;

import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class HomePage extends JFrame {
	private JList jList;
	private JPanel contentPane;
	private JPanel textPanel;
	private String user;
	
	public HomePage(String user) {
		setTitle("Home");
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 554);
		contentPane = new JPanel();
		//contentPane.setBackground(SystemColor.gray);
		contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome!  "+this.user);
		//lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Papyrus", Font.PLAIN, 20));
	
		lblWelcome.setBounds(5, 22, 274, 39);
		contentPane.add(lblWelcome);
		
	
		JButton btnInbox = new JButton("Inbox");
		btnInbox.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnInbox.setBounds(10, 110, 120, 35);
		contentPane.add(btnInbox);
		btnInbox.addActionListener(new btnInboxButtonListener());
		
		JButton btnSentMail = new JButton("Sent Items");
		btnSentMail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSentMail.setBounds(10, 185, 120, 35);
		contentPane.add(btnSentMail);
		btnSentMail.addActionListener(new btnSentMailButtonListener());
		
	
		JButton btnDraft = new JButton("Drafts");
		btnDraft.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDraft.setBounds(10, 260, 120, 40);
		contentPane.add(btnDraft);
		btnDraft.addActionListener(new btnDraftButtonListener());
		
		JButton btnCompose = new JButton("Compose");
		btnCompose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCompose.setBounds(10, 335, 120, 40);
		contentPane.add(btnCompose);
		btnCompose.addActionListener(new btnComposeButtonListener());
	
	    textPanel = new JPanel();
		textPanel.setBounds(127, 110, 680, 628);
			
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLogOut.setBounds(541, 21, 117, 40);
		contentPane.add(btnLogOut);
		btnLogOut.addActionListener(new btnLogOutButtonListener());	
		
		
		int count =0,i= 0;
		  try {
			  Connection conn = getConnection();
			  Statement st = conn.createStatement();
		
			  ResultSet re1 = st.executeQuery("SELECT * FROM user.info2");	
				while (re1.next()) {
					count++;
				}
				String[] months =  new String[count];
				
			  ResultSet re  = st.executeQuery("SELECT * FROM user.info2");
				 while(re.next()) {
					   String receiver = re.getString("receiver");
			            String sender = re.getString("sender");
			            String subject = re.getString("subject");
			            String content = re.getString("content");
			            String date = re.getString("date");
			           					 
					  if(receiver.equalsIgnoreCase(user)) {						 									
						  months[i] = "From :"+sender +". Time :"+date+". Subject :"+subject + ". Message :"+content;
							i++;						 
					  }					 
				 }
				 
			
				 	textPanel.removeAll();
					jList = new JList(months);
					
					
					//jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					jList.addListSelectionListener(new ListListener1());
					
					//
					JScrollPane jsp= new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					jList.setFixedCellHeight(30);
					jList.setFixedCellWidth(550);
					
					textPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
					contentPane.add(textPanel);
					
					JButton btnSpam = new JButton("SPAM");
					btnSpam.setBounds(234, 31, 89, 23);
					contentPane.add(btnSpam);
					setVisible(true);
					// 
				
				 conn.close();

				  }	
				  catch(Exception ex)
				  {
				  System.out.println("ERROR: " + ex.getMessage());
				  }	

		
		setVisible(true);
	}
	
	public String userName() {
		return user; 
	}

	  private class btnInboxButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			  
			  int count =0,i= 0;
			  try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
			
				  ResultSet re1 = st.executeQuery("SELECT * FROM user.info2");	
					while (re1.next()) {
						count++;
					}
					String[] months =  new String[count];
					
				  ResultSet re  = st.executeQuery("SELECT * FROM user.info2");
					 while(re.next()) {
						   String receiver = re.getString("receiver");
				            String sender = re.getString("sender");
				            String subject = re.getString("subject");
				            String content = re.getString("content");
				            String date = re.getString("date");
				           					 
						  if(receiver.equalsIgnoreCase(user)) {						 									
							  months[i] = "From :"+sender +". Time :"+date+". Subject :"+subject + ". Message :"+content;
								i++;						 
						  }					 
					 }
					 	textPanel.removeAll();
						jList = new JList(months);
						//jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						jList.addListSelectionListener(new ListListener1());
						
						//
						JScrollPane jsp= new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						jList.setFixedCellHeight(30);
						jList.setFixedCellWidth(550);
						
						textPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
						contentPane.add(textPanel);
						setVisible(true);
						// 
					
					 conn.close();

					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
			 
			
		  }  
	  }
	  private class ListListener1 implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
			          return;
			
				String selectedInfo = (String) jList.getSelectedValue();// This selection contains whole string
				//String DateTime =selectedInfo.substring(25,44);
				
				//getting from
				int fromIndex = selectedInfo.indexOf(". Time :", 0);
				String fromStr=selectedInfo.substring(6,fromIndex);
				//getting time
				int timeIndex = selectedInfo.indexOf(". Subject :", 0);
				String timeStr=selectedInfo.substring(fromIndex+8,timeIndex);
				//getting time
				int subjectIndex = selectedInfo.indexOf(". Message :", 0);
				String subjectStr=selectedInfo.substring(timeIndex+11,subjectIndex);
				//getting Message
				String messageStr=selectedInfo.substring(subjectIndex+11,selectedInfo.length());
				
				//Testing variables		
				System.out.println(selectedInfo+
						"\n"+fromIndex+" "+fromStr+
						"\n"+timeIndex+" "+timeStr+
						"\n"+subjectIndex+" "+subjectStr+
						"\n"+messageStr);
				
				
				//open a compose windows with info in it already
				ComposeInbox c = new ComposeInbox(user,fromStr,subjectStr,messageStr,timeStr);
			}
		}
	  
	  private class btnSentMailButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) { 
			 
			  int count =0,i= 0;
			  try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  
				  ResultSet re1 = st.executeQuery("SELECT * FROM user.info2");	
					while (re1.next()) {
						count++;
					}
					String[] months =  new String[count];
					
				  ResultSet re  = st.executeQuery("SELECT * FROM user.info2");
					 while(re.next()) {
						   String receiver = re.getString("receiver");
				            String sender = re.getString("sender");
				            String subject = re.getString("subject");
				            String content = re.getString("content");
				            String date = re.getString("date");
				           					 
						  if(sender.equalsIgnoreCase(user)) {						 									
							  months[i] = "To :"+receiver +". Time :"+date+". Subject :"+subject + ". Message :"+content;
								i++;						 
						  }					 
					 }
					 textPanel.removeAll();
						jList = new JList(months);
						//jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						jList.addListSelectionListener(new ListListener2());
						
						//
						JScrollPane jsp= new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						jList.setFixedCellHeight(30);
						jList.setFixedCellWidth(550);
						
						textPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
						contentPane.add(textPanel);
						setVisible(true);
						//
						conn.close();

					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
		  }  
	  }
	  private class ListListener2 implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
			          return;
				
				
				String selectedInfo = (String) jList.getSelectedValue();// This selection contains whole string
				//String DateTime =selectedInfo.substring(25,44);
				
				//getting to
				int toIndex = selectedInfo.indexOf(". Time :", 0);
				String toStr=selectedInfo.substring(4,toIndex);
				//getting time
				int timeIndex = selectedInfo.indexOf(". Subject :", 0);
				String timeStr=selectedInfo.substring(toIndex+8,timeIndex);
				//getting time
				int subjectIndex = selectedInfo.indexOf(". Message :", 0);
				String subjectStr=selectedInfo.substring(timeIndex+11,subjectIndex);
				//getting Message
				String messageStr=selectedInfo.substring(subjectIndex+11,selectedInfo.length());
				
				//Testing variables		
				System.out.println(selectedInfo+
						"\n"+toIndex+" "+toStr+
						"\n"+timeIndex+" "+timeStr+
						"\n"+subjectIndex+" "+subjectStr+
						"\n"+messageStr);
				
				
				//open a compose windows with info in it already
				ComposeSent c = new ComposeSent(user,toStr,subjectStr,messageStr,timeStr);
			
			}
		}
	  private class btnDraftButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {

			  int count =0,i= 0;
			  try {
				  Connection conn = getConnection();
				  Statement st = conn.createStatement();
				  
				  ResultSet re1 = st.executeQuery("SELECT * FROM user.draft");	
					while (re1.next()) {
						count++;
					}
					String[] months =  new String[count];
					
				  ResultSet re  = st.executeQuery("SELECT * FROM user.draft");
					 while(re.next()) {
						   String receiver = re.getString("receiver");
				            String sender = re.getString("sender");
				            String subject = re.getString("subject");
				            String content = re.getString("content");
				            String date = re.getString("date");
				           					 
						  if(sender.equalsIgnoreCase(user)) {						 									
							  months[i] = "From :"+sender +". To :"+receiver +". Time :"+date+". Subject :"+subject + ". Message :"+content;
								i++;						 
						  }					 
					 }
					 textPanel.removeAll();
						jList = new JList(months);
						//jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						jList.addListSelectionListener(new ListListener3());
						
						//
						JScrollPane jsp= new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						jList.setFixedCellHeight(30);
						jList.setFixedCellWidth(550);
						
						textPanel.add(new JScrollPane(jList), BorderLayout.CENTER);
						contentPane.add(textPanel);
						setVisible(true);
						//
					 conn.close();

					  }	
					  catch(Exception ex)
					  {
					  System.out.println("ERROR: " + ex.getMessage());
					  }	
		
		  }  
	  }

	  
	  private class ListListener3 implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
			          return;
				String selectedInfo = (String) jList.getSelectedValue();// This selection contains whole string
				//String DateTime =selectedInfo.substring(25,44);
				
				//getting from
				int fromIndex = selectedInfo.indexOf(". To :", 0);
				String fromStr=selectedInfo.substring(6,fromIndex);
				System.out.println(selectedInfo+"\n"+fromStr);
				//getting to
				int toIndex = selectedInfo.indexOf(". Time :", 0);
				String toStr=selectedInfo.substring(fromIndex+6,toIndex);
				//getting time
				int timeIndex = selectedInfo.indexOf(". Subject :", 0);
				String timeStr=selectedInfo.substring(toIndex+8,timeIndex);
				//getting time
				int subjectIndex = selectedInfo.indexOf(". Message :", 0);
				String subjectStr=selectedInfo.substring(timeIndex+11,subjectIndex);
				//getting Message
				String messageStr=selectedInfo.substring(subjectIndex+11,selectedInfo.length());
				
				//Testing variables		
				System.out.println(selectedInfo+
						"\n"+fromIndex+" "+fromStr+
						"\n"+toIndex+" "+toStr+
						"\n"+timeIndex+" "+timeStr+
						"\n"+subjectIndex+" "+subjectStr+
						"\n"+messageStr);
				
				
				//open a compose windows with info in it already
				ComposeDraft c = new ComposeDraft(user,toStr,fromStr,subjectStr,messageStr,timeStr);
			}
		}
	  
	  private class btnLogOutButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			    dispose();
				Email t= new Email();
	
		  }  
	  }
	  private class btnComposeButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			
			  ComposeDraft c = new ComposeDraft(user);
			
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
	

	  public static void main(String[] args) {
			HomePage h = new HomePage("aaaa@yg.com");
			
		}
}

