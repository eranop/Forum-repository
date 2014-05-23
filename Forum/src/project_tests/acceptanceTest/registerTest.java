package project_tests.acceptanceTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class registerTest extends ForumSiteTest{

	public registerTest() throws Exception {
		super();
	}

	@Test
	public void SimpleRegisterTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void nullFeildsRegisterTest() {
		fail("Not yet implemented");
	}
	@Test
	public void uncorrectValuesTest() {
		//name with numbers or tags
		fail("Not yet implemented");
		
		//mail without @
		
		//password too short
		
		//name too long
	}
	
	@Test
	public void registerTwiceTest() {
		//register with same name
		
		
		//register with same email
		
		
		//register with same password
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void registerWithoutForumTest() {
		fail("Not yet implemented");
	}
	
}
