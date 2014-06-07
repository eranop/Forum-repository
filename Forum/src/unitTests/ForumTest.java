package unitTests;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

import services.Response;
import services.report;
import allcode.Forum;
import allcode.Member;
import allcode.Post;
import allcode.SubForum;
public class ForumTest {
	
	public Forum initForum(){
		Forum forum=new Forum("sport","everything about sports");
		return forum;
	}
	
	private Member initMember() {
		Member temp=new Member("yossi","1234","yos@a", "an answer");
		return temp;
	}
	
	private Member initMember2() {
		Member temp=new Member("danny","1234","dan@a", "another answer");
		return temp;
	}

	@Test
	public void testSetFriends() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		forum.register(member2.get_userName(), member2.get_password().get_pass(), member2.get_email().getEmailString(),member2.get_password().get_passAnswer());
		assertTrue(forum.setFriends(member, member2)==report.OK);
		
	}

	@Test
	public void testLogin() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		//assertTrue(forum.login(member.get_userName(), member.get_password())!=null);
	}


	@Test
	public void testComplainInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Member member2=initMember2();
		forum.register(member2.get_userName(), member2.get_password().get_pass(), member2.get_email().getEmailString(),member2.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		assertTrue(sub.complain(member, member2, "he is stupid")==report.OK);
	}

	@Test
	public void testRegister() {
		Forum forum=initForum();
		Member member=initMember();
		assertTrue(forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer())==report.OK);
		assertTrue(forum.isMember(member.get_userName()));
		assertFalse(forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer())==report.OK);
	}

	@Test
	public void testSetAdmin() {
		Forum forum=initForum();
		Member member=initMember();
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		assertTrue(forum.isAdmin(member.get_userName()));
	}

	

	@Test
	public void testCreateSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		assertFalse(forum.createSubForum("soccer", "fans")==report.OK);
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		assertFalse(forum.createSubForum("soccer", "fans")==report.OK);
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		assertTrue(forum.createSubForum("soccer", "fans")==report.OK);
		assertTrue(forum.getSubForum("soccer")!=null);
		
	}

	@Test
	public void testViewSubForums() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		assertFalse(forum.viewSubForums().equals("no sub-forums in this forum!"));
	}


	@Test
	public void testPostInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		assertTrue(sub.addPost(member,"barcelona", "win").getReport()==report.OK);
		
	}

	@Test
	public void testPostResponseInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		assertTrue(sub.addPost(member,"barcelona", "win").getReport()==report.OK);
		assertTrue(sub.postRespond(member, 0, "barcelona", "win").getReport()==report.OK);
	}

	@Test
	public void testDeleteFromSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		Response res = sub.addPost(member,"barcelona", "win");
		assertTrue(res.getReport()==report.OK);
		assertTrue(sub.deletePost((Post)res.getMe())==report.OK);
		
	}


	@Test
	public void testShowPostInSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		HashMap<Integer,Post> temp=sub.getAllPosts();
		assertNull(temp.get(0));
		Response res = sub.addPost(member,"barcelona", "win");
		assertTrue(res.getReport()==report.OK);
		assertNotNull(sub.getAllPosts().get(0));
		
	}

	@Test
	public void testGetMember() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		assertTrue(forum.isMember(member.get_userName()));
		assertTrue(forum.getMember(member.get_userName()).equals(member));
		assertFalse(member2.equals(forum.getMember(member2.get_userName())));
	}

	@Test
	public void testAddAdmin() {
		Forum forum=initForum();
		Member member=initMember();
		assertFalse(forum.addAdmin(member)==report.OK);
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		assertTrue(forum.addAdmin(member)==report.OK);
		assertTrue(forum.isAdmin(member.get_userName()));
	}

	@Test
	public void testIsMember() {
		Forum forum=initForum();
		Member member=initMember();
		assertTrue(forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer())==report.OK);
		assertTrue(forum.isMember(member.get_userName()));
	}
	
	@Test
	public void testDeleteSubForum() {
		Forum forum=initForum();
		Member member=initMember();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		assertTrue(forum.deleteSubForum("soccer", member)==report.NO_SUCH_SUBFORUM);
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		assertTrue(forum.deleteSubForum("soccer", member)==report.NO_SUCH_SUBFORUM);
		forum.createSubForum("soccer", "fans");
		assertTrue(forum.deleteSubForum("soccer", member)==report.OK);
	}
	
	@Test
	public void testAddModerator() {
		Forum forum=initForum();
		Member member=initMember();
		Member member2=initMember2();
		forum.register(member.get_userName(), member.get_password().get_pass(), member.get_email().getEmailString(),member.get_password().get_passAnswer());
		forum.createSubForum("soccer", "fans");
		SubForum sub = forum.getSubForum("soccer");
		assertTrue(sub.addModerator(member, member2)==report.NOT_ALLOWED);
		Vector<Member> admins = forum.get_administrators();
		admins.add(member);
		forum.set_administrators(admins);
		assertTrue(sub.addModerator(member, member2)==report.NOT_ALLOWED);
		forum.register(member2.get_userName(), member2.get_password().get_pass(), member2.get_email().getEmailString(),member2.get_password().get_passAnswer());
		assertTrue(sub.addModerator(member, member2)==report.OK);
		
	}

}
