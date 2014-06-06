/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author aviad elitzur
 */
public interface ConnectionInterface  extends Remote{
    public abstract void setName(String name)throws RemoteException;
    public abstract int getID() throws RemoteException;
    public abstract String getName() throws RemoteException;
}
