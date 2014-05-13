package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import allcode.*;
import allcode.UserConnection;;

public class RMIserver {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 */
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		SiteManager sm = InitializeSystem.init("avi","123","a@b");
		RMIforUC(sm.openNewConnection());
		while(true){
			if(Constants.getFlag()==true){
				RMIforUC(sm.openNewConnection());
				Constants.setFlag(false);
				System.out.println("check");
			}
		}
	}

	public static void RMIforUC(UserConnection uc) throws RemoteException, AlreadyBoundException{
		int tmp1 = Constants.getPort();
		String tmp2 = Constants.getID();
		
		RemoteImpl ri = new RemoteImpl(uc);
		Registry reg = LocateRegistry.createRegistry(tmp1);
		reg.bind(tmp2,ri);
		System.out.print("server has started for a userconnection called "+uc.getID() +" on port "+tmp1+"and id "+tmp2);
	}

}
