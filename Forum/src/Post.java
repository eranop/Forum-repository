import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class Post {
	
	private Post root;
	private Member publisher;
	private String title;
	private String content;
	private int index;
	private HashMap <Integer, Post> responses;
	/**
	 * new post 
	 */
	public Post(Member publisher, String title, String content, int index)
	{
		this.root = null;
		this.title = title;
		this.content = content;
		this.publisher = publisher;
		this.index = index;
		this.responses = new HashMap <Integer, Post>();
	}
	/**
	 * response post
	 */
	public Post(Member publisher, String title, String content, int index, Post root)
	{
		this.root = root;
		this.title = title;
		this.content = content;
		this.publisher = publisher;
		this.index = index;
		this.responses = new HashMap <Integer, Post>();
	}

	public Set<Integer> getResponsesIndex(){
		return responses.keySet();
	}
	
	public boolean areResponsesEmpty()
	{
		return responses.isEmpty();
	}
	
	public boolean addResponse(int index, Post post)
	{
		responses.put(index, post);
		return true;
	}
	
	public Post getRespond(int index)
	{
		return responses.get(index);
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
	public String getContent() 
	{
		return this.content;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public Post getRoot()
	{
		return this.root;
	}
	
	public void showPost()
	{
		System.out.println("");
		System.out.println("Post number: " + getIndex());
		System.out.println("Title: " + getTitle());
		System.out.println("");
		System.out.println("Content: " + getContent());
		System.out.println("");
		System.out.println("Written by: " + getPublisher());
	}
	
	/**
	 * recursivly unfolding all correspondence of a post.
	 */
	public void unfold()
	{
		if (this.root == null)
			System.out.println("Post " + this.getIndex() + ". " + this.getTitle() + ". By: " + this.getPublisher());
		if (areResponsesEmpty())
			return;
		
		Collection<Post> cl = responses.values();
		Object tempposts[] = cl.toArray();
		for (int i = 0; i < tempposts.length; i++)
		{
			System.out.println("Respond to " + this.getIndex() + ". Post " + ((Post)tempposts[i]).getIndex() + ". " + ((Post)tempposts[i]).getTitle() + ". By: " + ((Post)tempposts[i]).getPublisher());
			((Post)tempposts[i]).unfold();
		}
	}

	public String getPublisher() {
		return publisher.get_userName();
	}


	

}
