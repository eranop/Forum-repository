package allcode;

import java.util.HashMap;

import services.report;

/**
 * enable only flow functions
 */
public abstract class SiteConnection {
	protected static int IDs=0;
	protected Forum _forum;
	protected SubForum _subForum;
	protected Post _post;
	protected ForumsManagement _fs;

	protected int _id;

	public SiteConnection(ForumsManagement fs){
		_fs=fs;
		_forum=null;
		_subForum=null;
		_post=null;
		_id=IDs++;
	}

	/**
	 * for each state there is "in" and "out" functions
	 */
	public report enterForum(String forumName){
		Forum f= _fs.getForum(forumName);
		if(f==null){
			return report.NO_SUCH_FORUM;
		}
		System.out.println("entering forum" + forumName);
		_forum=f;
		return report.OK;
	}
	public report exitForum(){
		if(_forum==null)
			return report.NO_FORUM;
		_forum=null;
		_subForum=null;
		_post=null;
		return report.OK;
	}
	public report enterSubforum(String subforumName){
		if(_forum==null){
			return report.NO_FORUM;
		}
		SubForum sf= _forum.getSubForum(subforumName);
		if(sf==null){
			return report.NO_SUCH_SUBFORUM;
		}
		_subForum=sf;
		return report.OK;
	}
	public report exitSubforum(){
		if(_subForum==null)
			return report.NO_SUBFORUM;
		_subForum=null;
		return report.OK;
	}
	public report enterPost(int id){
		if(_subForum==null){
			return report.NO_SUBFORUM;
		}
		Post p=_subForum.getPostByIndex(id);
		if(p==null){
			return report.NO_SUCH_POST;
		}
		_post=p;
		return report.OK;
	}
	public report exitPost(){
		if(_post==null){
			return report.NO_POST;
		}
		_post=null;
		return report.OK;
	}

	public void reset(){
		_forum=null;
		_subForum=null;
		_post=null;
	}

	/**
	 * getters
	 */
	public int getID(){
		return _id;
	}

}
