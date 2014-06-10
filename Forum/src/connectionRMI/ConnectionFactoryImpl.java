
package connectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import allcode.InitializeSystem;
import allcode.SiteManager;

/**
 * server factory for connections
 * @author aviad elitzur
 */
public class ConnectionFactoryImpl extends UnicastRemoteObject implements
        ConnectionFactory{

   	private static final long serialVersionUID = 1L;
   	private SiteManager _sm;
	protected ConnectionFactoryImpl() throws RemoteException {
    super();
    
    try {
    	_sm=InitializeSystem.init("aviad", "123", "aviadelitzur@gmail.com");
        Registry registry = LocateRegistry.createRegistry(200);
        registry.rebind("aviad_elitzur_connections", this);
        System.out.println("connections factory registered.");
    } catch (Exception e) {
        System.err.println("Error registering connections service factory: "
                + e.getMessage());
    }
}
    
    
    @Override
    public RemoteInterface createConnection() throws RemoteException {
       return new RemoteImpl(_sm.openNewConnection()); 
    }
    
    public static void main(String[] args) {
    try {
        new ConnectionFactoryImpl();
    } catch (RemoteException e) {
        System.err.println("Error creating echo service factory: "
                + e.getMessage());
    }
}
    
}
