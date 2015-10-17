package bookmgr.repos;

import bookmgr.exceptions.AuthorDoesntExistException;
import bookmgr.exceptions.AuthorHasNoBooksException;
import bookmgr.exceptions.BookDoesntExistException;
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
        List<Rent> rents;

        if (bookStatus == 2) {
            rents = rent.where("user_id = ?", user_id);
        } else {
            rents = rent.where("user_id = ? AND hasReturned = ?", user_id, bookStatus);
        }
        return this.reportRentsToString(rents);
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

        return this.reportRentsToString(rents);
    }

    /**
     * Method creates a report of all book by a specific author
     *
     * @param name name of the author in String form
     *
     * @throws AuthorDoesntExistException
     * @throws BookDoesntExistException
     * @throws AuthorHasNoBooksException
     *
     * @return List of the books
     */
    public ArrayList<String> reportBooksByAuthor(String name) throws AuthorDoesntExistException,
            BookDoesntExistException, AuthorHasNoBooksException {
        BookRepo newRepo = new BookRepo();
        Author author = newRepo.GetAuthor(name);
        List<BookAuthor> bookAuthors = BookAuthor.where("author_id = ?", author.getInteger("id"));
        if (bookAuthors.isEmpty()) {
            throw new AuthorHasNoBooksException();
        } else {
            List<Book> booksbyauthor = new ArrayList<>();
            for (int i = 0; i < bookAuthors.size(); i++) {
                int bookId = bookAuthors.get(i).getInteger("book_id");
                Book book = newRepo.fetchBook(bookId);
                booksbyauthor.add(book);

            }
            return this.reportBookToString(booksbyauthor);
        }
    }

    /**
     * Method changes Admin's password
     *
     * @param adminId ID of the admin
     * @param password new password for the admin, as given by user
     *
     * @throws UserDoesntExistException if user(admin) doesn't exist
     *
     * @return List of the books
     */
    public boolean changeAdminPassword(int adminId, String password) throws UserDoesntExistException {
        UserRepo newRepo = new UserRepo();
        User user = newRepo.fetchUser(adminId);

        user.set("password", password);
        user.saveIt();
        return true;
    }

    /**
     * Method changes a user's password
     *
     * @param username username of the user whose password is to be changed
     * @param password new password as entered by user
     *
     * @throws UserDoesntExistException
     *
     * @return List of the books
     */
    public boolean changeAPassword(String username, String password) throws UserDoesntExistException {
        UserRepo newRepo = new UserRepo();
        User user = newRepo.getUser(username);

        user.set("password", password);
        user.saveIt();
        return true;
    }

    private ArrayList<String> reportRentsToString(List list) {
        ArrayList<String> newArray = new ArrayList<>();
        for (Object list1 : list) {
            newArray.add(list1.toString());
        }

        return newArray;
    }

    private ArrayList<String> reportBookToString(List<Book> list) {
        ArrayList<String> newArray = new ArrayList<>();
        BookRepo newRepo = new BookRepo();
        for (int i = 0; 1 < list.size(); i++) {
            Book book = list.get(i);
            newArray.add(""+book.getString("isbn")+", "+book.getString("title")+", "
                    +book.getInteger("pub_year")+", "+book.getInteger("copies") +" copies");
        }
        return newArray;
    }
}
