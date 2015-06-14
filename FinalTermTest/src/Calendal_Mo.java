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
	int year,month,date,dayorder;//사용할 날자
	int ty,tm,td,to;
	
	int startP;
	int tempSP;
	String[] day = {"SUN","MON","TUS","WED","THU","FRI","SAT"};//요일
	int Month[] = {31,28,31,30,31,30,31,31,30,31,30,31};//날자 수;
	int gap[] = {3,0,3,2,3,2,3,3,2,3,2,3};
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
	Move Move = new Move();
	DateMo DateMo = new DateMo();
	
	public int getyear(){
		return year;
	}
	
	public int getmonth(){
		return month;
	}
	
	public int getdate(){
		return date;
	}
	
	public int getdayorder(){
		return dayorder;
	}
	
	public void setyear(int year){
		this.year = year;
	}
	
	public void setmonth(int month){
		this.month = month;
	}
	
	public void setdate(int date){
		this.date = date;
	}
	
	public void setdayorder(int dayorder){
		this.dayorder = dayorder;
	}
	
	public void CalendarMo(){
		this.setTitle("Calendal");
		this.setSize(700, 400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
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
		UpP.add(TodayBut);
		
		LeftMove = new JButton("<<");
		LeftMove.addActionListener(Move);
		UpP.add(LeftMove);
				
		Leftmove = new JButton("<");
		Leftmove.addActionListener(Move);
		UpP.add(Leftmove);
		
		YM = new JLabel((year)+"  /  "+(month));
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
	
	public void setToday(){//오늘 날자를 불러오기
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		date = cal.get(Calendar.DATE);//오늘 날자
		dayorder = cal.get(Calendar.DAY_OF_WEEK);
		ty = year;
		tm = month;
		td = date;
		to = dayorder;
		System.out.println(tm+"/"+td+"/"+to);
	}
	
	public void February(){//윤년 계산
		if(year%4==0&&year%100 !=0||year%400==0){
			 Month[1]++;
		}else{
			Month[1] = 28;
		}
	}
	
	public int StartP(){
		return dayorder - (date%7) + 1;
	}
	public void InCalendar(){
		February();
		int num = 0;
		int temp = DayOrder(StartP()+tempSP);
		//
		for(int i=0;i<6;i++){
			for(int j=0;j<day.length;j++){
				if(temp>0){
					Date[i][j] = 0;
					temp--;
				}else if(temp==0 && num<Month[month]+1){
					Date[i][j] = num++;
				}else{
					Date[i][j] = 0;
				}
			}
		}
	}
	
	private String toString(int i) {
		String j;
		j = i+"";
		return j;
	}
	
	public void ShowCalendar(){//달력의 스켈레톤
		InCalendar();
		
		for(int i=0;i<day.length;i++){
			dayB = new JButton(day[i]);//비활성화
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
				date = Date[i][j];
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
	
	public String dayordertoday(int dayorder){//요일을 문자로
		return day[dayorder];
	}
	
	public int DY(){
		int temp = 6;
		return temp;
	}
	
	public int DM(){
		int temp = 0;
		temp -= gap[month-1];
		if(temp<1){
		while(temp<1){
			temp += 7;
		}}
		return temp;
	}
	
	public int AM(){
		int temp = 0;
		if(month-2<0){
			temp += gap[11];
		}else
		temp += gap[month-2];
		if(temp>8){
		while(temp>8){
			temp -= 7;
		}}
		return temp;
	}
	
	public int AY(){
		int temp = 1;
		return temp;
	}
	public int DayOrder(int dayorder){
		while(dayorder<1){
			dayorder += 7;
		}while(dayorder>8){
			dayorder -= 7;
		}
		return dayorder;
	}
	
	public void removeCalender(){
		for(int i=0;i<6*7;i++){
			DownP.removeAll();
		}
	}

	public void moveMonth(int mon){
		month += mon;
		if(month>12){
			while(month>12){
				year++;
				month -= 12;
			}
		}else if(month<1){
			while(month<1){
				year--;
				month += 12;
			}
		}
	}
	
	class Move implements ActionListener{
		public void actionPerformed(ActionEvent e){
			removeCalender();
			if(e.getSource()==LeftMove){
				moveMonth(-12);
				tempSP = DY();
				YM.setText((year)+"  /  "+(month));
			}else if(e.getSource()==Leftmove){
				moveMonth(-1);
				tempSP = DM();
				YM.setText((year)+"  /  "+(month));
			}else if(e.getSource()==Rightmove){
				moveMonth(+1);
				tempSP = AM();
				YM.setText((year)+"  /  "+(month));
			}else if(e.getSource()==RightMove){
				moveMonth(+12);
				tempSP = AY();
				YM.setText((year)+"  /  "+(month));
			}
			ShowCalendar();
		}
	}
	
	class DateMo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Date_Mo Date = new Date_Mo();
			Date.DateMo();
		}
	}
}

