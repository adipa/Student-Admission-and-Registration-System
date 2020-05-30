package studAdmReg;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;

public class StudentAdmReg {

	private JFrame frame;
	
	Connection conn =null ;
	PreparedStatement pst =null;
	ResultSet rs = null;
	
	private JTextField txtDOB;
	private JTextField txtMotherName;
	private JTextField txtLastName;
	private JTextField txtMiddleName;
	private JTextField txtFirstName;
	private JTextField txtGR;
	private JTextField txtNationality;
	private JTextField txtStudentCont;
	private JTextField txtParentContact;
	private JTextField txtSSC;
	private JTextField txtHSC;
	private JTextField txtEmail;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JComboBox<String> comboBloodGrp;
	private JComboBox<String> comboCaste;
	private JComboBox<String> comboGP;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StudentAdmReg window = new StudentAdmReg();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	/**
	 * Create the application.
	 */
	public StudentAdmReg() {
		initialize();
		conn=SqliteConnection.ConnectDb();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_1.setBounds(1044, 132, 316, 425);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 296, 403);
		panel_1.add(textArea);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String GR = txtGR.getText();
				String FirstName = txtFirstName.getText();
				String MiddleName = txtMiddleName.getText();
				String LastName = txtLastName.getText();
				String MotherName = txtMotherName.getText();
				String DOB = txtDOB.getText();
				String BloodGrp = (String) comboBloodGrp.getSelectedItem();
				String Caste = (String) comboCaste.getSelectedItem();
				String Nationality = txtNationality.getText();
				String Contact = txtStudentCont.getText();
				String ParentContact = txtParentContact.getText();
				String SSC = txtSSC.getText();
				String HSC = txtHSC.getText();
				String Email = FirstName.toLowerCase() + "." + LastName.toLowerCase() + "17@vit.edu";
				txtEmail.setText(Email);
				String GP = (String) comboGP.getSelectedItem();
				
				//String sql = "Insert into Student (GR, First_Name) Values ('" + GR + "','" + FirstName + "');";
				String sql = "Insert into Student (GR, First_Name , Middle_Name , Last_Name , Mother_Name , " + 
							"DOB , Blood_Group, Caste, Nationality , Contact , Parent_Contact , SSC , HSC , Email, GP) Values " + 
							"('" + GR +"', " +
							"'" + FirstName +"', " +
							"'" + MiddleName +"', " +
							"'" + LastName +"', " +
							"'" + MotherName +"', " +
							"'" + DOB +"', " +
							"'" + BloodGrp +"', " +
							"'" + Caste +"', " +
							"'" + Nationality +"', " +
							"'" + Contact +"', " +
							"'" + ParentContact +"', " +
							"'" + SSC +"', " +
							"'" + HSC +"', " +
							"'" + Email +"', " +
							"'" + GP +"');";
						
				try {
				pst=conn.prepareStatement(sql);
		    	pst.executeUpdate();
		    	JOptionPane.showMessageDialog(null, "Student added successfully",
						"Add new student",JOptionPane.INFORMATION_MESSAGE);
		    	reset();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAdd.setBounds(14, 610, 179, 38);
		frame.getContentPane().add(btnAdd);
		
		JButton btnTranscript = new JButton("Transcript");
		btnTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append("Student Information \n"
						+"===========================================\n"
						+"Student GR no.: "+txtGR.getText()
						+"\nFirst Name: "+txtFirstName.getText()
						+"\nMiddle Name: "+txtMiddleName.getText()
						+"\nLast Name : "+txtLastName.getText()
						+"\nMother's Name : "+txtMotherName.getText()
						+"\nDate Of Birth : "+txtDOB.getText()
						+"\nBlood group: "+comboBloodGrp.getSelectedItem()
						+"\nCaste : "+comboCaste.getSelectedItem()
						+"\nNationality : "+txtNationality.getText()
						+"\nStudent Phone No. :"+txtStudentCont.getText()
						+"\nParent Phone No.: "+txtParentContact.getText()
						+"\n10th Marks : "+txtSSC.getText()
						+"\n12th Marks : "+txtHSC.getText()
						+"\nE-mail: "+txtEmail.getText()
						+"\nStudent Subjects \n"
						+"===========================================\n"
						+"GP: "+comboGP.getSelectedItem()
						);
			}
		});
		btnTranscript.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnTranscript.setBounds(593, 610, 179, 38);
		frame.getContentPane().add(btnTranscript);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat("Printing in progress");
				MessageFormat footer = new MessageFormat("Page {0,number,integer}");
				
				try 
				{
					textArea.print();
					
				}
				catch(java.awt.print.PrinterException ev){
					System.err.format("NO PRINTER FOUND " ,ev.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPrint.setBounds(786, 610, 179, 38);
		frame.getContentPane().add(btnPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String GR = txtGR.getText();
				String sql = "Delete From Student Where GR ='" + GR + "';"; 
				
				try {
					pst=conn.prepareStatement(sql);
			    	pst.executeUpdate();
			    	JOptionPane.showMessageDialog(null, "Student Deleted Successfully",
							"Delete Student",JOptionPane.INFORMATION_MESSAGE);
			    	//reset();
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
				}
			});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDelete.setBounds(979, 610, 179, 38);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame =new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame ,"Confirm if you want to exit ", "Student Admission & Registration", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setBounds(1172, 610, 179, 38);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_2.setBounds(682, 132, 352, 425);
		frame.getContentPane().add(panel_2);
		
		textField_12 = new JTextField();
		textField_12.setText("CS1001 : Electrical Engineering");
		textField_12.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_12.setColumns(10);
		textField_12.setBounds(10, 58, 332, 35);
		panel_2.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText("CS1002 : Electronics Engineering");
		textField_13.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_13.setColumns(10);
		textField_13.setBounds(10, 101, 332, 35);
		panel_2.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setText("CS1003 : Computer Programming");
		textField_14.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_14.setColumns(10);
		textField_14.setBounds(10, 147, 332, 35);
		panel_2.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setText("CS1004 : Biomedical Technology");
		textField_15.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_15.setColumns(10);
		textField_15.setBounds(10, 193, 332, 35);
		panel_2.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setText("CS1005 : Engineering Design and Innovations (EDI)");
		textField_16.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_16.setColumns(10);
		textField_16.setBounds(10, 239, 332, 35);
		panel_2.add(textField_16);
		
		JLabel label_14 = new JLabel("General Proficiency");
		label_14.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_14.setBounds(10, 283, 189, 35);
		panel_2.add(label_14);
		
		String[] GPs = new String[] {"", "Drawing", "Guitar", "Taekwondo", "Violin", "Yoga", "Photography"};
		comboGP = new JComboBox(GPs);
		comboGP.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboGP.setBounds(10, 319, 332, 33);
		panel_2.add(comboGP);
		
		JLabel lblSubjectRegistration = new JLabel("Subject Registration");
		lblSubjectRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblSubjectRegistration.setBounds(10, 11, 288, 40);
		panel_2.add(lblSubjectRegistration);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(10, 132, 662, 425);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Student GR Number");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(27, 62, 131, 33);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Student First Name");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_1.setBounds(27, 106, 131, 33);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Student Middle Name");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_2.setBounds(27, 150, 131, 33);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Student Last Name");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_3.setBounds(27, 194, 131, 33);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Mother's First Name");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_4.setBounds(27, 238, 131, 33);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Date of Birth (DD/MM/YY)");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_5.setBounds(27, 282, 131, 33);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Blood Group");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_6.setBounds(27, 326, 131, 33);
		panel.add(label_6);
		
		String[] bloodGroups = new String[] {"", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
		comboBloodGrp = new JComboBox(bloodGroups);
		comboBloodGrp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBloodGrp.setBounds(168, 332, 143, 33);
		panel.add(comboBloodGrp);
		
		txtDOB = new JTextField();
		txtDOB.setColumns(10);
		txtDOB.setBounds(168, 283, 143, 33);
		panel.add(txtDOB);
		
		txtMotherName = new JTextField();
		txtMotherName.setColumns(10);
		txtMotherName.setBounds(168, 238, 143, 33);
		panel.add(txtMotherName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(168, 195, 143, 33);
		panel.add(txtLastName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(168, 151, 143, 33);
		panel.add(txtMiddleName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(168, 107, 143, 33);
		panel.add(txtFirstName);
		
		txtGR = new JTextField();
		txtGR.setColumns(10);
		txtGR.setBounds(168, 62, 143, 33);
		panel.add(txtGR);
		
		JLabel label_7 = new JLabel("Caste");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_7.setBounds(321, 62, 131, 33);
		panel.add(label_7);
		
		String[] Castes = new String[] {"", "General", "OBC", "SC/ST", "Other"};
		comboCaste = new JComboBox(Castes);
		comboCaste.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboCaste.setBounds(482, 62, 143, 33);
		panel.add(comboCaste);
		
		JLabel label_8 = new JLabel("Nationality");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_8.setBounds(321, 106, 131, 33);
		panel.add(label_8);
		
		txtNationality = new JTextField();
		txtNationality.setColumns(10);
		txtNationality.setBounds(482, 107, 143, 33);
		panel.add(txtNationality);
		
		JLabel label_9 = new JLabel("Student Phone No.");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_9.setBounds(321, 150, 131, 33);
		panel.add(label_9);
		
		txtStudentCont = new JTextField();
		txtStudentCont.setColumns(10);
		txtStudentCont.setBounds(482, 150, 143, 33);
		panel.add(txtStudentCont);
		
		JLabel label_10 = new JLabel("Parent Phone No.");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_10.setBounds(321, 194, 131, 33);
		panel.add(label_10);
		
		txtParentContact = new JTextField();
		txtParentContact.setColumns(10);
		txtParentContact.setBounds(482, 193, 143, 33);
		panel.add(txtParentContact);
		
		JLabel label_11 = new JLabel("10th Percentage");
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_11.setBounds(321, 238, 131, 33);
		panel.add(label_11);
		
		txtSSC = new JTextField();
		txtSSC.setColumns(10);
		txtSSC.setBounds(482, 238, 143, 33);
		panel.add(txtSSC);
		
		JLabel label_12 = new JLabel("12th Percentage");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_12.setBounds(321, 282, 131, 33);
		panel.add(label_12);
		
		txtHSC = new JTextField();
		txtHSC.setColumns(10);
		txtHSC.setBounds(482, 282, 143, 33);
		panel.add(txtHSC);
		
		JLabel label_13 = new JLabel("E-mail ID");
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_13.setBounds(321, 326, 131, 33);
		panel.add(label_13);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(482, 326, 143, 33);
		panel.add(txtEmail);
		
		JLabel lblNewLabel = new JLabel("Student Information");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(27, 11, 288, 40);
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(20, 11, 1319, 93);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 93, 93);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Admission And Registration");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblNewLabel_2.setBounds(408, 5, 523, 77);
		panel_3.add(lblNewLabel_2);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String GR = txtGR.getText();
				String sql = "Select * From Student Where GR = '" + GR + "';"; 
				
				try {
					pst=conn.prepareStatement(sql);
			    	rs=pst.executeQuery();
			    	while(rs.next()) {
			    		txtFirstName.setText(rs.getString("First_Name"));
			    		txtMiddleName.setText(rs.getString("Middle_Name"));
			    		txtLastName.setText(rs.getString("Last_Name"));
			    		txtMotherName.setText(rs.getString("Mother_Name"));
			    		txtDOB.setText(rs.getString("DOB"));
			    		comboBloodGrp.setSelectedItem(rs.getString("Blood_Group"));
			    		comboCaste.setSelectedItem(rs.getString("Caste"));
			    		txtNationality.setText(rs.getString("Nationality"));
			    		txtStudentCont.setText(rs.getString("Contact"));
			    		txtParentContact.setText(rs.getString("Parent_Contact"));
			    		txtSSC.setText(rs.getString("SSC"));
			    		txtHSC.setText(rs.getString("HSC"));
			    		txtEmail.setText(rs.getString("Email"));
			    		comboGP.setSelectedItem(rs.getString("GP"));
			    		
			    	}
			    	
				} catch (SQLException ex) {
						ex.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setBounds(207, 610, 179, 38);
		frame.getContentPane().add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReset.setBounds(400, 610, 179, 38);
		frame.getContentPane().add(btnReset);
	}


	public void reset() {
		txtGR.setText("");
		txtFirstName.setText("");
		txtMiddleName.setText("");
		txtLastName.setText("");
		txtMotherName.setText("");
		txtDOB.setText("");
		comboBloodGrp.setSelectedItem("");
		comboCaste.setSelectedItem("");
		txtNationality.setText("");
		txtStudentCont.setText("");
		txtParentContact.setText("");
		txtSSC.setText("");
		txtHSC.setText("");
		txtEmail.setText("");
		comboGP.setSelectedItem("");
		textArea.setText("");
		
	}
}