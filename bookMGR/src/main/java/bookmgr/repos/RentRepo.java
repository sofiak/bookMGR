/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.models.Rent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;

public class RentRepo {

    public void RentRepo() {

    }

    public Rent newRent(int user_id, int book_id) {

    }

    public boolean returnBook(int rent_id) throws RentDoesntExistException {
        Rent rent = fetchRent(rent_id);
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        Date due_date = fetchRent(rent_id).getDate("due_date");

        if (date.before(due_date)) {
            rent.delete();
        } else {
            int days = this.daysBetween(date, due_date);
            double fees = 0.5*days;
        }

    }

    private int daysBetween(Date firstDate, Date secondDate) {

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
}
