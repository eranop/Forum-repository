package allcode;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class DateManagment {

	public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static DateManagment instance = null;

	private DateManagment() {
		// Exists only to defeat instantiation.
	}
	public static DateManagment getDateManagmentInstance() 
	{
		if(instance == null)
			instance = new DateManagment();
		return instance;
	}

	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) 
	{
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public static int getDateDiffDays(Date date1, Date date2)
	{
		return (int)getDateDiff(date1, date2, TimeUnit.DAYS);
	}
	
	/**
	 * getting the current date.
	 * @return returns the current date , hour, minute, second.
	 */
	public static Date getDate()
	{
		return new Date();
	}
	
}