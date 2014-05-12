package rmi;

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
		Constants.setFlag(true);
		Thread.sleep(5000);
		int tmp1 = Constants.getPortIncrease();
		String tmp2 = Constants.getIDIncrease();
		System.out.print("started new client on port: "+tmp1);
		System.out.print("started new client on id: "+tmp2);
		Registry reg = LocateRegistry.getRegistry(null, tmp1);
		remoteInterface remote = (remoteInterface) reg.lookup(tmp2);
		System.out.println(remote.login("somename", "pass"));
	}

}
