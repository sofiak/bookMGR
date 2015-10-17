package bookmgr.repos;

import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.BookNotAvailableException;
import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.models.Book;
import bookmgr.models.Rent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Repo for rent-related functions
 */
public class RentRepo {

    public void RentRepo() {
    }

    /**
     * Method for creating a Rent object with given attributes, User and Book
     *
     * @param user_id id for User
     * @param ISBN ISBN for the book
     *
     * @throws BookDoesntExistException if book does not exist
     * @throws BookNotAvailableException if the book has no available copies
     *
     * @return Rent object
     */
    public Rent createRent(int user_id, String ISBN) throws BookDoesntExistException, BookNotAvailableException {
        BookRepo newRepo = new BookRepo();
        Book book = newRepo.GetBook(ISBN);
        int book_id = book.getInteger("id");
        int availableCopies = this.availableCopies(book_id);

        if (availableCopies == 0) {
            throw new BookNotAvailableException();
        } else {
            Rent rent = new Rent();
            rent.set("user_id", user_id);
            rent.set("book_id", book_id);
            Date due_date = this.calculateDueDate();
            rent.set("due_date", due_date);
            rent.saveIt();
            return rent;
        }
    }

    /**
     * Method calculates a due date 30 days from current day onward
     *
     * @return the calculated due date
     */
    private Date calculateDueDate() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        date = calendar.getTime();
        return date;
    }

    /**
     * Method for returning a rented book
     *
     * @param user_id ID for the user
     * @param ISBN ISBN of the book
     *
     * @throws RentDoesntExistException if rent doesn't exist
     * @throws UserDoesntExistException if user doesn't exist
     * @throws BookDoesntExistException is book doesn't exist
     *
     * @return Rent object
     */
    public Rent returnBook(int user_id, String ISBN) throws RentDoesntExistException, 
            UserDoesntExistException, BookDoesntExistException {
        BookRepo newRepo = new BookRepo();
        Book book = newRepo.GetBook(ISBN);
        int book_id = book.getInteger("id");
        Rent rent = fetchRent(user_id, ISBN);
        Date date = new Date();
        Date due_date = rent.getDate("due_date");

        if (date.before(due_date)) {
            rent.set("hasReturned", 1);
            rent.saveIt();
            return rent;
        } else {
            int days = this.howManyDaysOverdue(date, due_date);
            double fees = 0.5 * days;
            UserRepo userrepo = new UserRepo();
            userrepo.addFee(fees, user_id);
            rent.set("hasReturned", 1);
            rent.saveIt();
            return rent;
        }

    }

    /**
     * Method calculates how many days a book is overdue
     *
     * @param date current date passed when method is called
     * @param due_date due date of the book
     *
     * @return diffInt the difference in days
     */
    private int howManyDaysOverdue(Date date, Date due_date) {
        long diff = date.getTime() - due_date.getTime();
        long diffDays = (diff / (24 * 60 * 60 * 1000));

        diffDays = Math.round(diffDays);
        int diffInt = (int) (long) diffDays;
        return diffInt;
    }

    /**
     * Method extends the renting period of a book by 30 days from current date
     *
     * @param user_id ID for the user
     * @param ISBN ISBN of the book
     *
     * @throws RentDoesntExistException if rent doesn't exist
     */
    public void extendRent(int user_id, String ISBN) throws RentDoesntExistException {
        Rent rent = this.fetchRent(user_id, ISBN);
        Date date = this.calculateDueDate();
        rent.set("due_date", date);
        rent.saveIt();
    }

    /**
     * Method fetches a rent based on ID of the user and ISBN of the book
     *
     * @param user_id ID of the user
     * @param ISBN ISBN of the book
     *
     * @throws RentDoesntExistException is rent doesn't exist
     *
     * @return Rent object
     */
    public Rent fetchRent(int user_id, String ISBN) throws RentDoesntExistException {
        List<Rent> rents = Rent.where("ISBN = ? AND user_id != ?", ISBN, user_id);
        if (rents.isEmpty()) {
            throw new RentDoesntExistException();
        } else {
            return rents.get(0);
        }
    }

    /**
     * Method calculates number of copies available of a certain book
     *
     * @param book_id id of the book
     * 
     * @throws BookDoesntExistException if book doesn't exist
     *
     * @return copies number of copies available to rent
     */
    public int availableCopies(int book_id) throws BookDoesntExistException {
        Rent rent = new Rent();
        BookRepo bookrepo = new BookRepo();
        Book book = bookrepo.fetchBook(book_id);
        List<Rent> rents = rent.where("book_id = ? AND hasReturned = ?", book_id, 0);
        int copies = book.getInteger("copies");
        if (rents.isEmpty()) {
            return copies;
        } else {
            int outOnRent = rents.size();
            copies = copies - outOnRent;
            return copies;
        }
    }
}
