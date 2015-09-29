/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.exceptions.UserHasUnresolvedFeesOrRentsException;
import bookmgr.models.Book;
import bookmgr.models.BookAuthor;
import bookmgr.models.Rent;
import bookmgr.models.User;
import java.util.List;
import org.javalite.activejdbc.Model;

/**
 * Repo for all admin-related functions
 */
public class AdminRepo {
    
    public AdminRepo() {
    }
    
    public boolean checkIfAdmin(int user_id){
        UserRepo userrepo = new UserRepo();
        User user = User.findById(user_id);
        
        if(user.getInteger("isAdmin") == 1) {
            return true;
        }else {
            return false;
        }
    }
    
    public void removeUser(int user_id) throws UserDoesntExistException, UserHasUnresolvedFeesOrRentsException {
        UserRepo userrepo = new UserRepo();
        User user = userrepo.fetchUser(user_id);
        List<Rent> rents = this.reportForRents(user_id, 0);
        if(rents.isEmpty() && userrepo.fetchFees(user_id) == 0) {
            userrepo.removeUser(user_id);
        }else{
            throw new UserHasUnresolvedFeesOrRentsException();
        }
        
    }
    
    public List<Rent> reportForRents(int user_id, int bookStatus) {
        RentRepo rentrepo = new RentRepo();
        Rent rent = new Rent();
        List<Rent> rents = rent.where("user_id = ? AND hasReturned = ?", user_id, bookStatus);
        return rents;
    }
    
    public List<Rent> reportForAllRents(int bookStatus) {
        RentRepo rentrepo = new RentRepo();
        Rent rent = new Rent();
        List<Rent> rents = rent.where("hasReturned = ?", bookStatus);
        return rents;
    }
    
    
    public List<BookAuthor> reportBooksByAuthor(int author_id) {
        List<BookAuthor> booksbyauthor = Book.where("author_id = ?", author_id);
        return booksbyauthor;
    }
}
