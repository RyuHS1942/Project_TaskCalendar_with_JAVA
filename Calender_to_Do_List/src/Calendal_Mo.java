import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calendal_Mo<todaydate> extends JFrame{
	int year,month,date,dayorder;//����� ����
	int ty,tm,td,to;
	
	int series = 0;
	
	int datei;
	int datej;
	
	int tempSPA =0;
	int tempSP = 0;
	String[] day = {"SUN","MON","TUS","WED","THU","FRI","SAT"};//����
	int Month[] = {31,28,31,30,31,30,31,31,30,31,30,31};//���� ��;
	int gap[] = {3,3,0,3,2,3,2,3,3,2,3,2,3};
	int Date[][] = new int[6][day.length]; 
	
	JPanel CalendarP;
	JPanel UpP;
	JPanel DownP;
	
	JButton TodayBut;
	JButton LeftMove;
	JButton Leftmove;
	JLabel YM;
	JButton RightMove;
	JButton Rightmove;
	
	JButton dayB;
	JButton dateB[][] = new JButton[6][day.length];
	
	Calendar cal = Calendar.getInstance();

	ArrayList<Data> Data = new ArrayList<Data>();
	
	Move Move = new Move();
	DateMo DateMo = new DateMo();
	TodayMove TodayMove = new TodayMove();
	Login_Mo Login_Mo;//check
	public void CalendarMo(Login_Mo Login_Mo){
		this.setTitle("Calendal");
		this.setSize(700, 400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.Login_Mo = Login_Mo;//check
		CalendarP = new JPanel();
		CalendarP.setSize(690,390);
		CalendarP.setLayout(new BorderLayout());
		this.add(CalendarP,BorderLayout.CENTER);
		
		setToday();
		
		UpP = new JPanel();
		UpP.setSize(680,50);
		CalendarP.add(UpP,BorderLayout.NORTH);
		UpP.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		
		TodayBut = new JButton(ty+"/"+tm+"/"+td);
		TodayBut.addActionListener(TodayMove);
		UpP.add(TodayBut);
		
		LeftMove = new JButton("<<");
		LeftMove.addActionListener(Move);
		UpP.add(LeftMove);
				
		Leftmove = new JButton("<");
		Leftmove.addActionListener(Move);
		UpP.add(Leftmove);
		
		YM = new JLabel((ty)+"  /  "+(tm));
		YM.setSize(60, 0);
		UpP.add(YM);
		
		Rightmove = new JButton(">");
		Rightmove.addActionListener(Move);
		UpP.add(Rightmove);
		
		
		RightMove = new JButton(">>");
		RightMove.addActionListener(Move);
		UpP.add(RightMove);
		
		DownP = new JPanel();
		DownP.setSize(685,340);
		CalendarP.add(DownP,BorderLayout.CENTER);
		DownP.setLayout(new GridLayout(7,7));

		ShowCalendar();
				
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setToday(){//���� ���ڸ� �ҷ�����
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		date = cal.get(Calendar.DATE);//���� ����
		dayorder = cal.get(Calendar.DAY_OF_WEEK);
		ty = year;
		tm = month+1;
		td = date;
		to = dayorder;
		System.out.println(tm+"/"+td+"/"+to);
	}
	
	public void February(){//���� ���
		if(ty%4==0&&ty%100 !=0||ty%400==0){
			 Month[1] = 29;
		}else{
			Month[1] = 28;
		}
	}
	
	public int StartP(){//���� ���� 1���� ����
		return dayorder - (date%7) + 1;
	}
	
	public void InCalendar(int tempSPA){
		int num = 1;
		int temp = 0;
		temp = DayOrder(StartP()+tempSPA);
		
		February();
		for(int i=0;i<6;i++){
			for(int j=0;j<day.length;j++){
				if(temp>1){
					Date[i][j] = 0;
					temp--;
				}else if(temp==1 && num<=Month[tm-1]){
					Date[i][j] = num++;
				}else{
					Date[i][j] = 0;
				}
			}
		}
	}
	
	public int CaltempSPA(){
		 tempSPA += tempSP;
		 return tempSPA;
	}
	
	private String toString(int i) {
		String j;
		j = i+"";
		return j;
	}
	
	public void ShowCalendar(){//�޷��� ���̷���
		InCalendar(CaltempSPA());
		
		for(int i=0;i<day.length;i++){
			dayB = new JButton(day[i]);//��Ȱ��ȭ
			dayB.setEnabled(false);
			DownP.add(dayB);
			if(i==0){
				dayB.setBackground(Color.RED);
			}else if(i==6){
				dayB.setBackground(Color.BLUE);
			}
		}
		for(int i=0;i<6;i++){
			for(int j=0;j<day.length;j++){
				dateB[i][j]=new JButton();
				dateB[i][j].addActionListener(DateMo);
				if(Date[i][j]==0){
					dateB[i][j].setText("");
					dateB[i][j].setEnabled(false);
				}else{
					dateB[i][j].setText(toString(Date[i][j]));
				}
				DownP.add(dateB[i][j]);
			}
		}
	}
	
	public String dayordertoday(int dayorder){//������ ���ڷ�
		return day[dayorder-1];
	}
	
	public int DY(){
		int temp = 0;;
		if(((ty)%4==0)&&(ty)%100 !=0||(ty)%400==0){
			temp = -1;
		}else if((((ty+1)%4==0)&&(ty+1)%100 !=0||(ty+1)%400==0)){
			temp = +1;
		}
		for(int i=0;i<Month.length;i++){
			temp -= Month[i];
		}
		temp = DayOrder(temp);
		return temp;
	}
	
	public int DM(){
		int temp = 0;
		temp -= gap[tm];
		if(temp<1){
			while(temp<1){
				temp += 7;
			}
		}
		return temp;
	}
	
	public int AM(){
		int temp = 0;
		temp += gap[tm-1];
		if(temp>7){
		while(temp>7){
			temp -= 7;
		}}
		return temp;
	}
	
	public int AY(){
		int temp=0;
		for(int i=0;i<Month.length;i++){
			temp += Month[i];
		}
		temp = DayOrder(temp);
		return temp;
	}
	public int DayOrder(int dayorder){//1~7������ ���ڷ� �����
		while(dayorder<1){
			dayorder += 7;
		}while(dayorder>7){
			dayorder -= 7;
		}
		return dayorder;
	}
	
	public void removeCalender(){//�޷� ����
		for(int i=0;i<6*7;i++){
			DownP.removeAll();
		}
	}

	public void moveMonth(int mon){//���� �̵�
		tm += mon;
		if(tm>12){
			while(tm>12){
				ty++;
				tm -= 12;
			}
		}else if(tm<1){
			while(tm<1){
				ty--;
				tm += 12;
			}
		}
	}
	
	class Move implements ActionListener{
		public void actionPerformed(ActionEvent e){
			removeCalender();
			if(e.getSource()==LeftMove){
				moveMonth(-12);
				tempSP = DY();
			}else if(e.getSource()==Leftmove){
				moveMonth(-1);
				tempSP = DM();
			}else if(e.getSource()==Rightmove){
				moveMonth(+1);
				tempSP = AM();
			}else if(e.getSource()==RightMove){
				moveMonth(+12);
				tempSP = AY();	
			}
			YM.setText((ty)+"  /  "+(tm));
			ShowCalendar();
		}
	}
	
	class DateMo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			for(int i=0;i<6;i++){
				for(int j=0;j<day.length;j++){
					if(e.getSource()==dateB[i][j]){
						datei = i;
						datej = j;
					}
				}
			}
			Date_Mo Date_Mo = new Date_Mo();
			Date_Mo.DateMo(Calendal_Mo.this, Data);
		}
	}
	
	class TodayMove implements ActionListener{
		public void actionPerformed(ActionEvent e){
			removeCalender();
			setToday();
		}
	}
}

