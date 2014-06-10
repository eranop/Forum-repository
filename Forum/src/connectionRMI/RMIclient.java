package connectionRMI;

import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIclient {
	public static ConnectionInterface getConnection() throws RemoteException, NotBoundException{
		try{
			Registry reg = LocateRegistry.getRegistry(null, 200);
			ConnectionInterface remote = (ConnectionInterface) reg.lookup("aviad_elitzur_connections");
			return remote;
		}catch(RemoteException | NotBoundException e){
			System.out.println("Exception: "+ e);
		}
		return null;
	}
	/*
	 * use factory to get different objects of connections (because it use state)
	 */
	public static RemoteInterface getConnectionByFactory(){
		try {
			Registry registry = LocateRegistry.getRegistry(200);

			ConnectionFactory serviceFactory =
					(ConnectionFactory) registry.lookup("aviad_elitzur_connections");
			RemoteInterface c= serviceFactory.createConnection();
			return c;
		} catch (RemoteException | NotBoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}

