package bookmgr.repos;

import bookmgr.models.User;
import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.exceptions.UserDoesntExistException;
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

    public User fetchUser(int user_id) throws UserDoesntExistException {
        
        User user = User.findById(user_id);
        if (user == null) {
            throw new UserDoesntExistException();
        }else{
            return user;
        }
    }
    
    public void removeUser(int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        user.delete();
    }

    public User editUser(int user_id, String newUsername) throws UserDoesntExistException{
        User user = this.fetchUser(user_id);
        user.set("username", newUsername);
        user.saveIt();
        return user;
    }
    
    public boolean setNewPassword(int user_id, String oldPass, String newPass) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        if(user.get("password") == oldPass) {
            user.set("password", newPass);
            return true;
        }else{
            return false;
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
