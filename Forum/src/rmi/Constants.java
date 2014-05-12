package rmi;

public class Constants {
	private static String RMI_ID = "RMI!";
	private static int RMI_PORT = 223;
	private static boolean requestFlag=false;
	public static int getPortIncrease(){
		RMI_PORT++;
		return RMI_PORT;
	}
	public static String getIDIncrease(){
		RMI_ID = RMI_ID.concat("*");
		return RMI_ID;
	}
	public static String getID(){
		return RMI_ID;
	}
	public static int getPort(){
		return RMI_PORT;
	}
	public static boolean getFlag(){
		return requestFlag;
	}
	public static void setFlag(boolean b){
		requestFlag = b;
	}
}
