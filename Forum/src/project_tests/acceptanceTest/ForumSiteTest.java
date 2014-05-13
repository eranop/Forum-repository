
package project_tests.acceptanceTest;

import java.util.Vector;



public class ForumSiteTest{
	project_tests.Bridge.SiteInterface _site;
	Vector<Integer> _connections;
	private static final int connectionNumber=5;
	

	public ForumSiteTest() throws Exception{
		super();
		_connections=new Vector<Integer>();
		this.setUp();
	}

	public void setUp() throws Exception {
		_site=Driver.getBridge();
		init();
		createForums();
		createSubForums();
		addMembers();
	}

	private void init() {

		_site.init("eyal", "123", "a@b");
		int conID=_site.openNewConnection();
		_connections.add(conID);
		//set the first connection
		_site.switchConnection(_connections.get(0));
		_site.loginSuperAdmin("eyal", "123");
		
		System.out.println("init end");
	}


	private void createForums() {
		_site.addForum("food1", "");
		_site.addForum("food2", "");
		//_site.addForum("food3", "");
		//_site.addForum("food4", "");
	}

	private void createSubForums() {
		_site.enterForum("food1");
		_site.addSubforumToForum("milk1", "", "");
		_site.addSubforumToForum("milk2", "", "");
		_site.addSubforumToForum("milk3", "", "");
		_site.addSubforumToForum("milk4", "", "");
		_site.exitForum();

		_site.enterForum("food2");
		_site.addSubforumToForum("cola1", "", "");
		_site.addSubforumToForum("cola2", "", "");
		_site.addSubforumToForum("cola3", "", "");
		_site.addSubforumToForum("cola4", "", "");
		_site.exitForum();

		_site.logoutSuperAdmin();
	}




	private void addMembers() {
		_site.enterForum("food1");
		_site.registerToForum("aviad", "aviad", "avi@gmail.com");
		_site.registerToForum("tzvi", "tzvi", "tzvi@gmail.com");

		_site.loginSuperAdmin("eyal", "123");
		_site.addAdministratorToForum("aviad");
		_site.logoutSuperAdmin();

		_site.exitForum();

		_site.enterForum("food2");
		_site.registerToForum("aviad", "aviad", "avi@gmail.com");
		_site.registerToForum("tzvi", "tzvi", "tzvi@gmail.com");

		_site.loginSuperAdmin("eyal", "123");
		_site.addAdministratorToForum("tzvi");
		_site.logoutSuperAdmin();

		_site.enterSubforum("cola1");
		_site.login("tzvi", "tzvi");
		_site.addModeratorTosubforum("aviad");
		_site.logout();
		_site.exitForum();
		System.out.println("members create end");
	}





	protected void tearDown() throws Exception {
		_site.cleanAllData();


	}



}
