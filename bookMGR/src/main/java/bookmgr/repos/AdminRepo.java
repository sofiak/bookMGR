package bookmgr.repos;

import bookmgr.exceptions.AuthorDoesntExistException;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.exceptions.UserHasUnresolvedFeesOrRentsException;
import bookmgr.models.Author;
import bookmgr.models.Book;
import bookmgr.models.BookAuthor;
import bookmgr.models.Rent;
import bookmgr.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Repo for all admin-related functions
 */
public class AdminRepo {

    public AdminRepo() {
    }

    /**
     * Method finds out if the passed user is an admin
     *
     * @param user_id ID of the user
     *
     * @return "true" if user is an admin, "false" if not
     */
    public boolean checkIfAdmin(int user_id) {
        UserRepo userrepo = new UserRepo();
        User user = User.findById(user_id);

        return user.getBoolean("isAdmin");
    }

    /**
     * Method removes user safely
     *
     * @param user_id ID for the user
     *
     * @throws UserDoesntExistException if user doesn't exist
     * @throws UserHasUnresolvedFeesOrRentsException if user has any unresolved
     * fees or rents
     */
    public void removeUser(int user_id) throws UserDoesntExistException, UserHasUnresolvedFeesOrRentsException {
        UserRepo userrepo = new UserRepo();
        User user = userrepo.fetchUser(user_id);
        ArrayList<String> rents = this.reportForRents(user_id, 0);
        if (rents.isEmpty() && userrepo.fetchFees(user_id) == 0) {
            userrepo.removeUser(user_id);
        } else {
            throw new UserHasUnresolvedFeesOrRentsException();
        }

    }

    /**
     * Method creates a List report of rents
     *
     * @param user_id ID of the user whose rents are collected
     * @param bookStatus specifies if loans to be collected are returned,
     * current, or all
     *
     * @return List of the rents
     */
    public ArrayList<String> reportForRents(int user_id, int bookStatus) {
        RentRepo rentrepo = new RentRepo();
        Rent rent = new Rent();
        List<Rent> rents = rent.where("user_id = ? AND hasReturned = ?", user_id, bookStatus);
        return this.reportToString(rents);
    }

    /**
     * Method creates a report of rents by all users
     *
     * @param bookStatus specifies if loans to be collected are returned,
     * current, or all
     *
     * @return List of rents
     */
    public ArrayList<String> reportForAllRents(int bookStatus) {
        RentRepo rentrepo = new RentRepo();
        Rent rent = new Rent();
        List<Rent> rents = rent.where("hasReturned = ?", bookStatus);
       
        return this.reportToString(rents);
    }

    /**
     * Method creates a report of all book by a specific author
     *
     * @param name name of the author in String form
     *
     * @return List of the books
     */
    public ArrayList<String> reportBooksByAuthor(String name) throws AuthorDoesntExistException {
        BookRepo newRepo = new BookRepo();
        Author author = newRepo.GetAuthor(name);
        List<BookAuthor> booksbyauthor = Book.where("author_id = ?", author.get("id"));
        return this.reportToString(booksbyauthor);
    }

    public boolean changeAdminPassword(int adminId, String password) throws UserDoesntExistException {
        UserRepo newRepo = new UserRepo();
        User user = newRepo.fetchUser(adminId);

        user.set("password", password);
        user.saveIt();
        return true;
    }

    public boolean changeAPassword(String username, String password) throws UserDoesntExistException {
        UserRepo newRepo = new UserRepo();
        User user = newRepo.getUser(username);

        user.set("password", password);
        user.saveIt();
        return true;
    }

    private ArrayList<String> reportToString(List list) {
        ArrayList<String> newArray = new ArrayList<>();
        for (Object list1 : list) {
            newArray.add(list1.toString());
        }
        
        return newArray;
    }
}
