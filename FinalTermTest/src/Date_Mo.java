import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Date_Mo extends JFrame{
	int year, month, date, dayorder;
	String user;
	String doit, memo;
	boolean check;
	
	JPanel Date;
	JPanel UpP;
	JPanel DownP;
	
	JButton add;
	JButton delete;
	
	JLabel List;
	JLabel Memo;
	
	JTextField list;
	JCheckBox box;
	
	Calendal_Mo Calendal = new Calendal_Mo(year, month, date, dayorder);
	Login_Mo Login = new Login_Mo(user);
	ArrayList<Data> Data = new ArrayList<Data>();
	
	public Date_Mo(boolean check, String doit, String memo){
		this.check = check;
		this.doit = doit;
		this.memo = memo;
	}
	
	public void DateMo(){
		this.setTitle(Login.user+"/"+Calendal.year+"/"+
				Calendal.month+"/"+Calendal.date);
		this.setSize(400,700);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		Date = new JPanel();
		Date.setSize(395,695);
		Date.setLayout(new BorderLayout());
		this.add(Date,BorderLayout.CENTER);
		
		UpP = new JPanel();
		UpP.setSize(390,350);
		Date.add(UpP,BorderLayout.NORTH);
		UpP.setLayout(new BorderLayout());
		
		DownP = new JPanel();
		DownP.setSize(390,300);
		Date.add(DownP,BorderLayout.CENTER);
		DownP.setLayout(new BorderLayout());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//끝내면 저장하는 방식으로
		this.setVisible(true);
	}
	
	public void addDoList(){
		int year,month,date,day;
		String user,DoList,memo;
		boolean check;
		
		year = Calendal.year;
		month = Calendal.month;
		date = Calendal.date;
		day = Calendal.dayorder;
		user = Login.get_User();
		check = ListC.getSelectedIcon() != null;
		DoList = ListTF.getText();
		memo = MemoA.getText();
		Data.add(new Data(year,month,date,day,user,check,DoList,memo));
	}
	
	public void deleteDoList(){
		int temp=0;
		Data.remove(temp);
	}
	
	public void saveData(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try{
			fout = new FileOutputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(Data);
			oos.reset();
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		}
	}
	
	public void loadData(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try{
			fin = new FileInputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			ois = new ObjectInputStream(fin);
			
			//정보 불러오기
			//할 일 추가
			//메모에 추가
			
		}catch(Exception e){
			System.out.println();
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		}
		
	}
}
