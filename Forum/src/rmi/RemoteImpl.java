package rmi;
import rmi.remoteInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import allcode.UserConection;
import allcode.report;

public class RemoteImpl extends UnicastRemoteObject implements remoteInterface{
	
	private UserConection uc;
	private static final long serialVersionUID = 1L;

	protected RemoteImpl(UserConection uc) throws RemoteException {
		super();
		this.uc=uc;
	}

	@Override
	public report login(String userName, String pass) throws RemoteException{
		return this.uc.login(userName, pass);
	}
	
}
