/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.models.Rent;
import bookmgr.models.User;
import java.util.Date;

public class RentRepo {

    public void RentRepo() {

    }

    public Rent newRent(int user_id, int book_id) {

    }

    public boolean returnBook(int rent_id) throws RentDoesntExistException {
        Rent rent = fetchRent(rent_id);
        int user_id = rent.getInteger("user_id");
        Date date = new Date();
        Date due_date = rent.getDate("due_date");

        if (date.before(due_date)) {
            rent.delete();
        } else {
            int days = this.daysBetween(date, due_date);
            double fees = 0.5 * days;
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
}
