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

    /**
     * Method creates a user, if the username isn't already taken
     *
     * @param uname the username the user has given
     * @param pw the password the user has given
     *
     * @throws UserAlreadyExistsException if the username is already taken
     *
     * @return todennäköisyys kalibroituna
     */
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

    /**
     * Method fetches a user based on ID
     *
     * @param user_id ID for the user
     *
     * @throws UserDoesntExistException if user doesn't exist
     *
     * @return User object
     */
    public User fetchUser(int user_id) throws UserDoesntExistException {

        User user = User.findById(user_id);
        if (user == null) {
            throw new UserDoesntExistException();
        } else {
            return user;
        }
    }

    /**
     * Method deletes a user
     *
     * @param user_id ID for the user
     *
     * @throws UserDoesntExistException if user doesn't exist
     */
    public void removeUser(int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        user.delete();
    }

    /**
     * Method changes the username of a specific user if the username isn't
     * taken
     *
     * @param user_id ID of the user
     * @param newUsername new username
     *
     * @throws UserDoesntExistException if the ID doesn't match any user
     * @throws UserAlreadyExistsException if username is taken
     *
     * @return User object
     */
    public User editUser(int user_id, String newUsername) throws UserDoesntExistException, UserAlreadyExistsException {
        User user = this.fetchUser(user_id);
        if (this.CheckUserName(newUsername) == false) {
            user.set("username", newUsername);
            user.saveIt();
            return user;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    /**
     * Method changes the password of a specific user if the old password is
     * entered correctly
     *
     * @param user_id ID of the user
     * @param oldPass user input old pw, to match with the old pw in DB
     * @param newPass user input new pw
     *
     * @throws UserDoesntExistException if user doesn't exist
     *
     * @return returns true if change was successful
     */
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

    /**
     * Method checks if a certain username is in user
     *
     * @param username username
     *
     * @return true if username is taken, false if not
     */
    private boolean CheckUserName(String username) {
        User user = new User();
        List<User> users = user.where("username = ?", username);
        if (users.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method adds fees to a certain users balance
     *
     * @param fee the fee calculated in a RentRepo method
     * @param user_id ID of the user
     *
     * @throws UserDoesntExistException if user doesn't exist
     */
    public void addFee(double fee, int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        double currentFees = user.getDouble("fees");
        currentFees += fee;
        user.setInteger("fees", currentFees);
        user.saveIt();
    }

    /**
     * Method resolves users fees balance with given amount
     *
     * @param fee how much is user paying
     * @param user_id ID of user
     * 
     * @throws UserDoesntExistException if user doesn't exist
     *
     * @return true if payment is successful, false if not
     */
    public boolean payFee(double fee, int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        double currentFees = user.getDouble("fees");
        if (currentFees >= fee) {
            currentFees -= fee;
            user.setInteger("fees", currentFees);
            user.saveIt();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method fetches a certain users active balance
     *
     * @param user_id ID of the user
     * 
     * @throws UserDoesntExistException if user doesn't exist
     *
     * @return current fees
     */
    public double fetchFees(int user_id) throws UserDoesntExistException {
        User user = this.fetchUser(user_id);
        return user.getDouble("fees");
    }

}
