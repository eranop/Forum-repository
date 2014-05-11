package allcode;

public class Main {

	public static void main(String[] args) {
//here
		//bla
		Forum frm = new Forum("animals", "this forum is about animals");
		SubForum sub = new SubForum("lion","this sub is about lions", frm);
		Member mem1 = new Member("Moti", "1234", "moti@walla.co.il");
		Member mem2 = new Member("Dani", "4321", "dani@walla.co.il");
		Member mem3 = new Member("RAFIKI", "9988", "RAFIKI@walla.co.il");
		
		sub.addPost(mem1, "the lion1", "lion 1 is bla 1");
		sub.addPost(mem2, "the lion2", "lion 2 is bla 2");
		sub.addPost(mem2, "the lion3", "lion 3 is bla 3");
		sub.addPost(mem1, "the lion4", "lion 4 is bla 4");
		sub.addPost(mem2, "the lion5", "lion 5 is bla 5");
		sub.deletePost(2);
		sub.addPost(mem2, "the lion6", "lion 6 is bla 6");
		
		sub.showRootPosts();
		sub.readPost(1);
		sub.readPost(2);
		
		sub.postRespond(mem3, 1, "the lion1 is wrong", "lion 1 ISNT BLA BLA 1");
		sub.postRespond(mem2, 1, "the lion1 true", "u are correct");
		sub.postRespond(mem1, 6, "bullshit!", "zzzzzzzzzzzzz");
		
		sub.showRootPosts();
		System.out.println("");
		sub.showAllPosts();
		
		System.out.println("");
		sub.unfoldPost(1);
		
		

	}

}
