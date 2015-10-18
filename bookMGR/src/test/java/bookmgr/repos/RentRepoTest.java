package bookmgr.repos;

import bookmgr.exceptions.AuthorAlreadyExistsException;
import bookmgr.exceptions.AuthorAndBookAreAlreadyConnectedException;
import bookmgr.exceptions.AuthorDoesntExistException;
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
import java.util.Date;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void ifNoCopiesAvailableToRentThrowsException() throws BookAlreadyExistsException,
            UserAlreadyExistsException, BookDoesntExistException,
            BookNotAvailableException, UnacceptableISBNException,
            AuthorAlreadyExistsException, AuthorDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {
        BookRepo bookrepo = new BookRepo();
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook("1234567891111", "Graveyard",
                "Neil Gaimans bestselling novel yet", "Anneli", 1991, 1);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        rentrepo.createRent(user.getInteger("id"), book.getString("ISBN"));
        rentrepo.createRent(user.getInteger("id"), book.getString("ISBN"));
    }

    @Test
    public void CreateRentCreatesNewRent() throws BookAlreadyExistsException,
            UserAlreadyExistsException, BookDoesntExistException,
            BookNotAvailableException, UnacceptableISBNException,
            AuthorDoesntExistException, AuthorAndBookAreAlreadyConnectedException,
            AuthorAlreadyExistsException {
        BookRepo bookrepo = new BookRepo();
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook("1234567891121", "Graveyard",
                "Neil Gaimans bestselling novel yet", "Anneli", 1991, 1);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        Rent rent = rentrepo.createRent(user.getInteger("id"), book.getString("isbn"));
        Assert.assertEquals(user.getInteger("id"), rent.getInteger("user_id"));
    }

    @Test
    public void availableCopiesReturnCorrectCopies() throws BookAlreadyExistsException,
            UserAlreadyExistsException, BookDoesntExistException,
            BookNotAvailableException, UnacceptableISBNException,
            AuthorDoesntExistException, AuthorAndBookAreAlreadyConnectedException,
            AuthorAlreadyExistsException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet",
                "Anneli", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        rentrepo.createRent(user_id, isbn);
        rentrepo.createRent(user_id, isbn);
        Assert.assertEquals(1, rentrepo.availableCopies(book_id));
    }

    @Test(expected = RentDoesntExistException.class)
    public void fetchRentThrowsRentDoesntExistException() throws RentDoesntExistException,
            UserAlreadyExistsException, BookAlreadyExistsException,
            UnacceptableISBNException, AuthorAlreadyExistsException,
            AuthorDoesntExistException, BookDoesntExistException, AuthorAndBookAreAlreadyConnectedException {
        RentRepo rentrepo = new RentRepo();
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet",
                "Anneli", 1991, 3);
        rentrepo.fetchRent(user.getInteger("id"), isbn);

    }

    @Test
    public void ReturnRentChangesHasReturnedTo1() throws BookAlreadyExistsException,
            UserAlreadyExistsException, BookDoesntExistException,
            BookNotAvailableException, RentDoesntExistException,
            UserDoesntExistException, UnacceptableISBNException,
            AuthorAlreadyExistsException, AuthorDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet",
                "Anneli", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        Rent rent = rentrepo.createRent(user_id, isbn);
        rent = rentrepo.returnBook(user_id, isbn);
        int hasReturned = rent.getInteger("hasReturned");
        Assert.assertEquals(1, hasReturned);
    }

    @Test
    public void ExtendRentExtendsRent() throws AuthorAlreadyExistsException,
            BookAlreadyExistsException, UnacceptableISBNException,
            UserAlreadyExistsException, AuthorDoesntExistException,
            BookDoesntExistException, AuthorAndBookAreAlreadyConnectedException,
            BookNotAvailableException, RentDoesntExistException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet",
                "Anneli", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        Rent rent = rentrepo.createRent(user_id, isbn);
        Date due_ate = rent.getDate("due_date");
        rentrepo.extendRent(user_id, isbn);
        rent = rentrepo.fetchRent(user_id, isbn);

        Assert.assertNotSame(due_ate, rent.getDate("due_date"));
    }

    @Test
    public void ReturnBookAddsFeeIfBookIsLate() throws AuthorAlreadyExistsException,
            BookAlreadyExistsException, UnacceptableISBNException,
            UserAlreadyExistsException, AuthorDoesntExistException,
            BookDoesntExistException, AuthorAndBookAreAlreadyConnectedException,
            BookNotAvailableException, RentDoesntExistException, UserDoesntExistException {
        BookRepo bookrepo = new BookRepo();
        String isbn = "1234567891121";
        bookrepo.createAuthor("Anneli");
        Book book = bookrepo.createBook(isbn, "Graveyard", "Neil Gaimans bestselling novel yet",
                "Anneli", 1991, 3);
        UserRepo userrepo = new UserRepo();
        User user = userrepo.createUser("ananas", "pineapple");
        RentRepo rentrepo = new RentRepo();
        int book_id = book.getInteger("id");
        int user_id = user.getInteger("id");
        Rent rent = rentrepo.createRent(user_id, isbn);

        rent.set("due_date", "2015 - 10 - 01");
        rent.saveIt();
        user = userrepo.fetchUser(user_id);
        int feesB = user.getInteger("fees");
        rentrepo.returnBook(user_id, isbn);
        user = userrepo.fetchUser(user_id);
        int feesA = user.getInteger("fees");

        Assert.assertTrue(feesA > feesB);
    }
}
