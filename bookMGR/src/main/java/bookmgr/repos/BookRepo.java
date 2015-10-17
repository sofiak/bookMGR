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
import bookmgr.models.BookAuthor;
import java.util.List;

/**
 * Repo for book- and author-related methods
 */
public class BookRepo {

    public BookRepo() {
    }

    /**
     * Method creates a Book object with user input
     *
     * @param isbn ISBN of the book
     * @param title title of the book
     * @param description description of the book
     * @param author name of author in string form
     * @param pubYear publishing year of the book
     * @param copies number of copies of the book
     *
     * @return Book object
     * @throws bookmgr.exceptions.UnacceptableISBNException
     * @throws bookmgr.exceptions.AuthorDoesntExistException
     * @throws bookmgr.exceptions.BookDoesntExistException
     * @throws bookmgr.exceptions.AuthorAndBookAreAlreadyConnectedException
     * @throws BookAlreadyExistsException if a book by the same ISBN already
     * exists
     */
    public Book createBook(String isbn, String title, String description,
            String author, int pubYear, int copies) throws BookAlreadyExistsException,
            UnacceptableISBNException, AuthorDoesntExistException, BookDoesntExistException,
            AuthorAndBookAreAlreadyConnectedException {
        if (isbn.length() == 13) {
            if (this.CheckBook(isbn) == false) {
                Author authorT = this.GetAuthor(author);

                Book book = new Book();
                book.set("ISBN", isbn);
                book.set("title", title);
                book.set("description", description);
                book.set("pub_year", pubYear);
                book.set("copies", copies);
                book.saveIt();

                this.addAuthorToBook(book.getInteger("id"), authorT.getInteger("id"));
                return book;
            } else {
                throw new BookAlreadyExistsException();
            }
        } else {
            throw new UnacceptableISBNException();
        }
    }

    /**
     * Method edits a Book object with user input
     *
     * @param isbn ISBN of the book
     * @param title title of the book
     * @param description description of the book
     * @param pubYear publishing year of the book
     * @param copies number of copies of the book
     *
     * @throws BookAlreadyExistsException if a book by the same ISBN already
     * exists
     * @throws BookDoesntExistException if the book doesn't exist
     *
     * @return Book object
     */
    public Book editBook(String isbn, String title, String description, String author, int pubYear,
            int copies) throws BookDoesntExistException, BookAlreadyExistsException, CantRemoveBooksNotOnTheShelfException, AuthorDoesntExistException, AuthorAndBookAreAlreadyConnectedException, AuthorAndBookAreNotConnectedException {
        Book book = this.GetBook(isbn);
        RentRepo newRepo = new RentRepo();
        if (copies < (book.getInteger("copies") - newRepo.availableCopies(book.getInteger("id")))) {
            throw new CantRemoveBooksNotOnTheShelfException();
        } else if (author.equalsIgnoreCase(book.getString("author"))) {
            book = this.bookSetter(book, title, description, pubYear, copies);
            return book;
        } else {
            Author oldAuthor = this.GetAuthor(book.getString("author"));
            this.removeAuthorFromBook(book.getInteger("id"), oldAuthor.getInteger("id"));
            book = this.bookSetter(book, title, description, pubYear, copies);
            this.addAuthorToBook(book.getInteger("id"), this.GetAuthor(author).getInteger("id"));
            return book;
        }
    }

    private Book bookSetter(Book aBook, String title, String description, int pubYear, int copies) {
        Book book = aBook;
        book.set("title", title);
        book.set("description", description);
        book.set("pub_year", pubYear);
        book.set("copies", copies);
        book.saveIt();

        return book;
    }

    /**
     * Method removes a Book object if all copies are present
     *
     * @param ISBN ISBN of the book
     *
     * @throws BookDoesntExistException if book doesn't exist
     */
    public void removeBook(String ISBN) throws BookDoesntExistException {
        Book book = this.GetBook(ISBN);
        int book_id = book.getInteger("id");
        RentRepo rentrepo = new RentRepo();
        if (rentrepo.availableCopies(book_id) == book.getInteger("copies")) {
            List<BookAuthor> bookauthors = BookAuthor.where("book_id = ?", book_id);
            for (int i = 0; i <= bookauthors.size(); i++) {
                BookAuthor bookauthor = bookauthors.get(i);
                bookauthor.delete();
            }
            book.delete();
        }
    }

    /**
     * Method fetches a Book object by ID
     *
     * @param book_id ID of the book
     *
     * @throws BookDoesntExistException if book doesn't exist
     *
     * @return Book object
     */
    public Book fetchBook(int book_id) throws BookDoesntExistException {
        Book book = Book.findById(book_id);
        if (book == null) {
            throw new BookDoesntExistException();
        } else {
            return book;
        }
    }

    /**
     * Method searches for a book with specific ISBN
     *
     * @param isbn ISBN to look for
     *
     * @return true if book exists, false if not
     */
    private boolean CheckBook(String isbn) {
        List<Book> books = Book.where("ISBN = ?", isbn);
        if (books.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Book GetBook(String isbn) throws BookDoesntExistException {
        List<Book> books = Book.where("ISBN = ?", isbn);
        if (books.isEmpty()) {
            throw new BookDoesntExistException();
        } else {
            return books.get(0);
        }
    }

    /**
     * Method creates Author object with passed parameter
     *
     * @param name name of the author
     *
     * @return Author object
     */
    public Author createAuthor(String name) throws AuthorAlreadyExistsException {
        if (this.CheckAuthor(name) == false) {
            Author author = new Author();
            author.set("name", name);
            author.saveIt();
            return author;
        } else {
            throw new AuthorAlreadyExistsException();
        }
    }

    /**
     * Method checks if an Author Object exists by a specified name
     *
     * @param name name to look for
     *
     * @return returns true if author exists, false if not
     */
    private boolean CheckAuthor(String name) {
        List<Author> authors = Author.where("name = ?", name);
        if (authors.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Author GetAuthor(String name) throws AuthorDoesntExistException {
        List<Author> authors = Author.where("name = ?", name);
        if (authors.isEmpty()) {
            throw new AuthorDoesntExistException();
        } else {
            return authors.get(0);
        }
    }

    /**
     * Method fetches an Author object by ID
     *
     * @param author_id ID for author
     *
     * @throws AuthorDoesntExistException if author doesn't exist
     *
     * @return Author object
     */
    public Author fetchAuthor(int author_id) throws AuthorDoesntExistException {
        Author author = Author.findById(author_id);
        System.out.println(author);
        if (author == null) {
            throw new AuthorDoesntExistException();
        } else {
            return author;
        }
    }

    /**
     * Method removes an Author object
     *
     * @param author_id ID for author
     *
     * @throws AuthorDoesntExistException if author doesn't exist
     */
    public void removeAuthor(int author_id) throws AuthorDoesntExistException {
        List<BookAuthor> bookauthor = BookAuthor.where("author_id = ?", author_id);
        if (bookauthor.isEmpty()) {
            Author author = this.fetchAuthor(author_id);
            author.delete();
        }
    }

    /**
     * Method combines an Author object and a Book object in a BookAuthor object
     *
     * @param book_id ID of the book
     * @param author_id ID of the author
     *
     * @throws BookDoesntExistException if the book doesn't exist
     * @throws AuthorDoesntExistException if the author doesn't exist
     * @throws AuthorAndBookAreAlreadyConnectedException if the objects are
     * already connected
     */
    public void addAuthorToBook(int book_id, int author_id) throws BookDoesntExistException,
            AuthorDoesntExistException, AuthorAndBookAreAlreadyConnectedException {
        Author author = this.fetchAuthor(author_id);
        Book book = this.fetchBook(book_id);
        boolean exists = this.checkBookAuthor(book_id, author_id);
        if (exists == false) {
            BookAuthor bookauthor = new BookAuthor();
            bookauthor.set("book_id", book_id);
            bookauthor.set("author_id", author_id);
            bookauthor.saveIt();
        } else {
            throw new AuthorAndBookAreAlreadyConnectedException();
        }
    }

    /**
     * Method break the connection between an author and a book
     *
     * @param book_id ID of the book
     * @param author_id ID of the author
     *
     * @throws BookDoesntExistException if the book doesn't exist
     * @throws AuthorDoesntExistException if the author doesn't exist
     * @throws AuthorAndBookAreNotConnectedException if the objects are not
     * connected
     */
    public void removeAuthorFromBook(int book_id, int author_id) throws AuthorDoesntExistException, BookDoesntExistException, AuthorAndBookAreNotConnectedException {
        boolean exists = this.checkBookAuthor(book_id, author_id);
        if (exists == true) {
            BookAuthor bookauthor = this.fetchBookAuthor(book_id, author_id).get(0);
            bookauthor.delete();
        } else {
            throw new AuthorAndBookAreNotConnectedException();
        }
    }

    /**
     * Method fetches a BookAuthor object
     *
     * @param book_id ID of the book
     * @param author_id ID of the author
     *
     * @return BookAuthor object
     */
    public List<BookAuthor> fetchBookAuthor(int book_id, int author_id) {
        List<BookAuthor> bookauthor = BookAuthor.where("author_id = ? AND book_id = ?", author_id, book_id);
        return bookauthor;
    }

    /**
     * Method checks if a connection between a book and author objects exists
     *
     * @param book_id ID of the book
     * @param author_id ID of the author
     *
     * @return true if BookAuthor exists, false if not
     */
    public boolean checkBookAuthor(int book_id, int author_id) {
        List<BookAuthor> bookauthor = BookAuthor.where("author_id = ? AND book_id = ?", author_id, book_id);
        if (bookauthor.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
