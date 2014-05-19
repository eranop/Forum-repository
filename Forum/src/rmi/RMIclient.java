
package rmi;
 
import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class RMIclient {
        /**
         * @param args
         * @throws RemoteException
         * @throws NotBoundException
         * @throws InterruptedException
         */
        public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
                Registry reg = LocateRegistry.getRegistry(null, Constants.getPortIncrease());
                remoteInterface remote = (remoteInterface) reg.lookup(Constants.getID());
                System.out.println(remote.login("somename", "pass"));
        }
 
}
 
 