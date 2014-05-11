package project_tests.acceptanceTest;
import project_tests.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostsTests extends ForumSiteTest{

	@Test
	public void CorectPostTest() {
		System.out.println("got here");
		//logged
		this.site.login("eli5", "5", "food1");
		assertTrue(site.writePostInSubForum("food1", "steak1", "eli1", "yumi", "eat steak fast"));
		assertTrue(site.writePostInSubForum("food1", "steak1", "eli1", "yumi2", "eat steak fast"));
		this.site.logout();
		
	}
	@Test
	public void notLogTest(){
		//not logged
				assertFalse(site.writePostInSubForum("food1", "steak1", "eli1", "yumi", "eat steak fast"));
				
				//not member in the forum
				assertFalse(site.writePostInSubForum("food1", "steak1", "eli10", "yumi", "eat steak fast"));
				
				//forum not exist
				assertFalse(site.writePostInSubForum("food7", "steak1", "eli1", "yumi", "eat steak fast"));
				//sub forum not exist
				assertFalse(site.writePostInSubForum("food7", "steak", "eli1", "yumi", "eat steak fast"));
				
				
	}

}
