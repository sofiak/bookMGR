package bookmgr.repos;

import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.models.User;
import java.sql.Timestamp;
import java.util.Date;
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

    @Test(expected = UserAlreadyExistsException.class)
    public void CantCreateUsernameDuplicates() throws UserAlreadyExistsException {

        UserRepo newrepo = new UserRepo();
        User user1 = newrepo.createUser(userName, passw);
        User user2 = newrepo.createUser(userName, passw);

    }

    @Test
    public void fetchUserWorks() throws UserDoesntExistException, UserAlreadyExistsException {
        UserRepo newrepo = new UserRepo();
        User user1 = newrepo.createUser(userName, passw);

        User user2 = newrepo.fetchUser(user1.getInteger("id"));

        Assert.assertEquals(user2.get("username"), user1.get("username"));
    }

    @Test(expected = UserDoesntExistException.class)
    public void removeUserWorks() throws UserAlreadyExistsException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user1 = newrepo.createUser(userName, passw);
        newrepo.removeUser(user1.getInteger("id"));
        newrepo.fetchUser(user1.getInteger("id"));
    }

    @Test(expected = UserDoesntExistException.class)
    public void fetchUserWorksWithWrongId() throws UserDoesntExistException {
        int randomId = 1234567891;
        UserRepo newrepo = new UserRepo();
        newrepo.fetchUser(randomId);
    }
    
    @Test
    public void editUserWorks() throws UserAlreadyExistsException, UserDoesntExistException{
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser(userName, passw);
        User user1 = newrepo.editUser(user.getInteger("id"), "anakonda");
        Assert.assertEquals("anakonda", user1.getString("username"));
    }
    
    @Test
    public void newPasswordWorks() throws UserAlreadyExistsException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser(userName, passw);
        boolean success = newrepo.setNewPassword(user.getInteger("id"), passw, "anakonda");
        Assert.assertTrue(success);
    }
    
    @Test
    public void timestamp() {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser(userName, passw);
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        int id = user.getInteger("id");
        Date due_date = fetchUser(id).getDate("due_date");

    }
}
