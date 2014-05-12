
package project_tests.acceptanceTest;

import java.util.Vector;



public class ForumSiteTest{
	project_tests.Bridge.SiteInterface _site;
	Vector<Integer> _connections;
	private static final int connectionNumber=5;
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


	public ForumSiteTest() throws Exception{
		super();
		this.setUp();
	}

	public void setUp() throws Exception {
		_site=Driver.getBridge();
		init();
		createForums();
		createSubForums();
		addMembers();
		System.out.println("end setup");
	}

	private void init() {

		if(_site.init("eyal", "123", "a@b")){
			int conID=_site.openNewConnection();
			if(conID!=-1){
				_connections.add(conID);
			}
			else{
				System.out.println("init failed");
			}
		}//set the first connection
		if(!_site.switchConnection(_connections.get(0))){
			System.out.println("init failed");
		}
		
		if(!_site.loginSuperAdmin("eyal", "123")){
			System.out.println("init failed");
		}
		System.out.println("init end");
	}


	private void createForums() {
		_site.addForum("food1", "");
		_site.addForum("food2", "");
		//_site.addForum("food3", "");
		//_site.addForum("food4", "");
		System.out.println("forums create end");
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
		System.out.println("subforum create end");
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
