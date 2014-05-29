package allcode;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {

		Member mem=new Member("tzvi","shapira","a@b");
		Post post= new Post(mem, "hi","im here",1);
		System.out.println("im doing database!");
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.save(post);   
		  ss.getTransaction().commit();  
		  ss.close();  
		

	}

}
