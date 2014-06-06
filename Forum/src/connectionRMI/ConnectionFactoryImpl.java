
package connectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * server factory for connections
 * @author aviad elitzur
 */
public class ConnectionFactoryImpl extends UnicastRemoteObject implements
        ConnectionFactory{

   	private static final long serialVersionUID = 1L;

	protected ConnectionFactoryImpl() throws RemoteException {
    super();
    
    try {
        Registry registry = LocateRegistry.createRegistry(200);
        registry.rebind("aviad_elitzur_connections", this);
        System.out.println("connections factory registered.");
    } catch (Exception e) {
        System.err.println("Error registering connections service factory: "
                + e.getMessage());
    }
}
    
    
    @Override
    public ConnectionInterface createConnection(int id, String name) throws RemoteException {
       return new Connection(id, name); 
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
