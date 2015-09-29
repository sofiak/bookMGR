package bookmgr.repos;

import bookmgr.models.User;
import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.exceptions.UserDoesntExistException;
import java.util.List;

/**
 * Repo for user-related functions
 */

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
        } else {
            return user;
        }
    }

    public void removeUser(int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        user.delete();
    }

    public User editUser(int user_id, String newUsername) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        user.set("username", newUsername);
        user.saveIt();
        return user;
    }

    public boolean setNewPassword(int user_id, String oldPass, String newPass) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        if (user.getString("password").equals(oldPass)) {
            user.set("password", newPass);
            user.saveIt();
            return true;
        } else {
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

    public void addFee(double fee, int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        double currentFees = user.getDouble("fees");
        currentFees += fee;
        user.setInteger("fees", currentFees);
        user.saveIt();
    }

    public boolean payFee(double fee, int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        double currentFees = user.getDouble("fees");
        if (currentFees >= fee) {
            currentFees -=fee;
            user.setInteger("fees", currentFees);
            user.saveIt();
            return true;
        } else {
            return false;
        }
    }
    
    public int fetchFees(int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        return user.getInteger("fees");
    }

}
