package unitTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
public class ForumTest {
	/*
	public Forum initForum(){
		Forum forum=new Forum("sport","everything about sports");
		return forum;
	}
	
	private Member initMember() {
		Member temp=new Member("yossi","1234","yos@a");
		return temp;
	}
	
	private Member initMember2() {
		Member temp=new Member("danny","1234","dan@a");
		return temp;
	}

	@Test
	public void testSetFriends() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.register(member2.get_userName(), member2.get_password(), member2.get_email());
		assertTrue(forum.setFriends(member.get_userName(), member2.get_userName()));
		
	}

	@Test
	public void testLogin() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertTrue(forum.login(member.get_userName(), member.get_password())!=null);
	}


	@Test
	public void testComplainInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		Member member2=initMember2();
		forum.register(member2.get_userName(), member2.get_password(), member2.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertTrue(forum.complainInSubForum("soccer", member.get_userName(), member2.get_userName(), "he is stupid"));
	}

	@Test
	public void testRegister() {
		Forum forum=initForum();
		Member member=initMember();
		assertTrue(forum.register(member.get_userName(), member.get_password(), member.get_email()));
		assertTrue(forum.isMember(member.get_userName()));
		assertFalse(forum.register(member.get_userName(), member.get_password(), member.get_email()));
	}

	@Test
	public void testSetAdmin() {
		Forum forum=initForum();
		Member member=initMember();
		assertTrue(forum.setAdmin(member));
		assertTrue(forum.isAdmin(member.get_userName()));
	}

	

	@Test
	public void testCreateSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		assertFalse(forum.createSubForum("soccer", "fans", member.get_userName()));
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertFalse(forum.createSubForum("soccer", "fans", member.get_userName()));
		forum.setAdmin(member);
		assertTrue(forum.createSubForum("soccer", "fans", member.get_userName()));
		assertTrue(forum.getSubForum("soccer")!=null);
		
	}

	@Test
	public void testViewSubForums() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertFalse(forum.viewSubForums().equals("no sub-forums in this forum!"));
	}


	@Test
	public void testPostInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertTrue(forum.postInSubForum("soccer", member.get_userName(),"barcelona", "win"));
		
	}

	@Test
	public void testPostResponseInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertTrue(forum.postInSubForum("soccer", member.get_userName(),"barcelona", "win"));
		assertTrue(forum.postResponseInSubForum("soccer", member.get_userName(),"barcelona", "win", 0));
	}

	@Test
	public void testDeleteFromSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertTrue(forum.postInSubForum("soccer", member.get_userName(),"barcelona", "win"));
		assertTrue(forum.deleteFromSubForum("soccer", 0));
		
	}


	@Test
	public void testShowPostInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		forum.setAdmin(member);
		forum.createSubForum("soccer", "fans", member.get_userName());
		HashMap<Integer,Post> temp=forum.showPostInSubForum("soccer");
		assertNull(temp.get(0));
		assertTrue(forum.postInSubForum("soccer", member.get_userName(),"barcelona", "win"));
		assertNotNull(forum.showPostInSubForum("soccer").get(0));
		
	}

	@Test
	public void testGetMember() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertTrue(forum.isMember(member.get_userName()));
		assertTrue(forum.getMember(member.get_userName()).equals(member));
		assertFalse(member2.equals(forum.getMember(member2.get_userName())));
	}

	@Test
	public void testAddAdmin() {
		Forum forum=initForum();
		Member member=initMember();
		assertFalse(forum.addAdmin(member));
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertTrue(forum.addAdmin(member));
		assertTrue(forum.isAdmin(member.get_userName()));
	}

	@Test
	public void testIsMember() {
		Forum forum=initForum();
		Member member=initMember();
		assertTrue(forum.register(member.get_userName(), member.get_password(), member.get_email()));
		assertTrue(forum.isMember(member.get_userName()));
	}
	
	@Test
	public void testDeleteSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertFalse(forum.deleteSubForum("soccer", member.get_userName()));
		forum.setAdmin(member);
		assertFalse(forum.deleteSubForum("soccer", member.get_userName()));
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertTrue(forum.deleteSubForum("soccer", member.get_userName()));
	}
	
	@Test
	public void testAddModerator() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		
		forum.register(member.get_userName(), member.get_password(), member.get_email());
		assertFalse(forum.addModerator("soccer", member.get_userName(), member2.get_userName()));
		forum.setAdmin(member);
		assertFalse(forum.addModerator("soccer", member.get_userName(), member2.get_userName()));
		forum.createSubForum("soccer", "fans", member.get_userName());
		assertFalse(forum.addModerator("soccer", member.get_userName(), member2.get_userName()));
		forum.register(member2.get_userName(), member2.get_password(), member2.get_email());
		assertTrue(forum.addModerator("soccer", member.get_userName(), member2.get_userName()));
		
	}
*/
}
