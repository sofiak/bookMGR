/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.BookAlreadyExistsException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.models.Book;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sofia
 */
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
    public void AddBookWorks() throws BookAlreadyExistsException {
        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
       
        String title = book.getString("title");
        Assert.assertEquals("Harry Potter", title);
    }

    @Test(expected = BookAlreadyExistsException.class)
    public void CantCreateISBNDuplicates() throws BookAlreadyExistsException {

        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
        book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
    }

    @Test
    public void fetchBookWorks() throws BookAlreadyExistsException, BookDoesntExistException {
        
        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
        Book book1 = repo.fetchBook(book.getInteger("id"));

        Assert.assertEquals(book.get("title"), book1.get("title"));
    }

    @Test(expected = BookDoesntExistException.class)
    public void removeBookWorks() throws BookAlreadyExistsException, BookDoesntExistException {
        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
        repo.removeBook(book.getInteger("id"));
        Book book1 = repo.fetchBook(book.getInteger("id"));
    }

    @Test(expected = BookDoesntExistException.class)
    public void fetchBookWorksWithWrongId() throws BookDoesntExistException {
        int randomId = 1234567891;
        BookRepo repo = new BookRepo();
        repo.fetchBook(randomId);
    }
    
    @Test
    public void editBookWorks() throws BookAlreadyExistsException, BookDoesntExistException{
        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
        book = repo.editBook(book.getInteger("id"),"1234567890123", "LOTR", "Good book", 1999, 5);
        Assert.assertEquals("LOTR", book.getString("title"));
    }
    
    @Test(expected = BookAlreadyExistsException.class)
    public void editBookthrowsExcetionWhenISBNexists() throws BookAlreadyExistsException, BookDoesntExistException{
        BookRepo repo = new BookRepo();
        Book book = repo.addBook("1234567890123", "Harry Potter", "Good book", 1999, 5);
        Book newBook = repo.addBook("1234567890567", "LOTR", "Best book", 1999, 5);
        book = repo.editBook(book.getInteger("id"),"1234567890567", "LOTR", "Best book", 1999, 5);
    }
}