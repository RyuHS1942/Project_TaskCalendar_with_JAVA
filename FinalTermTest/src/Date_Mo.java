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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
	int series = 0;
	int year,month,date;
	String user,memo;
	
	JScrollPane MemoS;
	JTextArea MemoA;
	JTextField MemoTF;
	
	JButton AddB;
	JButton DelB;
	JButton ModB;
	JButton SchB;
	JPanel BLP;
	
	AddBut AddBut = new AddBut();
	DelBut DelBut = new DelBut();
	ModBut ModBut = new ModBut();
	SchBut SchBut = new SchBut();
	
	Calendal_Mo CalendalMo;
	
	ArrayList<Data> Data = new ArrayList<Data>();

	public void DateMo(Calendal_Mo CalendalMo){
		this.setLayout(new BorderLayout());
		this.CalendalMo = CalendalMo;
		
		user = Login_Mo.user;
		year = CalendalMo.ty;
		month = CalendalMo.tm;
		date = CalendalMo.Date[CalendalMo.datei][CalendalMo.datej];

		this.setTitle(user+"/"+CalendalMo.ty+"/"+CalendalMo.tm+"/"+CalendalMo.Date[CalendalMo.datei][CalendalMo.datej]);
		
		MemoTF = new JTextField(10);
		
		AddB = new JButton();
		AddB.setText("ADD");
		AddB.addActionListener(AddBut);
		
		DelB = new JButton();
		DelB.setText("DEL");
		DelB.addActionListener(DelBut);
		
		ModB = new JButton();
		ModB.setText("MOD");
		ModB.addActionListener(ModBut);
		
		SchB = new JButton();
		SchB.setText("SCH");
		SchB.addActionListener(SchBut);
		
		MemoA = new JTextArea(10,40);
		MemoA.setEnabled(false);
		MemoS = new JScrollPane();
		MemoS = new JScrollPane(MemoA);
		
		BLP = new JPanel();
		BLP.setLayout(new FlowLayout());
		BLP.add(MemoTF);
		BLP.add(AddB);
		BLP.add(DelB);
		BLP.add(ModB);
		BLP.add(SchB);
		
		this.add(BLP,BorderLayout.NORTH);
		this.add(MemoS,BorderLayout.CENTER);
		
		this.pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){
				loadData();
			}
		});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				saveData();
				dispose();
			}
		});
		this.setVisible(true);
	}
	
	public void addData(){//메모장으로 추가 시킬 것
		MemoA.append(MemoTF.getText()+"\n");
	}
	
	public void delData(){//데이터 자체를 삭제하기
		MemoA.setText("");
	}
	
	public void modData(){
		MemoA.setText(MemoTF.getText());
	}
	
	public void schData(){
		for(int i=0;i<Data.size();i++){
			if(MemoTF.getText().contains(Data.get(i).getmemo())){
				MemoA.append(Data.get(i).toString());
			}
		}
	}
	
	public void saveData(){
		
		memo = MemoA.getText();
		Data.add(new Data(series,user,year,month,date,memo));
		System.out.println(series+"/"+user+"/"+year+"/"+month+"/"+date+"/"+memo);
		series++;
		
		FileWriter fw = null;
		try{
			fw = new FileWriter("C:\\Users\\ryu\\Downloads\\data.txt");
			for(int i=0;i<Data.size();i++){
				fw.write(series+"^^"+user+"^^"+
						year+"^^"+month+"^^"+
						date+"^^"+memo+"\n");
				}
		}catch(Exception ex){
		}//finally{
			//try{
				//fw.close();
			//}catch(IOException e){}
		//}
	}
	
	public void loadData(){
		BufferedReader br = null;
		try{
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:\\Users\\ryu\\Downloads\\data.txt"));
			while((sCurrentLine = br.readLine()) != null){
				String[] info = sCurrentLine.split("^^");
				for(int i=0;i<Data.size();i++){
					if(info[1].equals(Login_Mo.user)){
						System.out.println(Login_Mo.user);
						if(info[2].equals(year)){
							System.out.println(year);
							if(info[3].equals(month)){
								System.out.println(month);
								if(info[4].equals(date)){
									System.out.println(date);
									MemoA.append(Data.get(i).getmemo());
								}
							}
						}
					}
				}
			}
		}catch(Exception e){}
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
	class ModBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			modData();
		}
	}
	class SchBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			schData();
		}
	}
}
