package project_tests.acceptanceTest;
import project_tests.unitTests.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({
/*unit tests
	PostTest.class,
    ForumSystemTest.class,
    ForumTest.class,
    SubForumTest.class,
*/
//acceptance tests
    PostsTests.class,
    
})public class SuiteAllTests
{
 /* empty class */
}



