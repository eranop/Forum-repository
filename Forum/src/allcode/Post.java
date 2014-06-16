package allcode;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Post")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name="post_index")
	private int _postID;
	
	

	@ManyToOne
	@Cascade(value={CascadeType.ALL})
	@JoinColumn(name="root_post")
	private Post _root;
	
	@ManyToOne
	@Cascade(value={CascadeType.ALL})
	@JoinColumn(name="member_id")
	private Member _publisher;
	
	@Column (name="post_title",length=8000)
	private String _title;
	
	@Column (name="post_content",length=8000)
	private String _content;
	


	@Column(name = "post_index_per_subforum")
	private int _index;
	
	
	@ElementCollection(fetch=FetchType.EAGER)

	@Cascade(value={CascadeType.ALL})

	  @MapKeyColumn(name="_postID")
	@Column(name="respond_post")
	@CollectionTable(name="responds",joinColumns={@JoinColumn(name="original_post_index")})
	private Map <Integer, Post> _responses;
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
	
	public Post(){
		
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
	
	public void setContent(String content) 
	{
		this._content = content;
	}
	
	public String getTitle()
	{
		return this._title;
	}
	
	public void setTitle(String title) 
	{
		this._title = title;
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

	public Member getMember(){
		return _publisher;
	}
	
	public Map <Integer, Post> getResponds()
	{
		return this._responses;
	}

	public Collection getCollectionResponds()
	{
		return this._responses.values();
	}
}
