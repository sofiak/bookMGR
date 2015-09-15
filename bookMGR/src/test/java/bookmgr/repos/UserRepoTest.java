/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.models.User;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sofia
 */
public class UserRepoTest {

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
    public void CreateNewUserCreatesRightUserName() {
        UserRepo newrepo = new UserRepo();
        String userName = "ananas";
        String passw = "123";
        User user = newrepo.createUser(userName, passw);

        String username = user.getString("username");

        Assert.assertEquals(userName, username);
    }
}
