/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.bookmgr;

import bookmgr.exceptions.UnauthorizedException;
import bookmgr.models.User;
import bookmgr.repos.UserRepo;
import java.util.List;

/**
 *
 * @author Sofia
 */
public class Auth {
    
    public Auth() {
        
    }
    
    public User signIn(String username, String password) throws UnauthorizedException {
        UserRepo userrepo = new UserRepo();
        List<User> userlist = User.where("username = ?", username);
        if(userlist.isEmpty()) {
            throw new UnauthorizedException();
        }else{
        User user = userlist.get(0);
        if(user.get("username").equals(username)) {
            return user;
        }else{
            throw new UnauthorizedException();
        }
        }
    }
    
}
