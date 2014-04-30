import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PostTest {


	Member mem1 = new Member("Moti", "1234", "moti@walla.co.il");
	Member mem2 = new Member("David", "4321", "david@walla.co.il");
	Member mem3 = new Member("Ben", "5544", "ben@walla.co.il");
	Post post1 = new Post(mem1, "title1", "content1", 1);
	Post post2 = new Post(mem2, "title2", "content2", 2, post1);		//response 1
	Post post3 = new Post(mem1, "title3", "content3", 3, post2);		//response to 2 response 1
	Post post4 = new Post(mem3, "title4", "content4", 4);
	Post post5 = new Post(mem3, "title5", "content5", 5, post1);		//response 1
	
	@Before 
	public void setUp() {
		post1.addResponse(2, post2);
		post2.addResponse(3, post3);
		post1.addResponse(5, post5);
	}
	

	@Test
	public void testPostTitle() {
		assertTrue(post1.getTitle().equals("title1"));
		assertTrue(post4.getTitle().equals("title4"));
		assertFalse(post5.getTitle().equals("title1"));
	}
	@Test
	public void testPostContent() {
		assertTrue(post1.getContent().equals("content1"));
		assertTrue(post4.getContent().equals("content4"));
		assertFalse(post5.getContent().equals("content1"));
	}
	
	@Test
	public void testPostPublisher() {
		assertTrue(post1.getPublisher().equals("Moti"));
		assertTrue(post4.getPublisher().equals("Ben"));
		assertFalse(post5.getPublisher().equals("David"));		
	}
	
	@Test
	public void testPostIndex() {
		assertTrue(post1.getIndex() == 1);
		assertTrue(post2.getIndex() == 2);
		assertFalse(post3.getIndex() == 1 && post3.getIndex() == 2 && post3.getIndex() == 4 && post3.getIndex() == 5);	
	}
	
	@Test
	public void testPostResponses() {
		assertTrue(post5.areResponsesEmpty());
		assertTrue(post2.getRoot() == post1);
		assertTrue(post1.getRespond(2) == post2);
		assertFalse(post1.getRespond(3) == post3);
		assertTrue(post2.getRespond(3) == post3);
		assertTrue(post1.getRespond(5) == post5);
		assertTrue(post4.getRespond(2) == null);
		assertFalse(post1.areResponsesEmpty());
		assertFalse(post2.areResponsesEmpty());
	}
}