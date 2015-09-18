/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.exceptions.BookAlreadyExistsException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.models.Book;
import java.util.List;

/**
 *
 * @author Sofia
 */
public class BookRepo {

    public BookRepo() {
    }

    public Book addBook(int isbn, String title, String description, int pubYear, int copies) throws BookAlreadyExistsException {
        if (this.CheckBook(isbn) == false) {
            Book book = new Book();
            book.set("ISBN", isbn);
            book.set("title", title);
            book.set("description", description);
            book.set("pub_year", pubYear);
            book.set("copies", copies);
            book.saveIt();
            return book;
        } else {
            throw new BookAlreadyExistsException();
        }
    }
    
    public Book editBook(int book_id, int isbn, String title, String description, int pubYear, int copies) throws BookDoesntExistException, BookAlreadyExistsException{
        Book book = this.fetchBook(book_id);
        List<Book> books = Book.where("ISBN = ? AND id != ?", isbn, book_id);
        if (books.isEmpty()) {
            book.set("ISBN", isbn)
                .set("title", title)
                .set("description", description)
                .set("pub_year", pubYear)
                .set("copies", copies);
            book.saveIt();
            return book;
        } else {
            throw new BookAlreadyExistsException();
        }
        
        //TO-DO - check if copies can be reduced based on number of copies given out to rent
    }
    
    public void removeBook(int book_id) throws BookDoesntExistException {
        
        Book book = this.fetchBook(book_id);
        book.delete();
    }
    
    public Book fetchBook(int book_id) throws BookDoesntExistException {
        Book book = Book.findById(book_id);
        if (book == null) {
            throw new BookDoesntExistException();
        }else{
            return book;
        }
    }
    
    private boolean CheckBook(int isbn) {
        Book book = new Book();
        List<Book> books = book.where("ISBN = ?", isbn);
        if (books.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
