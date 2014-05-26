package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import allcode.Forum;
import allcode.ForumPolicy;
import allcode.Member;
import allcode.Post;
import allcode.SubForum;

public class PolicyTest {

	Forum frm = new Forum("animals", "this forum is about animals");
	SubForum sub = new SubForum("lion","this sub is about lions", frm);
	Member mem1 = new Member("Moti", "1234", "moti@walla.co.il");
	Member mem2 = new Member("Dani", "4321", "dani@walla.co.il");
	Member mem3 = new Member("RAFIKI", "9988", "RAFIKI@walla.co.il");
	Member mem4 = new Member("simba", "1111", "simba@walla.co.il");
	Member mem5 = new Member("pumba", "1111", "pumba@walla.co.il");
	ForumPolicy plcy = frm.get_forumPolicy();
	
	Post post1 = (Post) sub.addPost(mem1, "the lion1", "lion 1 is bla 1").getMe();	//1
	Post post2 = (Post) sub.addPost(mem2, "the lion2", "lion 2 is bla 2").getMe();	//2
	Post post3 = (Post) sub.addPost(mem3, "the lion3", "lion 3 is bla 3").getMe();	//3
	Post post4 = (Post) sub.addPost(mem4, "the lion4", "lion 4 is bla 4").getMe();	//4
	Post post5 = (Post) sub.addPost(mem5, "the lion5", "lion 5 is bla 5").getMe();	//5
	
	Post post6 = (Post) sub.postRespond(mem1, 1, "the lion1 is wrong", "lion 1 ISNT BLA BLA 1").getMe();	//6
	Post post7 = (Post) sub.postRespond(mem2, 1, "the lion1 true", "u are correct").getMe();				//7
	Post post8 = (Post) sub.postRespond(mem3, 3, "bullshit!", "zzzzzzzzzzzzz").getMe();						//8
	Post post9 = (Post) sub.postRespond(mem4, 4, "title444", "weay").getMe();								//9
	Post post10 = (Post) sub.postRespond(mem5, 4, "title4222244", "weaey").getMe();							//10
	
	@Before
	public void setUp() throws Exception {
		
		frm.addMember(mem1);
		frm.addMember(mem2);
		frm.addMember(mem3);
		frm.addMember(mem4);
		frm.addMember(mem5);
		
		frm.addAdmin(mem1);
		frm.addAdmin(mem2);
		sub.addModerator(mem3, mem1);
		sub.addModerator(mem4, mem1);
		
		/* 
		 * admins:	 mem1, mem2.
		 * mods:	 mem1 --> mem3.
		 * mods: 	 mem1 --> mem4.
		 * member:	 mem5.	
		 * current forum policy is deafult
		 */
	}
	
	@Test
	public void testDefaultPolicyFields() {
		assertTrue(plcy.isDeleteMessageModerator() == true);
		assertTrue(plcy.isDeleteMessagePublisher() == true);
		assertTrue(plcy.isDeleteMessageAdmin() == true);
		assertTrue(plcy.getModeratorDays() == 0);
		assertTrue(plcy.getModeratorPosts() == 0);
		assertTrue(plcy.isDeleteModeratorOnlyByRankingAdmin() == false);
		assertTrue(plcy.isDeleteLastModerator() == true);
	}
	
	@Test
	public void testDefaultPolicyFuncionality() {
		assertTrue(frm.canDeletePost(mem5, post5, sub) == true); 	//isDeleteMessagePublisher = true
		assertTrue(frm.canDeletePost(mem5, post1, sub) == false); 	//NOT publisher NOT admin NOT mod.
		assertTrue(frm.canDeletePost(mem3, post4, sub) == true);	//isDeleteMessageModerator = true
		assertTrue(frm.canDeletePost(mem1, post7, sub) == true); 	//isDeleteMessageAdmin == true
		assertTrue(frm.canAddModerator(mem5) == true);				//mod days and posts = 0 so should be true.
		assertTrue(frm.canRemoveModerator(mem3, mem1, sub) == true);	//mem3 added by mem1
		assertTrue(frm.canRemoveModerator(mem3, mem2, sub) == true);	//isDeleteModeratorOnlyByRankingAdmin = false. 
		sub.removeModerator(mem3);
		assertTrue(frm.canRemoveModerator(mem3, mem4, sub) == true);	//isDeleteLastModerator = true.
	}
	
	@Test
	public void testModeratorPolicyFunctionality() {
		plcy.setDeleteLastModerator(false);
		plcy.setDeleteModeratorOnlyByRankingAdmin(true);
		plcy.setModeratorPosts(3);
		assertTrue(frm.canRemoveModerator(mem3, mem1, sub) == true);	//isDeleteModeratorOnlyByRankingAdmin = false.
		sub.removeModerator(mem3);
		assertTrue(frm.canRemoveModerator(mem3, mem1, sub) == false);	//isDeleteLastModerator = false.
		assertTrue(frm.canRemoveModerator(mem3, mem2, sub) == false);	//setDeleteModeratorOnlyByRankingAdmin = true
		assertTrue(frm.canAddModerator(mem3) == false);					//only posted 2 adds.
		assertTrue(frm.canAddModerator(mem5) == false);	
		sub.addPost(mem5, "the lion22", "lion 22 is bla 22");			//added 3 posts
		assertTrue(frm.canAddModerator(mem5) == true);	
		//left to test is date policy. better happen in real time.
	}
	
	@Test
	public void testDeleteMessagePolicy() {
		plcy.setDeleteMessageModerator(false);						//mods cannot delete messages in forum
		assertTrue(frm.canDeletePost(mem3, post4, sub) == false);	//isDeleteMessageModerator = false
		plcy.setDeleteMessageAdmin(false);							//admins cannot delete messages
		assertTrue(frm.canDeletePost(mem1, post7, sub) == false); 	//isDeleteMessageAdmin = false
		plcy.setDeleteMessagePublisher(false);						//ownder of posts cannot delete it
		assertTrue(frm.canDeletePost(mem5, post5, sub) == false); 	//isDeleteMessagePublisher = false
	}

}
