import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Date_Mo extends JFrame{
	int year, month, date, check;
	String user;
	String doit, memo;
	
	JPanel ListP;
	JLabel ListL;
	JButton ListAdd;
	JCheckBox ListC;
	JTextField ListF;
	JButton ListDelete;
	JScrollPane ListS;
	
	JPanel MemoP;
	JLabel MemoL;
	JTextArea MemoA;
	JScrollPane MemoS;
	
	Calendal_Mo Calendal = new Calendal_Mo();
	Login_Mo Login = new Login_Mo();
	
	public Date_Mo(){
		this.setTitle(Calendal.year+"/"+Calendal.month+"/"+Calendal.day);
		this.setSize(350,400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		ListP = new JPanel();
		ListP.setSize(300,200);
		
		ListL = new JLabel("Do it List");
		ListP.add(ListL);
		
		ListAdd = new JButton("+");
		ListP.add(ListAdd);
		
		MemoP = new JPanel();
		MemoP.setSize(300,200);
		
		MemoL = new JLabel("Memo");
		MemoP.add(MemoL);
		
		MemoA = new JTextArea();
		
		this.add(ListP,MemoP);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//끝내면 저장하는 방식으로
		this.setVisible(true);
	}
	
	public void usercheck(){
		if(user.equals(Login.get_User())){
			
		}else{}
	}
	
	public void addDoList(){
		
	}
	
	public void deleteDoList(){
		
	}
	
	public void saveData(){
		
	}
	
	public void loadData(){
		
	}
	
	public boolean getCheck(){//체크 확인
		if(check==1){
			return true;
		}else{
			return false;
		}
	}
}
