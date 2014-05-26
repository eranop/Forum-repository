package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.mail.iap.Response;

import services.report;
import allcode.Forum;
import allcode.Member;
import allcode.SubForum;

public class SubForumTest {

	Forum frm = new Forum("animals", "this forum is about animals");
	SubForum sub = new SubForum("lion","this sub is about lions", frm);
	Member mem1 = new Member("Moti", "1234", "moti@walla.co.il");
	Member mem2 = new Member("Dani", "4321", "dani@walla.co.il");
	Member mem3 = new Member("RAFIKI", "9988", "RAFIKI@walla.co.il");
	Member mem4 = new Member("simba", "1111", "simba@walla.co.il");
	Member mem5 = new Member("pumba", "1111", "pumba@walla.co.il");
	Member mem6 = new Member("timon", "1111", "timon@walla.co.il");
	
	@Before
	public void setUp() throws Exception {
		frm.addAdmin(mem6);
		sub.addPost(mem1, "the lion1", "lion 1 is bla 1");	//1
		sub.addPost(mem2, "the lion2", "lion 2 is bla 2");	//2
		sub.addPost(mem2, "the lion3", "lion 3 is bla 3");	//3
		sub.addPost(mem1, "the lion4", "lion 4 is bla 4");	//4
		sub.addPost(mem2, "the lion5", "lion 5 is bla 5");	//5
		sub.deletePost(2);
		sub.addPost(mem2, "the lion6", "lion 6 is bla 6");	//6
		
		sub.postRespond(mem3, 1, "the lion1 is wrong", "lion 1 ISNT BLA BLA 1");	//7
		sub.postRespond(mem2, 1, "the lion1 true", "u are correct");		//8
		sub.postRespond(mem1, 6, "bullshit!", "zzzzzzzzzzzzz");				//9
		sub.postRespond(mem3, 4, "title444", "weay");						//10
		sub.postRespond(mem3, 4, "title4222244", "weaey");					//11
		sub.deletePost(10);
		//	root size = 5. all posts = 9	
		
		sub.addModerator(mem4, mem6);
		sub.complain(mem5, mem4, "blabla");
	
	}

	@Test 
	public void testPostsSize() {
		assertFalse(sub.getRootPosts().size() == 6);
		assertTrue(sub.getRootPosts().size() == 5);
		assertFalse(sub.getAllPosts().size() == 11);
		assertTrue(sub.getAllPosts().size() == 9);
		assertTrue(sub.getRootPosts().size() == 5);
	}
	@Test
	public void testPostsDelete() {
		assertFalse(sub.deletePost(10) == report.OK);
		assertTrue(sub.deletePost(5) == report.OK);
		assertTrue(sub.getAllPosts().size() == 8);
		assertTrue(sub.getRootPosts().size() == 4);
	}
	@Test
	public void testDeleteRootPostWithReponses() {
		sub.deletePost(1);
		assertTrue(sub.getRootPosts().size() == 4);
		assertTrue(sub.getAllPosts().size() == 6);
	}
	@Test
	public void testPostsCreate() {
		assertTrue(sub.addPost(mem2, "newtitle", "content").getReport() == report.OK);
		assertTrue(sub.getRootPosts().size() == 6);
		assertTrue(sub.postRespond(mem3, 9, "title", "content").getReport() == report.OK);
		assertTrue(sub.getRootPosts().size() == 6);
		assertTrue(sub.getAllPosts().size() == 11);
	}
	
	@Test
	public void testDeleteUnexistedPost() {
		assertFalse(sub.deletePost(2) == report.OK);
		assertTrue(sub.getRootPosts().size() == 5);
		assertTrue(sub.getAllPosts().size() == 9);
	}
	@Test
	public void testModeratorsSize() {
		assertTrue(sub.getModerators().size() == 1);
		assertTrue(sub.addModerator(mem5, mem6) == report.OK);
		assertTrue(sub.getModerators().size() == 2);
		assertTrue(sub.removeModerator(mem4) == report.OK);
		assertTrue(sub.getModerators().size() == 1);
	}
	
	@Test
	public void testUnexistedModeratorsDelete() {
		assertFalse(sub.removeModerator(mem1) == report.OK);		
	}
	
	@Test
	public void testModeratorsAdding() {
		assertTrue(sub.isModerator(mem4));
		assertFalse(sub.isModerator(mem5));
		assertTrue(sub.addModerator(mem5, mem6) == report.OK);
		assertTrue(sub.isModerator(mem5));
	}
	
	@Test
	public void testComplainsSize() {
		assertTrue(sub.getComplains().size() == 1);
	}
	@Test
	public void testAddComplain() {
		sub.complain(mem5, mem4, "blabla");
		assertTrue(sub.getComplains().size() == 2);
	}
}
