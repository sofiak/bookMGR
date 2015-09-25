package bookmgr.repos;

import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.BookNotAvailableException;
import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.models.Book;
import bookmgr.models.Rent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Repo for book-related functions
 */
public class RentRepo {

    public void RentRepo() {
    }

    /**
     * Method for creating a Rent object with given attributes, User and Book
     *
     * @param user_id id for User
     * @param book_id id for Book
     *
     * @return returns true if creation was successful
     */
    public boolean createRent(int user_id, int book_id) throws BookDoesntExistException, BookNotAvailableException {
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
            return true;
        }
    }

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
     * @param rent_id id for Rent object
     *
     * @return returns true if return was successful
     */
    public boolean returnBook(int rent_id) throws RentDoesntExistException {
        Rent rent = fetchRent(rent_id);
        int user_id = rent.getInteger("user_id");
        Date date = new Date();
        Date due_date = rent.getDate("due_date");

        if (date.before(due_date)) {
            rent.delete();
            return true;
        } else {
            int days = this.daysBetween(date, due_date);
            double fees = 0.5 * days;
            UserRepo userrepo = new UserRepo();
            userrepo.addFee(fees, user_id);
            rent.delete();
            return true;
        }

    }

    private int daysBetween(Date date, Date due_date) {
        long diff = date.getTime() - due_date.getTime();
        long diffDays = (diff / (24 * 60 * 60 * 1000));

        diffDays = Math.round(diffDays);
        int diffInt = (int) (long) diffDays;
        return diffInt;
    }

    public void extendRent(int rent_id) throws RentDoesntExistException {
        Rent rent = this.fetchRent(rent_id);
        Date date = this.calculateDueDate();
        rent.set("due_date", date);
        rent.saveIt();
    }

    public Rent fetchRent(int rent_id) throws RentDoesntExistException {
        Rent rent = Rent.findById(rent_id);
        if (rent == null) {
            throw new RentDoesntExistException();
        } else {
            return rent;
        }
    }

    public int availableCopies(int book_id) throws BookDoesntExistException {
        Rent rent = new Rent();
        BookRepo bookrepo = new BookRepo();
        Book book = bookrepo.fetchBook(book_id);
        List<Rent> rents = rent.where("book_id = ?", book_id);
        int copies = book.getInteger("copies");
        copies -= (rents.size() + 1);
        return copies;
    }
}
