package allcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import services.Logger2;
import services.report;

public class InitializeSystem {

	public static SiteManager init(String superAdminName, String password, String email){
		
		DataBaseInit.initialize();
		
		Password.setQuestions();
		
		SiteManager sm= new SiteManager(superAdminName, password, email);
		SuperAdminConnection c=sm.openSuperAdminConnection();
		
		Logger2.initLogSystem();
		Logger2.initLogUser();

		c.addNewForum("food", "");
		c.addNewForum("sport", "");
		c.addNewForum("judaism", "the right way to go");
		
		
		UserConnection uc=sm.openNewConnection();
		List<Forum> forums=(List<Forum>) uc.getForums().getMe();
		Forum f=forums.get(0);
		f.createSubForum("tomato1", "red");
		f.createSubForum("tomato2", "red");
		f.createSubForum("tomato3", "red");
		f.createSubForum("tomato4", "red");
		
		f=forums.get(1);
		f.createSubForum("ping pong1", "white");
		f.createSubForum("ping pong2", "white");
		f.createSubForum("ping pong3", "white");
		f.createSubForum("ping pong4", "white");
		
		uc.enterForum(f.get_forumName());
		uc.registerToForum("oriya", "oriya", "oriya1989@walla.com","is it works?", "no");
		uc.registerToForum("or", "123", "oriya1989@walla.com","is it works?", "no");
		uc.registerToForum("david", "123", "oriya1989@walla.com","is it works?", "no");
		uc.registerToForum("avi", "123", "aviadelitzur@gmail.com","is it works?", "no");
		c.enterForum(f.get_forumName());
		report r=c.addAdminToForum("avi");
		
		uc.enterSubforum("ping pong1");
		uc.login("avi",	"123");
		uc.addModerator("oriya");
		uc.logout();
		uc.login("oriya", "oriya");
		Post p=(Post) uc.writePost("first post", "oriya check our project first time").getMe();
		uc.enterPost(p.getIndex());
		uc.writeResponsePost("response1", "first response");
		uc.writeResponsePost("response2", "2th response");
		uc.exitPost();
		
		uc.writePost("first post", "oriya check our project 2nd time");
		uc.writePost("first post", "oriya check our project 3th time");
		uc.writePost("first post", "oriya check our project 4th time");
		
		uc.exitForum();
		
		
		sm.closeConnection(uc.getID());
		sm.closeConnection(c.getID());
		System.out.println("admin appoint " + r.toString());
		return sm;
	}	
}

