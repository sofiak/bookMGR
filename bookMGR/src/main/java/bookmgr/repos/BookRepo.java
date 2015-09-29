
package bookmgr.repos;

import bookmgr.exceptions.BookAlreadyExistsException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.models.Book;
import java.util.List;

/**
 * Repo for book-related functions
 */

public class BookRepo {

    public BookRepo() {
    }

    public Book addBook(String isbn, String title, String description, int pubYear, int copies) throws BookAlreadyExistsException {
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
    
    public Book editBook(int book_id, String isbn, String title, String description, int pubYear, int copies) throws BookDoesntExistException, BookAlreadyExistsException{
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
        RentRepo rentrepo = new RentRepo();
        if(rentrepo.availableCopies(book_id) == book.getInteger("copies")){
        book.delete();
        }
    }
    
    public Book fetchBook(int book_id) throws BookDoesntExistException {
        Book book = Book.findById(book_id);
        if (book == null) {
            throw new BookDoesntExistException();
        }else{
            return book;
        }
    }
    
    private boolean CheckBook(String isbn) {
        List<Book> books = Book.where("ISBN = ?", isbn);
        if (books.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
