package allcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import services.Logger2;

public class InitializeSystem {

	public static SiteManager init(String superAdminName, String password, String email){
		
		DataBaseInit.initialize();

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
		
		sm.closeConnection(uc.getID());
		sm.closeConnection(c.getID());
		return sm;
	}	
}

