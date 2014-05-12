package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;



public class ForumSystemTest {
	/*
	public ForumSystem initSystem(){
		ForumSystem fs=new ForumSystem();
		fs.setSuperAdmin("dani", "1234", "a@b");
		return fs;
	}
	public ForumSystem initSystem2(){
		ForumSystem fs=new ForumSystem();
		fs.setSuperAdmin("dani", "1234", "a@b");
		fs.loginSuperAdmin("dani", "1234");
		return fs;
	}
	

	@Test
	public void testCreateForum() {
		ForumSystem fs=initSystem();
		assertNull(fs.createForum("sports", "kinds", "moshe", "1111", "c@d"));
		fs=initSystem2();
		assertNotNull(fs.createForum("sports", "kinds", "moshe", "1111", "c@d"));
		
	}

	@Test
	public void testAddAdminToForum() {
		ForumSystem fs=initSystem2();
		assertFalse(fs.addAdminToForum("dani", "sports"));
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		assertFalse(fs.registerToForum("sports", "avram", "1111", "b@c"));
		assertNotNull(fs.showSubforumsOfForum("sports"));
		assertTrue(fs.registerToForum("sports", "avram", "1111", "b@c"));
		assertTrue(fs.addAdminToForum("avram", "sports"));
		
	}

	@Test
	public void testSetSuperAdmin() {
		ForumSystem fs=new ForumSystem();
		assertTrue(fs.setSuperAdmin("dani", "1234", "a@b"));
	}

	@Test
	public void testLogout() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.login("avram", "1111", "sports");
		assertTrue(fs.logout());
		assertFalse(fs.logout());
		
		
		}

	@Test
	public void testRegisterToForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		assertFalse(fs.registerToForum("sports", "avram", "1111", "b@c"));
		assertNotNull(fs.showSubforumsOfForum("sports"));
		assertTrue(fs.registerToForum("sports", "avram", "1111", "b@c"));
	}

	@Test
	public void testComplain() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.registerToForum("sports", "yosi", "1234", "c@c");
		fs.addAdminToForum("avram", "sports");
		
		fs.login("avram", "1111", "sports");
		
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		assertTrue(fs.addModerator("sports", "soccer", "avram", "yosi"));
		assertTrue(fs.complain("avram", "yosi", "sports", "soccer", "he is stupid"));
		}

	@Test
	public void testSetFriends() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		
		
		assertFalse(fs.setFriends("sports", "avram", "yosi"));
		fs.registerToForum("sports", "yosi", "1111", "ba@c");
		
		assertFalse(fs.setFriends("sports", "avram", "yosi"));
		fs.login("avram", "1111", "sports");
		assertTrue(fs.setFriends("sports", "avram", "yosi"));
		
	}

	@Test
	public void testLogin() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.exitForum("sports");
		assertFalse(fs.login("avram", "1111", "sports"));
		fs.showSubforumsOfForum("sports");
		assertFalse(fs.login("avragm", "1111", "sports"));
		assertTrue(fs.login("avram", "1111", "sports"));
		assertFalse(fs.login("avram", "1111", "sports"));
	}

	@Test
	public void testWritePostInSubForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.addAdminToForum("avram", "sports");
		assertFalse(fs.writePostInSubForum("sports", "soccer", "avram", "barca", "champion"));
		fs.login("avram", "1111", "sports");
		assertFalse(fs.writePostInSubForum("sports", "soccer", "avram", "barca", "champion"));
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		assertTrue(fs.writePostInSubForum("sports", "soccer", "avram", "barca", "champion"));	
	}

	@Test
	public void testWriteResponsePostInSubForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.addAdminToForum("avram", "sports");
		
		fs.login("avram", "1111", "sports");
		
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		
		fs.writePostInSubForum("sports", "soccer", "avram", "barca", "champion");
		
		assertTrue(fs.writeResponsePostInSubForum("sports", "soccer", "avram",
				"real madrid", "champions", 0));
	}

	@Test
	public void testDeletePostInSubForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.registerToForum("sports", "yosi", "1234", "c@c");
		fs.addAdminToForum("avram", "sports");
		
		fs.login("avram", "1111", "sports");
		
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		assertTrue(fs.addModerator("sports", "soccer", "avram", "yosi"));
		fs.logout();
		fs.login("yosi", "1234", "sports");
	
		fs.writePostInSubForum("sports", "soccer", "yosi", "barca", "champion");
		assertTrue(fs.deletePostInSubForum("sports", "soccer",0,"yosi"));
	}

	@Test
	public void testLoginSuperAdmin() {
		ForumSystem fs=initSystem();
		assertFalse(fs.loginSuperAdmin("avram", "1111"));
		assertTrue(fs.loginSuperAdmin("dani", "1234"));
		assertFalse(fs.loginSuperAdmin("avram", "1111"));
		
	}

	@Test
	public void testLogoutSuperAdmin() {
		ForumSystem fs=initSystem2();
		assertTrue(fs.logoutSuperAdmin());
		assertFalse(fs.logoutSuperAdmin());
		}

	@Test
	public void testShowSubforumsOfForum() {
		ForumSystem fs=initSystem2();
		assertNull(fs.showSubforumsOfForum("sports"));
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		
		assertNotNull(fs.showSubforumsOfForum("sports"));
		fs.exitForum("sports");
		
	}

	@Test
	public void testShowPostInSubForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.addAdminToForum("avram", "sports");
		
		fs.login("avram", "1111", "sports");
		assertNull(fs.showPostInSubForum("sports", "soccer"));
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		
		fs.writePostInSubForum("sports", "soccer", "avram", "barca", "champion");
		assertNotNull(fs.showPostInSubForum("sports", "soccer"));
		}

	@Test
	public void testCreateSubForumInForumByAdmin() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		assertFalse(fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram"));
		fs.addAdminToForum("avram", "sports");
		assertFalse(fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram"));
		fs.login("avram", "1111", "sports");
		assertTrue(fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram"));
		}
	
	
	@Test
	public void testExitForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		assertFalse(fs.exitForum("sports"));
		fs.showSubforumsOfForum("sports");
		assertTrue(fs.exitForum("sports"));
	}

	public void testDeleteSubForum() {
		ForumSystem fs=initSystem2();
		fs.createForum("sports", "kinds", "moshe", "1111", "c@d");
		fs.showSubforumsOfForum("sports");
		fs.registerToForum("sports", "avram", "1111", "b@c");
		fs.addAdminToForum("avram", "sports");
		fs.login("avram", "1111", "sports");
		assertFalse(fs.deleteSubForum("sports", "soccer", "avram"));
		fs.createSubForumInForumByAdmin("sports", "soccer", "football", "avram");
		assertTrue(fs.deleteSubForum("sports", "soccer", "avram"));
	}
*/
}
