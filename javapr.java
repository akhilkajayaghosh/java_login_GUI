import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.lang.Exception;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
class SignUP extends JFrame{
	JTextField t1,t2;
	JButton b1;
	JLabel l1,l2,l3;
	SignUP()
	{
		setLayout(null);
		setTitle("Register");
		l1=new JLabel("Usename");
		l2=new JLabel("Password");
		t1=new JTextField(60);
		t2=new JTextField(60);
		b1=new JButton("Submit");

		t1.setBounds(250,120, 120, 20);
		t2.setBounds(250,80, 120, 20);
		l1.setBounds(150,75,120,20);
		l2.setBounds(150,115,120,20);
		
		b1.setBounds(260,220, 90, 30);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(b1);

	};
}

class login extends JFrame{
	JTextField t1;
	JPasswordField t2;
	JButton b1,b2;
	JLabel l1,l2,l3,l4;
	login(){
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("LOGIN");
		t2=new JPasswordField(60);
		b1=new JButton("LOGIN");
		t1=new JTextField(60);
		l1=new JLabel("LOGIN PAGE");
		l1.setFont(new Font("Times New Roman",Font.BOLD,30));
		l1.setForeground(Color.BLUE);
		l2=new JLabel("username");
	    l3=new JLabel("password");
		b2=new JButton("SIGH UP");
		l4=new JLabel("Not a User ?");
		
		t2.setBounds(250,120,120,20);
		t1.setBounds(250,80,120,20);
		l2.setBounds(150,75,120,20);
		b1.setBounds(260,220,90,30);
		l1.setBounds(150,5,200,50);
		l3.setBounds(150,115,120,20);
		b2.setBounds(140,220,90,30);
		l4.setBounds(70,225,220,25);
		
		add(l3);
		add(t1);
		add(t2);
		add(l1);
		add(b1);
		add(l2);
		add(l4);
		add(b2);
		
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				Statement smt=con.createStatement();
				String sql="Select * from details where username='"+t1.getText()+"' and password='"+String.valueOf(t2)+"' ";
			    ResultSet rs=smt.executeQuery(sql);
				if(rs.next())
				    JOptionPane.showMessageDialog(null, "Login Successfully");
			     else
				    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                con.close();}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});

		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent af)
			{
				SignUP s=new SignUP();
				s.setVisible(true);
				s.setBounds(200,200,500,300);
				try{
				String user=t1.getText();
				String password=String.valueOf(t2);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			    PreparedStatement pst =con.prepareStatement("insert into details(username,password) values(?,?)");
				pst.setString(1,user);
				pst.setString(2,password);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"Data added");}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		
		

	
	}
		
}
public class javapr{
public static void main(String arr[]){
	    login l=new login();
		l.setBounds(550, 200,500,300);
		l.setVisible(true);
	}
}