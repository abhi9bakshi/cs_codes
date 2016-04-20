 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


class RegistrationFormDesign {

	JLabel register_label,name_label,gender_label,username_label,label5,pwd_label,login_user,login_pwd,label9,male_label,female_label,register_name_label,s1,s2,s3,login_label;
	JPanel panel;
	JFrame jf;
	JButton register,login;
	JTextField txtname,email_text,login_email;
	JPasswordField pwdfield,pwd_textbox;
	JRadioButton radiobutton2,radiobutton3;
	String register_name,register_username,register_pwd,gender;
	String login_username,login_password;
	
	//String name,gender,dob1,dob2,dob,dobb,password,mobileNumber,email,area,state,nationality,selectedState;
	//JComboBox combobox1;
	//Connection con1;
	//Statement st1;
	//int index,count;

	public RegistrationFormDesign()
	{
		initComponents();
		event();
	}

	public void initComponents() 
	{
		jf=new javax.swing.JFrame("Registration Form");
		
		panel=new javax.swing.JPanel();
		jf.add(panel);
		//panel.setBackground(new Color(191,239,255));
		panel.setLayout(null);
		jf.setSize(3000,3000);
		jf.show();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		register_label=new javax.swing.JLabel("Registration Form");
		register_label.setFont(new Font("Dialog", Font.ITALIC, 24));
		register_label.setBounds(250,20,400,40);
		panel.add(register_label);

		name_label=new javax.swing.JLabel("Name");
		name_label.setFont(new Font("Dialog", Font.BOLD, 20));
		name_label.setBounds(100,80,150,40);
		panel.add(name_label);
		
		txtname=new javax.swing.JTextField();
		txtname.setFont(new Font("Dialog", Font.BOLD, 16));
		txtname.setBounds(350,80,180,30);
		panel.add(txtname);

		gender_label=new javax.swing.JLabel("Gender");
		gender_label.setFont(new Font("Dialog", Font.BOLD, 20));
		gender_label.setBounds(100,140,150,40);
		panel.add(gender_label);
		
		male_label=new javax.swing.JLabel("Male");
		male_label.setFont(new Font("Dialog", Font.BOLD, 20));
		male_label.setBounds(350,140,60,40);
		panel.add(male_label);

		female_label=new javax.swing.JLabel("Female");
		female_label.setFont(new Font("Dialog", Font.BOLD, 20));
		female_label.setBounds(460,140,100,40);
		panel.add(female_label);

		radiobutton2=new javax.swing.JRadioButton();
		radiobutton2.setFont(new Font("Dialog", Font.BOLD, 16));
		radiobutton2.setBounds(420,140,20,30);
		panel.add(radiobutton2);
		
		radiobutton3=new javax.swing.JRadioButton();
		radiobutton3.setFont(new Font("Dialog", Font.BOLD, 16));
		radiobutton3.setBounds(570,140,20,30);
		panel.add(radiobutton3);
		
		ButtonGroup jb = new ButtonGroup();
		jb.add(radiobutton2);
		jb.add(radiobutton3);

		username_label=new javax.swing.JLabel("Username");
		username_label.setFont(new Font("Dialog", Font.BOLD, 20));
		username_label.setBounds(100,200,150,40);
		panel.add(username_label);
		
		email_text=new javax.swing.JTextField();
		email_text.setFont(new Font("Dialog", Font.BOLD, 16));
		email_text.setBounds(350,200,180,30);
		panel.add(email_text);
		
		pwd_label=new javax.swing.JLabel("Password");
		pwd_label.setFont(new Font("Dialog", Font.BOLD, 20));
		pwd_label.setBounds(100,260,150,40);
		panel.add(pwd_label);
		
		pwdfield=new javax.swing.JPasswordField();
		pwdfield.setFont(new Font("Dialog", Font.BOLD, 16));
		pwdfield.setBounds(350,270,180,30);
		panel.add(pwdfield);
		
		
		register = new javax.swing.JButton("Register");
		register.setFont(new Font("Dialog", Font.BOLD, 16));
		register.setBounds(250,320,100,30);
		panel.add(register);
		
		login_label=new javax.swing.JLabel("Sign in");
		login_label.setFont(new Font("Dialog", Font.ITALIC, 24));
		login_label.setBounds(250,370,150,40);
		panel.add(login_label);

	
		login_user=new javax.swing.JLabel("Enter Username");
		login_user.setFont(new Font("Dialog", Font.BOLD, 20));
		login_user.setBounds(100,450,150,40);
		panel.add(login_user);
		
		login_email=new javax.swing.JTextField();
		login_email.setFont(new Font("Dialog", Font.BOLD, 16));
		login_email.setBounds(350,450,180,30);
		panel.add(login_email);
		
		login_pwd=new javax.swing.JLabel("Enter Password");
		login_pwd.setFont(new Font("Dialog", Font.BOLD, 20));
		login_pwd.setBounds(100,500,150,40);
		panel.add(login_pwd);

		pwd_textbox=new javax.swing.JPasswordField();
		pwd_textbox.setFont(new Font("Dialog", Font.BOLD, 16));
		pwd_textbox.setBounds(350,500,180,30);
		panel.add(pwd_textbox);

		login = new javax.swing.JButton("Sign In");
		login.setFont(new Font("Dialog", Font.BOLD, 16));
		login.setBounds(250,550,100,30);
		panel.add(login);

	}
	
	

	public void event() {

		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
				{
				
				try 
				{
					if(radiobutton2.isSelected())
					{
						gender="Male";
					}
					else
					{
						gender="";
					}
					
						register_name=txtname.getText();
						register_username=email_text.getText();
						register_pwd=MD5Calculation.encrypt(pwdfield.getText(),"MD5");
						Database obj=new Database();
						obj.insert(register_name, gender, register_username, register_pwd);
					
				}
				catch (Exception e) 
				{
					// TODO: handle exception
				}
					
				}
		});
		
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
				{
					try 
					{
						login_username=login_email.getText();
						login_password=pwd_textbox.getText();
						String hash=MD5Calculation.encrypt(login_password,"MD5");
						String original=Database.retrieve(login_username);
						if(hash.equals(original))
						{
							System.out.println("Login successfull");
							JOptionPane.showMessageDialog(jf, "Login successfull");
						}
						else
						{
							System.out.println("Login failed");
							JOptionPane.showMessageDialog(jf, "Login Failed","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
					}
					
				}
		});


	}

}

class RegistrationForm {
	public static void main(String args[]) {
		RegistrationFormDesign form = new RegistrationFormDesign();
		System.out.println("Registration Form");

	}
}

