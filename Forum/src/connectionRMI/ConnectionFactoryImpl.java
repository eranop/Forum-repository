
package connectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import allcode.ForumsManagement;
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
	protected ConnectionFactoryImpl(SiteManager sm) throws RemoteException {
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
	public ConnectionInterface createConnection() throws RemoteException {
		return new Connection(_sm);
	}

	public static void main(String[] args) {
		if(args.length != 3){
                    System.out.println("number of arguments not ok");
                    return;
		}
		try {
			SiteManager sm = InitializeSystem.init(args[0], args[1], args[2]);
			new ConnectionFactoryImpl(sm);
		} catch (RemoteException e) {
			System.err.println("Error creating echo service factory: "
					+ e.getMessage());
		}
	}



}
