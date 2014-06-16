
package connectionRMI;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
	protected ConnectionFactoryImpl() throws RemoteException {
		super();

		try {
			//System.setProperty("java.rmi.server.hostname", "132.73.198.149");
			_sm=InitializeSystem.init("aviad", "123", "aviadelitzur@gmail.com");
			Registry registry = LocateRegistry.createRegistry(200);
			registry.rebind("132.73.204.3", this);
			System.out.println("connections factory registered.");
		} catch (Exception e) {
			System.err.println("Error registering connections service factory: "
					+ e.getMessage());
		}
	}


	@Override
	public RemoteInterface createConnection() throws RemoteException {
		return new RemoteImpl(_sm.openNewConnection()); 
	}

	public static void main(String[] args) throws IOException {
		try {
			rmiSetup();
			new ConnectionFactoryImpl();
		} catch (RemoteException e) {
			System.err.println("Error creating echo service factory: "
					+ e.getMessage());
		}
	}
	public static void rmiSetup() throws IOException{
		// This whole song and dance is necessary in order to figure out our Global Address so
		// Java Remote Method Invocation works correctly. EK
		// See http://docs.oracle.com/javase/6/docs/technotes/guides/rmi/faq.html#domain

		String hostname = InetAddress.getLocalHost().getHostName();
		System.out.println("Searching for global address of " + hostname);

		String globalIp4address = null;
		String globalIp6address = null;

		InetAddress [] inetAddresses = InetAddress.getAllByName(hostname);
		for (InetAddress inetAddress : inetAddresses) {
		    System.out.println("hostname is " + inetAddress.getHostName());
		    System.out.println("address  is " + inetAddress.getHostAddress());
		    boolean isSiteLocalAddress = inetAddress.isSiteLocalAddress();
		    boolean isLinkLocalAddress = inetAddress.isLinkLocalAddress();
		    boolean isLoopbackAddress = inetAddress.isLoopbackAddress();
		    boolean isReachable = inetAddress.isReachable(10000);
		    System.out.println("isSiteLocalAddress  = " + isSiteLocalAddress);
		    System.out.println("isLinkLocalAddress  = " + isLinkLocalAddress);
		    System.out.println("isLoopbackAddress   = " + isLoopbackAddress);
		    System.out.println("isReachable         = " + isReachable);
		    if (isReachable && !isSiteLocalAddress && !isLinkLocalAddress && !isLoopbackAddress) {
		        if (inetAddress instanceof Inet4Address) {
		            globalIp4address = inetAddress.getHostAddress();
		        }
		        if (inetAddress instanceof Inet6Address) {
		            globalIp6address = inetAddress.getHostAddress();
		        }
		    }
	}
	

	// It is extremely important that this be set correctly or you can waste endless hours
	// troubleshooting why your RMI application does not work. EK
	System.setProperty("java.rmi.server.hostname", globalIp4address);
	System.out.println("java.rmi.server.hostname = " + System.getProperty("java.rmi.server.hostname"));

}
}
