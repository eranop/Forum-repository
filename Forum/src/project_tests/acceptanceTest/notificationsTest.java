package project_tests.acceptanceTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import allcode.Member;

public class notificationsTest extends ForumSiteTest{

	public notificationsTest() throws Exception {
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

	@Test
	public void notifyAfterPostTest() {
		_site.enterSubforum("milk1");
		_site.login("aviad", "aviad");
		_site.writePostInSubForum("Title1", "content");
		_site.logout();
		_site.login("tzvi", "tzvi");
		Member member = _site.getMember();
		assertFalse(member.getMessages().isEmpty());
	}
	
	@Test
	public void notifyAfterResponseTest() {
		fail("Not yet implemented");
	}
	@Test
	public void notifyWhenMemberIsLoggedTest() {
		fail("Not yet implemented");
	}
	@Test
	public void notifyWhenMemberIsnotLoggedTest() {
		fail("Not yet implemented");
	}
	@Test
	public void notifyNoFriedsTest() {
		fail("Not yet implemented");
	}
	
}
