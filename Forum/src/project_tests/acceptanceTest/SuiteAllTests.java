package project_tests.acceptanceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({
	unitTests.ForumTest.class, 
	unitTests.PolicyTest.class, 
	unitTests.PostTest.class, 
	unitTests.SubForumTest.class, 
	
	//acceptance tests 
	LoginTest.class, 
	//ForumSiteTest.class, 
	PostTest.class, 
	registerTest.class,
	notificationsTest.class,
	
})public class SuiteAllTests
{
 /* empty class */
}



