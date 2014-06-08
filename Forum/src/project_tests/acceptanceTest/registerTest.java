package project_tests.acceptanceTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class registerTest extends ForumSiteTest{

	public registerTest() throws Exception {
		super();
	}

	@Before
	public void set_up(){
		getRegularConnection();
		_site.enterForum("food1");
	}
	@Test
	public void SimpleRegisterTest() {
		assertTrue(_site.registerToForum("adva1", "234", "adva1@gmail.com", "cat"));
		assertTrue(_site.registerToForum("adva2", "234", "adva2@gmail.com", "cat"));
		assertTrue(_site.registerToForum("adva3", "234", "adva3@gmail.com", "cat"));
	}

	@Test
	public void nullOrEmptyFeildsRegisterTest() {
		assertFalse(_site.registerToForum("yoni", "", "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum("yoni", null, "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum("", "dfs3", "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum(null, "dsfsd", "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum("yoni", "dsfs", "", "cat"));
		assertFalse(_site.registerToForum("yoni", "dsfs", null, "cat"));
	}
	@Test
	public void uncorrectValuesTest() {
		//name with numbers or tags
		//mail without @
		//password too short		
		//name too long
		assertFalse(_site.registerToForum("12", "ds", "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum("$%#$%3", "ds", "yoni@gmail.com", "cat"));
		assertFalse(_site.registerToForum("yoni", "ds", "yonigmail.com", "cat"));
		assertFalse(_site.registerToForum("yoni", "ds", "yoni@", "cat"));
		assertFalse(_site.registerToForum("yoni", "ds", "@gmail.com", "cat"));
		assertFalse(_site.registerToForum("yonidsfffffffffffffffsdfsssssss", "ds", "@gmail.com", "cat"));		
	}

	@Test
	public void registerTwiceTest() {
		//register with same name
		assertTrue(_site.registerToForum("david", "david2", "david2@gmail.com", "cat"));
		//all same
		assertFalse(_site.registerToForum("david", "david2", "david2@gmail.com", "cat"));
		//same name
		assertFalse(_site.registerToForum("david", "dav", "david2@gmail.com", "cat"));
		//same email
		assertFalse(_site.registerToForum("dav", "david2", "david2@gmail.com", "cat"));
	}


	@Test
	public void registerWithoutForumTest() {
		_site.exitForum();
		assertFalse(_site.registerToForum("moshe", "987", "moshe@gmail.com", "cat"));
	}

}
