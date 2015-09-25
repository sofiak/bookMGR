/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.ReservationDoesntExistException;
import bookmgr.exceptions.UserAlreadyHasAReservationForThisBookException;
import bookmgr.models.Reservation;
import java.util.List;

public class ReservationRepo {

    public ReservationRepo() {

    }

    public Reservation createReservation(int user_id, int book_id) throws UserAlreadyHasAReservationForThisBookException {
        if (this.CheckReservation(user_id, book_id) == false) {
            Reservation reservation = new Reservation();
            reservation.set("book_id", book_id);
            reservation.set("user_id", user_id);
            reservation.saveIt();
            return reservation;
        } else {
            throw new UserAlreadyHasAReservationForThisBookException();
        }
    }

    private boolean CheckReservation(int user_id, int book_id) {
        Reservation reservation = new Reservation();
        List<Reservation> users = reservation.where("user_id = ?", user_id, "book_id = ?", book_id);
        if (users.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Reservation fetchReservation(int reservation_id) throws ReservationDoesntExistException {

        Reservation reservation = Reservation.findById(reservation_id);
        if (reservation == null) {
            throw new ReservationDoesntExistException();
        } else {
            return reservation;
        }
    }

}
