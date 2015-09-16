package bookmgr.repos;

import bookmgr.models.User;
import bookmgr.exceptions.UserAlreadyExistsException;
import java.util.List;

public class UserRepo {

    public void UserRepo() {

    }

    public User createUser(String uname, String pw) throws UserAlreadyExistsException {
        if (this.CheckUserName(uname) == false) {
            User user = new User();
            user.set("username", uname);
            user.set("password", pw);
            user.saveIt();
            return user;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    public void removeUser(String username) {
        if (this.CheckUserName(username) == true) {
            User user = new User();
            List<User> users = user.where("username = ?", username);
            users.get(0).delete();
        }else{
            
        }
    }

    private boolean CheckUserName(String username) {
        User user = new User();
        List<User> users = user.where("username = ?", username);
        if (users.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
