package project_tests.acceptanceTest;

import org.junit.After;
import org.junit.Before;

public class PostTest extends ForumSiteTest{

	public PostTest() throws Exception {
		super();
	}
	@Before
	 public void initialize() {
		getRegularConnection();
		_site.exitForum();
		_site.enterForum("food1");
	}
	@After
	public void after() {
		_site.logout(); 
		_site.exitForum();
  }

	
}
