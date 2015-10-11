package bookmgr.repos;

import bookmgr.exceptions.BookAlreadyExistsException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.BookNotAvailableException;
import bookmgr.exceptions.RentDoesntExistException;
import bookmgr.exceptions.UnacceptableISBNException;
import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.models.Book;
import bookmgr.models.Rent;
import bookmgr.models.User;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentRepoTest {

    public RentRepoTest() {
    }

    @Before
    public void setUp() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
        Base.openTransaction();
    }

    @After
    public void tearDown() {
        Base.rollbackTransaction();
        Base.close();
    }

    @Test(expected = BookNotAvailableException.class)
    public void ifNoCopiesAvailableToRentThrowsException() throws BookAlreadyExistsException, UserAlreadyExistsException, BookDoesntExistException, BookNotAvailableException, UnacceptableISBNException {
        BookRepo bookrepo = new BookRepo();
        Book book = bookrepo.createBook("1234567891111", "Graveyard", "Neil Gaimans bestselling novel yet", 1991, 1);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        rentrepo.createRent(user.getInteger("id"), book.getString("ISBN"));
        rentrepo.createRent(user.getInteger("id"), book.getString("ISBN"));
    }

//    @Test
//    public void CreateRentCreatesNewRent() throws BookAlreadyExistsException, UserAlreadyExistsException, BookDoesntExistException, BookNotAvailableException {
//        BookRepo bookrepo = new BookRepo();
//        Book book = bookrepo.addBook("1234567891121", "Graveyard", "Neil Gaimans bestselling novel yet", 1991, 1);
//        UserRepo userrepo = new UserRepo();
//        User user = userrepo.createUser("ananas", "pineapple");
//        RentRepo rentrepo = new RentRepo();
//        boolean success = rentrepo.createRent(user.getInteger("id"), book.getInteger("id"));
//        Assert.assertTrue(success);
//    }

    @Test
    public void availableCopiesReturnCorrectCopies() throws BookAlreadyExistsException, UserAlreadyExistsException, BookDoesntExistException, BookNotAvailableException, UnacceptableISBNException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        rentrepo.createRent(user_id, isbn);
        rentrepo.createRent(user_id, isbn);
        Assert.assertEquals(1, rentrepo.availableCopies(book_id));
    }
    
    @Test (expected = RentDoesntExistException.class)
    public void fetchRentThrowsRentDoesntExistException() throws RentDoesntExistException {
        RentRepo rentrepo = new RentRepo();
        rentrepo.fetchRent(87583);
        
    }
    
    @Test
    public void ReturnRentChangesHasReturnedTo1 () throws BookAlreadyExistsException, UserAlreadyExistsException, BookDoesntExistException, BookNotAvailableException, RentDoesntExistException, UserDoesntExistException, UnacceptableISBNException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        Rent rent = rentrepo.createRent(user_id, isbn);
        rent = rentrepo.returnBook(rent.getInteger("id"));
        int hasReturned = rent.getInteger("hasReturned");
        Assert.assertEquals(1, hasReturned);
    }
}
