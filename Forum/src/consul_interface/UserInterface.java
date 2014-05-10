package consul_interface;
import java.util.Scanner;
import java.util.Vector;

import allcode.ForumsManagement;
import allcode.Post;
import allcode.SubForum;

import allcode.*;


public class UserInterface {
	private ForumsManagement _fs;
	public UserInterface(ForumsManagement fs) {
		_fs = fs;
	}
	public void run() {
		int cmd=11;
		Scanner sc = new Scanner(System.in);

		System.out.println("GUI is runing now");
		System.out.println("choose one of the options");
		showMenu();

		while(cmd!=0){
			System.out.print("enter your choise: ");
			try{
			cmd=sc.nextInt();
			}catch(Exception e){ 
				System.out.print("try again:");
				cmd=sc.nextInt();
			}
			switch(cmd){
			case 0:
				break;
			case 1:
				createForumBySuperAdminGUI();
				break;
			case 2:
				createSubForumByAdminGUI();
				break;
			case 3:
				enterToForumAsGuestGUI();
				break;
			case 4:
				publishPostInSubforumGUI();
				break;
			case 5:
				publishResponsePostInSubforumGUI();
				break;
			case 6:
				loginToForumGUI();
				break;
			case 7:
				logoutFromForumGUI();
				break;
			case 8:
				registrationToForumGUI();
				break;
			case 9:
				showPostsOfSubforumGUI();
				break;
			case 10:
				showSubforumsOfForumGUI();
				break;
			case 11://done
				set2membersAsFriendsGUI(sc);
				break;
			case 12:
				complainOnMediatorGUI();
				break;
			case 13:
				deletePostFromSubforumGUI();
				break;
			default:
				System.out.print("wrong number inserted! ");

			}
		}
		sc.close();

	}
	private String[] getInformationFromUser(String[] fields){
		 Scanner sc= new Scanner(System.in);
		String []ans= new String[fields.length]; 
		for(int i=0; i< fields.length; i++){
			System.out.println("insert" + fields[i] + ": ");
			ans[i]=sc.next();
		}
		return ans;
	}
	
	private void deletePostFromSubforumGUI() {
		System.out.println("---delete post----");
		String[] fields={"forum name", "sub forum name", "post id", "moderator name"};
		fields=getInformationFromUser(fields);
		report res  =_fs.deletePostInSubForum(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
		System.out.println(res);
	}
	private void complainOnMediatorGUI() {
		System.out.println("---complain---");
		String[] fields={"userName", "moderator name", "forum name", "sub forum name", "complain content"};
		fields = getInformationFromUser(fields);

		report res = _fs.complain(fields[0], fields[1], fields[2], fields[3], fields[4]);
		System.out.println(res);
	}
	private void set2membersAsFriendsGUI(Scanner sc) {
		System.out.println("----set 2 members friends------");
		String[] fields={"forumName", "userName1", "userName2"};
		fields=getInformationFromUser(fields);

		boolean res=_fs.setFriends(fields[0],fields[1],fields[2]);
		System.out.println(res);
	}
	private void showSubforumsOfForumGUI() {
		System.out.println("-----show sub forum-----");
		String[] fields={"forumName"};
		fields=getInformationFromUser(fields);

		Vector<SubForum> res=_fs.showSubforumsOfForum(fields[0]);
		
		for(int i=0; i< res.size(); i++){
		System.out.println(res.elementAt(i).getName());
		}
	}
	private void showPostsOfSubforumGUI() {
		System.out.println("----show posts----");
		String[] fields={"forumName", "sub forum name"};
		fields=getInformationFromUser(fields);

		Vector<Post> res=_fs.showPostInSubForum(fields[0], fields[1]);
		
		for(int i=0; i< res.size(); i++){
		System.out.println(res.elementAt(i).getTitle());
		}
	}
	private void enterToForumAsGuestGUI() {
		System.out.println("---enter to forum---");
		showSubforumsOfForumGUI();

	}
	private void registrationToForumGUI() {
		String[] fields={"forumName", "userName", "passward", "email"};
		fields=getInformationFromUser(fields);
		
		boolean res=_fs.registerToForum(fields[0],fields[1],fields[2], fields[3]);
		System.out.println(res);
	}
	private void logoutFromForumGUI() {
		System.out.println("logout");
		boolean res=_fs.logout();
		System.out.println(res);
	}
	private void loginToForumGUI() {
		String[] fields={ "userName", "passward", "forumName"};
		fields=getInformationFromUser(fields);
		boolean res=_fs.login(fields[0], fields[1], fields[2]);
		System.out.println(res);
	
	}
	private void publishResponsePostInSubforumGUI() {
		String[] fields= {"forum name", "sub forum name", "user name", "title", "content", "post id for response"};
		fields=getInformationFromUser(fields);
		boolean res=_fs.writeResponsePostInSubForum(fields[0], fields[1], fields[2],
				fields[3],fields[4], Integer.parseInt(fields[5]));
		System.out.println(res);
	}
	private void publishPostInSubforumGUI() {
		String[] fields= {"forum name", "sub forum name", "user name", "title", "content"};
		fields=getInformationFromUser(fields);
		boolean res=_fs.writePostInSubForum(fields[0], fields[1], fields[2], fields[3],fields[4]);
		System.out.println(res);
	}
	private void createSubForumByAdminGUI() {
		String[] fields= {"forum name", "sub forum name", "description", "adminName"};
		fields=getInformationFromUser(fields);
		_fs.createSubForumInForumByAdmin(fields[0],fields[1],fields[2],fields[3]);
		
	}
	private void createForumBySuperAdminGUI() {
		String[] fields= {"name", "description", "adminName", "adminPass", "adminMail"};
		fields=getInformationFromUser(fields);
		_fs.createForum(fields[0],fields[1],fields[2],fields[3],fields[4]);
		

	}

	private void showMenu() {
		System.out.println("1- add new forum to forum system");
		System.out.println("2- add new sub forum to forum");
		System.out.println("3- enter to forum");
		System.out.println("4- write new post in sub forum");
		System.out.println("5- write response post");
		System.out.println("6- login");
		System.out.println("7- logout");
		System.out.println("8- register");
		System.out.println("9- show sub forum posts");
		System.out.println("10- show sub forums in forum");
		System.out.println("11- set 2 members as friends");
		System.out.println("12- complain");
		System.out.println("13- delete post from sub forum");
	}





}
