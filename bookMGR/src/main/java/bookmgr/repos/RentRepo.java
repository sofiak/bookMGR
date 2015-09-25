/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.BookNotAvailableException;
import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.models.Book;
import bookmgr.models.Rent;
import bookmgr.models.Reservation;
import bookmgr.models.User;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RentRepo {

    public void RentRepo() {

    }

    public boolean newRent(int user_id, int book_id) throws BookDoesntExistException, BookNotAvailableException {
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

    public Date calculateDueDate() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        date = calendar.getTime();
        return date;
    }

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

    public boolean extendRent(int rent_id) {

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
        copies -= (rents.size() - 1);

        if (copies > 0) {
            Reservation reservation = new Reservation();
            List<Reservation> reservations = reservation.where("book_id = ?", book_id);
            int availableCopies = copies - (reservations.size() + 1);
            return availableCopies;
        } else {
            return copies;
        }
    }
}
