import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Login_Mo extends JFrame{
	
	JPanel LoginP;
	JLabel LoginL;
	JTextField LoginTF;
	JButton LoginB;
	
	
	
	public Login_Mo(){
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
		//ActionListenr
		ListenForButton LFB = new ListenForButton();
		LoginB.addActionListener(LFB);
		LoginP.add(LoginB);
		
		this.add(LoginP);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public String get_User(){
		return LoginTF.getText();
	}
	
	class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Calendal_Mo Calendal = new Calendal_Mo();
			setVisible(false);
		}
	}
}
