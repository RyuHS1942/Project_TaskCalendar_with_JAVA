import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Login_Mo extends JFrame{
	String user;
	
	JPanel LoginP;
	JLabel LoginL;
	JTextField LoginTF;
	JButton LoginB;
	
	public Login_Mo(String user){
		this.user = user;
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
		user = LoginTF.getText();
		LoginP.add(LoginTF);
		
		LoginB = new JButton("enter");
		ListenForButton LFB = new ListenForButton();
		LoginB.addActionListener(LFB);
		LoginP.add(LoginB);
		
		this.add(LoginP);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public String get_User(){
		return user;
	}
	
	class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int year = 0;
			int month = 0;
			int date = 0;
			int dayorder = 0;
			
			Calendal_Mo Calendal = new Calendal_Mo(year, month, date, dayorder);
			Calendal.CalendarMo();
			
			setVisible(false);
		}
	}
}
