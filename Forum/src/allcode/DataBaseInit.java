package allcode;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;  



public class DataBaseInit {
	
	public static Configuration configuration;
	public static ServiceRegistry serviceRegistry;
	public static SessionFactory sf;

	public DataBaseInit(){

	}
	public static void initialize(){
		configuration=new Configuration();  

		 

		configuration.configure(); 
		System.out.println("im initializing!");

		serviceRegistry = 
				new StandardServiceRegistryBuilder().applySettings( configuration.getProperties() ).build(); 

		sf = configuration.buildSessionFactory(serviceRegistry);
	}
}
