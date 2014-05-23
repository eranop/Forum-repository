package project_tests.acceptanceTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostTest extends ForumSiteTest{

	public PostTest() throws Exception {
		super();
	}
	@Before
	 public void set_up() {
		getRegularConnection();
		_site.exitForum();
		_site.enterForum("food1");
		_site.login("aviad", "aviad");
		_site.enterSubforum("milk1");
	}
	@After
	public void after() {
		_site.logout(); 
		_site.exitForum();
  }
	
	
	@Test
	public void simplePostTest(){
		
	}
	@Test
	public void postWithMissingFieldTest(){
		
	}
	@Test
	public void postWithLongTextTest(){
		
	}
	
	
	@Test
	public void simpleResponseTest(){
		
	}
	@Test
	public void responseToNoExistPostTest(){
		
	}
	@Test
	public void selfResponseTest(){
		
	}	
	@Test
	public void postPreconditionsTest(){
		/*
		 * must be member
		 * in sub-forum
		 */
	}	
	@Test
	public void responsePreconditionsTest(){
		/*
		 * must be member 
		 * in post
		 */
	}	
	
	
	
}
