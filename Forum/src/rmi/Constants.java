
package rmi;
 
public class Constants {
        private static String RMI_ID = "RMI!";
        private static int RMI_PORT = 200;
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
        public static void setPort(int i){
                RMI_PORT=i;
        }
       
}