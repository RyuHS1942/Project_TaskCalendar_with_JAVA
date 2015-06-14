import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Date_Mo extends JFrame{
	JScrollPane MemoS;
	JTextArea MemoA;
	
	JLabel MemoL;
	JButton AddB;
	JButton DelB;
	JPanel BLP;
	
	Calendal_Mo Calendal = new Calendal_Mo();
	Login_Mo Login = new Login_Mo();
	ArrayList<Data> Data = new ArrayList<Data>();
	AddBut AddBut = new AddBut();
	DelBut DelBut = new DelBut();

	public void DateMo(){
		this.setTitle(Calendal.year+"/"+Calendal.month+"/"+Calendal.date);
		this.setLayout(new BorderLayout());
		
		AddB = new JButton();
		AddB.setText("ADD");
		AddB.addActionListener(AddBut);
		
		DelB = new JButton();
		DelB.setText("DEL");
		DelB.addActionListener(DelBut);
		
		MemoL = new JLabel();
		MemoL.setText("Memo");
		
		MemoA = new JTextArea(10,40);
		MemoS = new JScrollPane();
		MemoS = new JScrollPane(MemoA);
		MemoS.setEnabled(false);
		
		BLP = new JPanel();
		BLP.setLayout(new FlowLayout());
		BLP.add(AddB);
		BLP.add(DelB);
		
		this.add(BLP,BorderLayout.NORTH);
		this.add(MemoS,BorderLayout.CENTER);
		
		this.pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				saveData();
				dispose();
			}
		});
		this.setVisible(true);
	}
	
	public void addData(){//메모장으로 추가 시킬 것
		int year,month,date,day;
		String user,memo;
		
		year = Calendal.year;
		month = Calendal.month;
		date = Calendal.date;
		day = Calendal.dayorder;
		user = Login.get_User();
		memo = MemoA.getText();
		Data.add(new Data(year,month,date,day,user,memo));
	}
	
	public void delData(){//데이터 자체를 삭제하기
		int temp=0;
		Data.remove(temp);
	}
	
	public static void schData(){
		
	}
	
	public void saveData(){
		ArrayList<Data> Data = new ArrayList<Data>();
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
		ArrayList<Data> Data = new ArrayList<Data>();
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try{
			fin = new FileInputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			ois = new ObjectInputStream(fin);
			
			ArrayList data = (ArrayList) ois.readObject();
			for(int i=0;i<data.size();i++){
				Data.add((Data) data.get(i));
			}
			
		}catch(Exception e){
			System.out.println();
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		}
	}
	class AddBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addData();
		}
	}
	class DelBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			delData();
		}
	}
}
