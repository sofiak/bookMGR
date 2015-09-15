/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.models.User;
import java.util.List;

public class UserRepo {

    public void UserRepo() {

    }

    public User createUser(String uname, String pw) {
        System.out.println(uname);
        if (this.CheckUserName(uname) == false) {
            User user = new User();
            user.set("username", uname);
            user.set("password", pw);
            user.saveIt();
            return user;
        } else {
            throw new RuntimeException();
        }
    }

    private boolean CheckUserName(String username) {
        User user = new User();
        List<User> users = User.where("username", username);
        if(users.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
}
