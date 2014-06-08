
package rmi;
 
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
 
import allcode.*;
import allcode.UserConnection;
 
public class RMIserver {
 
        /**
         * @param args
         * @throws RemoteException
         * @throws AlreadyBoundException
         */
       
        public static void main(String[] args) throws RemoteException, AlreadyBoundException {
                SiteManager sm = InitializeSystem.init("avi","123","aviadelitzur@gmail.com");
                int i=200;
                while(i<210){
                        RMIforUC(sm.openNewConnection(), i);
                        i++;
                }
                Constants.setPort(200);
        }
 
        public static void RMIforUC(UserConnection uc, int i) throws RemoteException, AlreadyBoundException{
                RemoteImpl ri = new RemoteImpl(uc);
                Registry reg = LocateRegistry.createRegistry(i);
                reg.bind(Constants.getID(),ri);
                System.out.print("server has started for a userconnection called "+uc.getID() +" on port "+i+" and id "+Constants.getID()+'\n');
        }
 
}
 