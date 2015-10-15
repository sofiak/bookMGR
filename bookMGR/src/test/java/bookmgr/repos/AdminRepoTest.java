package bookmgr.repos;

import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.models.User;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminRepoTest {

//    public AdminRepoTest() {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
//        Base.openTransaction();
//    }
//
//    @After
//    public void tearDown() {
//        Base.rollbackTransaction();
//        Base.close();
//    }
//
//    @Test
//    public void CheckIfAdminWorksIfUserIsntAdmin() throws UserAlreadyExistsException {
//        UserRepo newrepo = new UserRepo();
//        User user = newrepo.createUser("username", "password");
//
//        int userId = user.getInteger("id");
//
//        AdminRepo adRepo = new AdminRepo();
//        boolean success = adRepo.checkIfAdmin(userId);
//        Assert.assertFalse(success);
//    }
//
//    @Test
//    public void CheckIfAdminWorksIfUserIsAdmin() {
//
//    }
}
