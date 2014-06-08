
package project_tests.acceptanceTest;

import java.util.Vector;



public class ForumSiteTest {

	project_tests.Bridge.SiteInterface _site;
	Vector <Integer> _connections;
	private static final int connectionNumber = 5;


	public ForumSiteTest() throws Exception {
		super();
		_connections = new Vector <Integer>();
		this.setUp();
	}

	public void setUp() throws Exception {
		_site = Driver.getBridge();
		init();
		createForums();
		addMembers();
		createSubForums();
	}

	/**
	 * initialize the system with Owner "eya" pass 123.
	 * opening a connection for Super Admin.
	 * opening a connection for a regular user.
	 */
	private void init() {

		_site.init("eyal", "123", "a@b");
		//open regular and administrator connection

		int conID = _site.openSuperAdminConnection();	//opening connection for super admin
		_connections.add(conID);

		conID = _site.openNewConnection();				//opening connection for regular user
		_connections.add(conID);

		System.out.println("init end");
	}

	/**
	 * creating 2 Forums.
	 */
	private void createForums() {
		if(getSuperAdminConnection()){
			_site.addForum("food1", "");
			_site.addForum("food2", "");
			//_site.addForum("food3", "");
			//_site.addForum("food4", "");
		}
		else{
			System.out.println("create forum faild");
		}
	}

	/**
	 * register aviad and tzvi as users to both forums.
	 * and set them as admins. 
	 * Tzvi in food1. Aviad in food2.
	 */
	private void addMembers() {
		if(getRegularConnection()){

			_site.enterForum("food1");
			_site.registerToForum("aviad", "aviad", "avi@gmail.com", "cat");
			_site.registerToForum("tzvi", "tzvi", "tzvi@gmail.com", "cat");
			_site.exitForum();

			_site.enterForum("food2");
			_site.registerToForum("aviad", "aviad", "avi@gmail.com", "cat");
			_site.registerToForum("tzvi", "tzvi", "tzvi@gmail.com", "cat");
			_site.exitForum();

		}
		else{
			System.out.println("registration faild");
		}
		// set administrators
		if(getSuperAdminConnection()){
			_site.enterForum("food1");
			_site.addAdministratorToForum("tzvi");
			_site.exitForum();
			_site.enterForum("food2");
			_site.addAdministratorToForum("aviad");
			_site.exitForum();
		}
		else{
			System.out.println("set administrators faild");
		}
	}

	/**
	 * Creating sub forums in each forum with admin premissions.
	 */
	private void createSubForums() {
		if(getRegularConnection()){
			_site.enterForum("food1");
			_site.login("tzvi", "tzvi");
			_site.addSubforumToForum("milk1", "", "");
			_site.addSubforumToForum("milk2", "", "");
			_site.addSubforumToForum("milk3", "", "");
			_site.addSubforumToForum("milk4", "", "");
			_site.exitForum();

			_site.enterForum("food2");
			_site.login("aviad", "aviad");
			_site.addSubforumToForum("cola1", "", "");
			_site.addSubforumToForum("cola2", "", "");
			_site.addSubforumToForum("cola3", "", "");
			_site.addSubforumToForum("cola4", "", "");
			_site.exitForum();
		}
		else{
			System.out.println("create subforum faild");
		}
	}

	protected void tearDown() throws Exception {
		_site.cleanAllData();
	}
	
	protected boolean getSuperAdminConnection(){
		for(int e: _connections){
			if(!_site.isRegularConnection(e)){
				_site.switchConnection(e);
				return true;
			}
		}
		return false;
	}
	
	protected boolean getRegularConnection(){
		for(int e: _connections){
			if(_site.isRegularConnection(e)){
				_site.switchConnection(e);
				return true;
			}
		}
		return false;
	}

}
