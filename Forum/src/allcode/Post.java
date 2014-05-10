package allcode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class Post {
	
	private Post _root;
	private Member _publisher;
	private String _title;
	private String _content;
	private int _index;
	private HashMap <Integer, Post> _responses;
	/**
	 * new post 
	 */
	public Post(Member publisher, String title, String content, int index)
	{
		this._root = null;
		this._title = title;
		this._content = content;
		this._publisher = publisher;
		this._index = index;
		this._responses = new HashMap <Integer, Post>();
	}
	/**
	 * response post
	 */
	public Post(Member publisher, String title, String content, int index, Post root)
	{
		this._root = root;
		this._title = title;
		this._content = content;
		this._publisher = publisher;
		this._index = index;
		this._responses = new HashMap <Integer, Post>();
	}

	public Set<Integer> getResponsesIndex(){
		return _responses.keySet();
	}
	
	public boolean areResponsesEmpty()
	{
		return _responses.isEmpty();
	}
	
	public boolean addResponse(int index, Post post)
	{
		_responses.put(index, post);
		return true;
	}
	
	public Post getRespond(int index)
	{
		return _responses.get(index);
	}
	
	public int getIndex()
	{
		return this._index;
	}
	
	public String getContent() 
	{
		return this._content;
	}
	
	public String getTitle()
	{
		return this._title;
	}
	
	public Post getRoot()
	{
		return this._root;
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
		if (this._root == null)
			System.out.println("Post " + this.getIndex() + ". " + this.getTitle() + ". By: " + this.getPublisher());
		if (areResponsesEmpty())
			return;
		
		Collection<Post> cl = _responses.values();
		Object tempposts[] = cl.toArray();
		for (int i = 0; i < tempposts.length; i++)
		{
			System.out.println("Respond to " + this.getIndex() + ". Post " + ((Post)tempposts[i]).getIndex() + ". " + ((Post)tempposts[i]).getTitle() + ". By: " + ((Post)tempposts[i]).getPublisher());
			((Post)tempposts[i]).unfold();
		}
	}

	public String getPublisher() {
		return _publisher.get_userName();
	}


	

}
