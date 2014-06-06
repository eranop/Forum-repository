package connectionRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class RMIserver {

	/**
	 * @param args
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {    
		try{
			Connection c = new Connection(0, "me");
			System.out.println("connection " + c.toString() + "created");
			Registry reg = LocateRegistry.createRegistry(200);
			reg.bind("aviad_elitzur_connections",c);
		}catch(RemoteException | AlreadyBoundException e){
			System.out.println("Exception: " + e);
		}
	}

}
