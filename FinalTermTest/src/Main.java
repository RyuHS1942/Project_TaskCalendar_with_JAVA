import java.util.Calendar;


public class Main {
	public static void main(String[] args){
		String user = null;
		
		Login_Mo Login = new Login_Mo(user);
		//Login.LoginMo();
		Date_Mo date = new Date_Mo(true, null, null);
		date.DateMo();
	}
}
