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
public interface ConnectionFactory  extends Remote {
    RemoteInterface createConnection() throws RemoteException;    
}
