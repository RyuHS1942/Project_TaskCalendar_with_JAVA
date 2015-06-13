
public class Data {
	
	static int year, month, date, day;
	static String user;
	static boolean check;
	static String DoList;
	static String memo;
	
	public Data(int year,int month,int date,int day,
			String user,boolean check,String DoList,String memo){
		this.year = year;
		this.month = month;
		this.date = date;
		this.day = day;
		this.user = user;
		this.check = check;
		this.DoList = DoList;
		this.memo = memo;
	}
	
	public int getyear(){
		return year;
	}
	
	public int getmonth(){
		return month;
	}
	
	public int getdate(){
		return date;
	}
	
	public int getday(){
		return day;
	}
	
	public String getuser(){
		return user;
	}
	
	public boolean getcheck(){
		return check;
	}
	
	public String getDoList(){
		return DoList;
	}
	
	public String getmemo(){
		return memo;
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

	public void setday(int day){
		this.day = day;
	}
	
	public void setuser(String user){
		this.user =user;
	}
	
	public void setcheck(boolean check){
		this.check =check;
	}
	
	public void setDoList(String DoList){
		this.DoList = DoList;
	}
	
	public void setmemo(String memo){
		this.memo = memo;
	}
}
