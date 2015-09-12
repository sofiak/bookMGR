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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sofia
 */
public class UserRepoTest {
    
    public UserRepoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    public void CreateNewUser() {
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("sofia", "reset123");
        
        String username = user.getString("username");
        
        Assert.assertEquals("sofia", username);
    }
}
