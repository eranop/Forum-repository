package project_tests.acceptanceTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends ForumSiteTest{

	public LoginTest() throws Exception {
		super();
	}
	@Before
	 public void initialize() {
		_site.enterForum("food1");
	}
	@After
	public void after() {
		_site.logout(); 
		_site.exitForum();
   }

	@Test
	public void loginSimpleTest() {
		assertTrue(_site.login("tzvi", "tzvi"));
		_site.logout();
		assertTrue(_site.login("tzvi", "tzvi"));
		_site.logout();
		assertTrue(_site.login("aviad", "aviad"));
	}
	
	/*
	@Test
	public void loginWrogDitailsTest() {
		assertFalse(_site.login("tzvi", ""));
		assertFalse(_site.login("tzvi", "tvi"));
		assertFalse(_site.login("aviad", "tzvi"));
		assertFalse(_site.login("yoni", "tzvi"));
	}
	@Test
	public void loginTwiceTset() {
		assertTrue(_site.login("tzvi", "tzvi"));
		assertFalse(_site.login("tzvi", "tzvi"));
	}
	
	@Test
	public void loginAfterSwitchForumTset() {
		_site.login("tzvi", "tzvi");
		_site.exitForum();
		assertTrue(_site.enterForum("food2"));
		assertTrue(_site.login("aviad", "aviad"));
	}
	*/
}
