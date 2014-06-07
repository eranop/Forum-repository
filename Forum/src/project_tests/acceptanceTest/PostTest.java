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
		_site.writePostInSubForum("m1", "milk1");	//1
		_site.writePostInSubForum("m2", "milk2");	//2
		_site.writePostInSubForum("m3", "milk3");	//3
		_site.enterPost(1);
		_site.writeResponsePostInSubForum("m11", "milk1_1");	//4
		_site.exitPost();
		_site.enterPost(2);
		_site.writeResponsePostInSubForum("m22", "milk2_2");	//5
		_site.exitPost();
		
		
	}
	@After
	public void after() {
		_site.logout(); 
		_site.exitForum();
  }
	@Test
	public void simplePostTest(){
		assertTrue(_site.writePostInSubForum("M10", "MILK10") == 5);	//6
		assertTrue(_site.enterPost(1));		//entering post.
		assertFalse(_site.enterPost(6));	//cannot enter post. already in 1.
		_site.exitPost();
		assertTrue(_site.enterPost(1));		//entering post 6.
	}
	@Test
	public void postWithMissingFieldTest(){
		assertFalse(_site.writePostInSubForum("", "MILK10") == 5);	//6
		assertFalse(_site.writePostInSubForum("M10", "") == 5);	//6
		assertFalse(_site.writePostInSubForum("M10", null) == 5);	//6
	}
	@Test
	public void postWithLongTextTest(){
		assertTrue(_site.writePostInSubForum("M10", "MILK10 fffffff!@#^&^@#$%*()_+_|{SPDF::C<XV>Z>BMNffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == 5);
	}
	
	
	@Test
	public void simpleResponseTest(){
		assertFalse(_site.writeResponsePostInSubForum("m22", "milk2_2") == 5);	//5 //not in post.
		_site.enterPost(3);
		assertTrue(_site.writeResponsePostInSubForum("m33", "milk3_3") == 5);	//5 //not in post.
	}
	@Test
	public void responseToNoExistPostTest(){
		assertFalse(_site.enterPost(8));
		assertFalse(_site.writeResponsePostInSubForum("m22", "milk2_2") == 5);	//5 //not in post.
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
