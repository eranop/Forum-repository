package connectionRMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author aviad elitzur
 */
public class Connection extends UnicastRemoteObject implements ConnectionInterface{
    
	private static final long serialVersionUID = 1L;
	private int _id;
    private String _name;
    
    public Connection(int id, String name)throws RemoteException{
        super();
        _id=id;
        _name=name;
    }
    
    @Override
    public void setName(String name){
        _name=name;
    }
    @Override
    public int getID() {
        return _id;
    }
    @Override
    public String getName(){
        return _name;
    }
    @Override
    public String toString(){
        return "name: " + _name + " id: " + _id;
    }
    
    
}
