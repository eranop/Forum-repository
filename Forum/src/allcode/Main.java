package allcode;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {
		
		
		//SiteManager sm=InitializeSystem.init("avi", "aaa", "a@b");
		//System.out.println(sm._fm.getForums().size());
		//sm._fm.getForums().get(0).get_subForums().get(0).getAllPosts().get(0).showPost();
		//System.out.println(sm._fm.getForums().get(0).createSubForum("dogs", "blabla"));
		DataBaseInit.initialize();
		Forum forum=new Forum("animals", "kinds of animals");
		Session ss=DataBaseInit.sf.openSession();  
		  ss.beginTransaction();  
		 //saving objects to session  
		  ss.saveOrUpdate(forum);  
		  ss.getTransaction().commit();  
		  ss.close(); 
		forum.register("tzvi", "shapira", "a@b", "b");
		forum.addAdminByName("tzvi");
		forum.createSubForum("dogs", "people who like pets");
		forum.createSubForum("cats", "other people");
		SubForum subforum=forum.getSubForum("dogs");
		subforum.addPost(forum.getMember("tzvi"), "kaka", "mayka");
		subforum.postRespond(forum.getMember("tzvi"), 0,"shoo", "hada");

	}

}
