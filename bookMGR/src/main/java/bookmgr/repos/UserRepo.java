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
        if (User.where("id", user_id).isEmpty()) {
            throw new UserDoesntExistException();
        } else {
            User user = User.findById(user_id);
            return user;
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
