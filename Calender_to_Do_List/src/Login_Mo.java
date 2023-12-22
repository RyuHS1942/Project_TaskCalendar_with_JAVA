import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Login_Mo extends JFrame{
	static String user;
	JPanel LoginP;
	JLabel LoginL;
	JTextField LoginTF;
	JButton LoginB;
	public String getuser(){
		return user;
	}
	public void LoginMo(){
		this.setTitle("Login");
		this.setSize(250,100);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		LoginP = new JPanel();
		
		LoginL = new JLabel("User");
		LoginP.add(LoginL);
		
		LoginTF = new JTextField(10);
		
		LoginP.add(LoginTF);
		
		LoginB = new JButton("enter");
		ListenForButton LFB = new ListenForButton();
		LoginB.addActionListener(LFB);
		LoginP.add(LoginB);
		
		this.add(LoginP);
		
		System.out.println(LoginTF.getText());
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			user = LoginTF.getText();
			Calendal_Mo Calendal = new Calendal_Mo();
			Calendal.CalendarMo(Login_Mo.this);
			dispose();
		}
	}
}
