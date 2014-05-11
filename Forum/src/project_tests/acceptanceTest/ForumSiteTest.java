
package project_tests.acceptanceTest;

import junit.framework.TestCase;

import org.junit.Test;

import Bridge.SiteInterface;


public class ForumSiteTest{
	SiteInterface site;
	
	/*
	public void setTestInterface(String interfaceType){
		if (interfaceType.equals("Real"))
			site = new RealBridge();
		else if (interfaceType.equals("proxy"))
			site = new ProxyBridge();
		else
		fail("Undefined interface " + interfaceType);
		}
	 */


	public ForumSiteTest(){
		super();
	}

	protected void setUp() throws Exception {
		this.site=Driver.getBridge();
		this.initSuperAdmin();
		this.createForums();
		this.addMembers();
		this.createSubForums();

	}

	private void initSuperAdmin() {
		this.site.init("eyal", "123", "a@b");

	}


	private void createForums() {
		this.site.loginSuperAdmin("eyal", "123");
		this.site.addForumToSite("food1", "", "rubi1", "123", "rubi1@c");
		this.site.addForumToSite("food2", "", "rubi2", "1234", "rubi2@c");
		this.site.addForumToSite("food3", "", "rubi3", "1235", "rubi3@c");
		this.site.addForumToSite("food4", "", "rubi4", "1236", "rubi4@c");
		this.site.addForumToSite("food5", "", "rubi5", "1237", "rubi5@c");
		this.site.logoutSuperAdmin();
	}


	private void addMembers() {
		this.site.showSubforumsOfForum("food1");
		this.site.addNewMemberToForum("food1", "eli1", "56789", "eli1@a");
		this.site.addNewMemberToForum("food1", "eli2", "5678", "eli2@a");
		this.site.addNewMemberToForum("food1", "eli3", "567", "eli3@a");
		this.site.addNewMemberToForum("food1", "eli4", "56", "eli4@a");
		this.site.addNewMemberToForum("food1", "eli5", "5", "eli5@a");
		this.site.exitForum("food1");
		
		this.site.showSubforumsOfForum("food2");	
		this.site.addNewMemberToForum("food2", "eli1", "56789", "eli1@a");
		this.site.addNewMemberToForum("food2", "eli2", "5678", "eli2@a");
		this.site.addNewMemberToForum("food2", "eli3", "567", "eli3@a");
		this.site.addNewMemberToForum("food2", "eli4", "56", "eli4@a");
		this.site.addNewMemberToForum("food2", "eli5", "5", "eli5@a");
		this.site.exitForum("food2");
		
	}

	private void createSubForums() {
		this.site.showSubforumsOfForum("food1");
		this.site.login("rubi1", "123", "food1");
		this.site.createSubForumInForumByAdmin("food1", "steak1", "", "rubi1");
		this.site.createSubForumInForumByAdmin("food1", "steak2", "", "rubi1");
		this.site.createSubForumInForumByAdmin("food1", "steak3", "", "rubi1");
		this.site.createSubForumInForumByAdmin("food1", "steak4", "", "rubi1");
		this.site.logout();
		this.site.showSubforumsOfForum("food2");
		this.site.login("rubi2", "1234", "food2");
		this.site.createSubForumInForumByAdmin("food2", "sald1", "", "rubi2");
		this.site.createSubForumInForumByAdmin("food2", "salad2", "", "rubi2");
		this.site.createSubForumInForumByAdmin("food2", "salad3", "", "rubi2");
		this.site.createSubForumInForumByAdmin("food2", "salad4", "", "rubi2");
		this.site.logout();

	}




	protected void tearDown() throws Exception {
		site.cleanAllData();
		

	}



}
