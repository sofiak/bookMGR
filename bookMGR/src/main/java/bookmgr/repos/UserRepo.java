/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.models.User;
import java.util.Date;
import java.sql.Timestamp;
        

public class UserRepo {
    
    public void UserRepo(){
        
    }
    
    public User createUser(String uname, String pw){
        User user = new User();
        user.set("username", uname);
        user.set("password", pw);
        user.saveIt();
        return user;
    }
    
}
