package connectionRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author aviad elitzur
 */
public class RMItest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
 
    ConnectionInterface c1= RMIclient.getConnectionByFactory(1, "david");
    ConnectionInterface c2= RMIclient.getConnectionByFactory(2, "moshe");
    ConnectionInterface c3= RMIclient.getConnectionByFactory(3, "omer");
    
    c1.setName("aviad");
    c2.setName("tzvi");
    
    System.out.println(c1.getName());
    System.out.println(c3.getName());
    
    
    
    }
}
