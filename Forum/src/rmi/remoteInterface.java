package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;

import allcode.report;
public interface remoteInterface extends Remote{
	public report login(String userName, String pass) throws RemoteException;
}
