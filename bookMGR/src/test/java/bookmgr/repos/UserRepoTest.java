package bookmgr.repos;
 
import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.models.User;
import java.util.List;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class UserRepoTest {
 
    String userName = "ananas";
    String passw = "123";
 
    public UserRepoTest() {
    }
 
    @Before
    public void setUp() throws Exception {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
        Base.openTransaction();
    }
 
    @After
    public void tearDown() {
        Base.rollbackTransaction();
        Base.close();
    }
 
    @Test
    public void CreateNewUserCreatesRightUserName() throws UserAlreadyExistsException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser(userName, passw);
 
        String username = user.getString("username");
 
        Assert.assertEquals(userName, username);
    }
 
    @Test (expected=UserAlreadyExistsException.class)
    public void CantCreateUsernameDuplicates() throws UserAlreadyExistsException {
        
        UserRepo newrepo = new UserRepo();
        User user1 = newrepo.createUser(userName, passw);
        User user2 = newrepo.createUser(userName, passw);
        
    }
    
    @Test
    public void fetchUserWorks() throws UserAlreadyExistsException {
        UserRepo newrepo = new UserRepo();
        User user1 = newrepo.createUser(userName, passw);
        
        int thatID = user1.getInteger("id");
        
        User user2 = newrepo.fetchUser(thatID);
        
        Assert.assertEquals(user1, user2);
    }
}