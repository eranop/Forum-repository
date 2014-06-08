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
 
    ConnectionInterface c1= RMIclient.getConnectionByFactory();
    ConnectionInterface c2= RMIclient.getConnectionByFactory();
    ConnectionInterface c3= RMIclient.getConnectionByFactory();
    
    
    
    
    }
}
