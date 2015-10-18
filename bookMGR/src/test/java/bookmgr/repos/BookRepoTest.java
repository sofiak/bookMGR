package bookmgr.repos;

import bookmgr.exceptions.AuthorAlreadyExistsException;
import bookmgr.exceptions.AuthorAndBookAreAlreadyConnectedException;
import bookmgr.exceptions.AuthorAndBookAreNotConnectedException;
import bookmgr.exceptions.AuthorDoesntExistException;
import bookmgr.exceptions.BookAlreadyExistsException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.CantRemoveBooksNotOnTheShelfException;
import bookmgr.exceptions.UnacceptableISBNException;
import bookmgr.models.Author;
import bookmgr.models.Book;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookRepoTest {

    @Before
    public void setUp() throws Exception {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
        Base.openTransaction();
    }

    @After
    public void tearDown() {
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void AddBookWorks() throws BookAlreadyExistsException, UnacceptableISBNException,
            AuthorAlreadyExistsException, AuthorDoesntExistException,
            BookDoesntExistException, AuthorAndBookAreAlreadyConnectedException {
        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("1234567895446", "Harry Potter", "Good book", "Anneli", 1999, 5);

        String title = book.getString("title");
        Assert.assertEquals("Harry Potter", title);
    }

    @Test(expected = BookAlreadyExistsException.class)
    public void CantCreateISBNDuplicates() throws BookAlreadyExistsException,
            UnacceptableISBNException, AuthorAlreadyExistsException,
            AuthorDoesntExistException, BookDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {

        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("1234567890123", "Harry Potter", "Good book", "Anneli", 1999, 5);
        book = newRepo.createBook("1234567890123", "Harry Potter", "Good book", "Anneli", 1999, 5);
    }

    @Test(expected = UnacceptableISBNException.class)
    public void createBookThrowsExceptionIfIsbnIsInvalid() throws BookAlreadyExistsException,
            UnacceptableISBNException, AuthorAlreadyExistsException,
            AuthorDoesntExistException, BookDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {

        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("123456789", "Harry Potter", "Good book", "Anneli", 1999, 5);
    }

    @Test
    public void fetchBookWorks() throws BookAlreadyExistsException,
            BookDoesntExistException, UnacceptableISBNException,
            AuthorAlreadyExistsException, AuthorDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {

        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("1234567895587", "Harry Potter", "Good book", "Anneli", 1999, 5);
        Book book1 = newRepo.fetchBook(book.getInteger("id"));

        Assert.assertEquals(book.get("title"), book1.get("title"));
    }

    @Test(expected = BookDoesntExistException.class)
    public void removeBookWorks() throws BookAlreadyExistsException,
            BookDoesntExistException, AuthorAlreadyExistsException,
            UnacceptableISBNException, AuthorDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {
        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("1234567898798", "Harry Potter",
                "Good book", "Anneli", 1999, 5);
        newRepo.removeBook("1234567898798");
        Book book1 = newRepo.fetchBook(book.getInteger("id"));
    }

    @Test(expected = BookDoesntExistException.class)
    public void fetchBookWorksWithWrongId() throws BookDoesntExistException {
        int randomId = 1234567891;
        BookRepo repo = new BookRepo();
        repo.fetchBook(randomId);
    }

    @Test
    public void editBookWorks() throws BookAlreadyExistsException,
            BookDoesntExistException, UnacceptableISBNException,
            AuthorAlreadyExistsException, AuthorDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException,
            CantRemoveBooksNotOnTheShelfException, AuthorAndBookAreNotConnectedException {
        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        newRepo.createBook("1234567897898", "Harry Potter", "Good book", "Anneli", 1999, 5);
        Book book = newRepo.editBook("1234567897898", "LOTR", "Good book", "Anneli", 1999, 5);
        Assert.assertEquals("LOTR", book.getString("title"));
    }

    @Test(expected = BookDoesntExistException.class)
    public void getBookThrowsException() throws BookDoesntExistException {
        BookRepo newRepo = new BookRepo();
        newRepo.GetBook("6887454845453");
    }

    @Test(expected = AuthorAlreadyExistsException.class)
    public void createAuthorThrowsException() throws AuthorAlreadyExistsException {
        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("R2D2");
        newRepo.createAuthor("R2D2");
    }

    @Test(expected = AuthorDoesntExistException.class)
    public void GetAuthorThrowsException() throws AuthorAlreadyExistsException,
            AuthorDoesntExistException {
        BookRepo newRepo = new BookRepo();
        newRepo.GetAuthor("R2D2");
    }

    @Test(expected = AuthorAndBookAreAlreadyConnectedException.class)
    public void addAuthorToBookThrowsException()
            throws AuthorAlreadyExistsException, BookAlreadyExistsException,
            UnacceptableISBNException, AuthorDoesntExistException,
            BookDoesntExistException, AuthorAndBookAreAlreadyConnectedException {
        BookRepo newRepo = new BookRepo();
        Author author = newRepo.createAuthor("Anneli");
        Book book = newRepo.createBook("1234567898798", "Harry Potter",
                "Good book", "Anneli", 1999, 5);
        newRepo.addAuthorToBook(book.getInteger("id"), author.getInteger("id"));
    }

    @Test(expected = AuthorDoesntExistException.class)
    public void fetchAuthorThrowsException() throws AuthorAlreadyExistsException,
            AuthorDoesntExistException {
        BookRepo newRepo = new BookRepo();
        newRepo.fetchAuthor(8888);
    }

    @Test(expected = AuthorDoesntExistException.class)
    public void removeAuthorThrowsException() throws AuthorAlreadyExistsException,
            AuthorDoesntExistException {
        BookRepo newRepo = new BookRepo();
        newRepo.removeAuthor(8888);
    }

    @Test(expected = AuthorAndBookAreNotConnectedException.class)
    public void removeAuthorFromBookThrowsException() throws AuthorAlreadyExistsException,
            AuthorDoesntExistException,
            BookAlreadyExistsException,
            UnacceptableISBNException,
            BookDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException,
            AuthorAndBookAreNotConnectedException {
        BookRepo newRepo = new BookRepo();
        newRepo.createAuthor("Anneli");
        Author author = newRepo.createAuthor("The Hulk");
        Book book = newRepo.createBook("1234567898798", "Harry Potter",
                "Good book", "Anneli", 1999, 5);
        newRepo.removeAuthorFromBook(book.getInteger("id"), author.getInteger("id"));
    }

}
