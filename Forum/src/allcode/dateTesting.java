package allcode;





import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
 
 
public class dateTesting {
  public static void main(String[] args) throws Exception {
 
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	   System.out.println(date.getTime());
	   
	   System.out.println(dateFormat.format(date));
	   Thread.sleep(2002);
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
	   System.out.println(dateFormat.format(cal.getTime()));
	   Date date2 = new Date();
	   long diffInMillies = date2.getTime() - date.getTime();
	   
	   
	   System.out.println(TimeUnit.MILLISECONDS.convert(diffInMillies,TimeUnit.MILLISECONDS));
 
  }
}