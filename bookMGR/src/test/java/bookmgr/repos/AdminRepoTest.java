package bookmgr.repos;
 
import bookmgr.exceptions.AuthorDoesntExistException;
import bookmgr.exceptions.AuthorHasNoBooksException;
import bookmgr.exceptions.BookDoesntExistException;
import bookmgr.exceptions.UserAlreadyExistsException;
import bookmgr.exceptions.UserDoesntExistException;
import bookmgr.exceptions.UserHasUnresolvedFeesOrRentsException;
import bookmgr.models.Author;
import bookmgr.models.Book;
import bookmgr.models.BookAuthor;
import bookmgr.models.Rent;
import bookmgr.models.User;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.Assert;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class AdminRepoTest {
 
    public AdminRepoTest() {
    }
 
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
    public void CheckIfAdminWorksIfUserIsntAdmin() throws UserAlreadyExistsException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int userId = user.getInteger("id");
 
        AdminRepo adRepo = new AdminRepo();
        boolean success = adRepo.checkIfAdmin(userId);
        Assert.assertFalse(success);
    }
 
    @Test
    public void CheckIfAdminWorksIfUserIsAdmin() throws UserAlreadyExistsException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
        user.set("isAdmin", 1);
        user.saveIt();
 
        int userId = user.getInteger("id");
 
        AdminRepo adRepo = new AdminRepo();
        boolean success = adRepo.checkIfAdmin(userId);
        Assert.assertTrue(success);
    }
   
   
    @Test(expected = UserHasUnresolvedFeesOrRentsException.class)
    public void removeUserShouldThrowExceptionWhenUnresolvedFees() throws UserAlreadyExistsException, UserDoesntExistException, UserHasUnresolvedFeesOrRentsException, BookDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
        user.set("fees", 34.50);
        user.saveIt();
 
        int userId = user.getInteger("id");
 
        AdminRepo adRepo = new AdminRepo();
        adRepo.removeUser(userId);
//        user = User.findById(userId);
//        Assert.assertEquals(null, user);
    }
   
    @Test
    public void removeUserShouldRemoveUserWhenNoFees() throws UserAlreadyExistsException, UserDoesntExistException, UserHasUnresolvedFeesOrRentsException, BookDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int userId = user.getInteger("id");
 
        AdminRepo adRepo = new AdminRepo();
        adRepo.removeUser(userId);
        user = User.findById(userId);
        Assert.assertEquals(null, user);
    }
   
    @Test
    public void reportForRentsShouldReturnNoRentsWhenNoBookIsRented() throws UserAlreadyExistsException, BookDoesntExistException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int userId = user.getInteger("id");
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportForRents(userId, 2);
        Assert.assertEquals(0, newArray.size() );
       
    }
   
    @Test
    public void reportForRentsShouldReturnRentedBooks() throws UserAlreadyExistsException, BookDoesntExistException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int user_id = user.getInteger("id");
       
        Book book = new Book();
        book.set("title", "Test book");
        book.set("description", "Test Description");
        book.set("pub_year", 1999);
        book.set("ISBN", "1234565432189");
        book.set("copies", 50);
        book.saveIt();
       
        Rent rent = new Rent();
            rent.set("user_id", user_id);
            rent.set("book_id", book.getInteger("id"));
            rent.set("due_date", "2015-11-17");
            rent.saveIt();
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportForRents(user_id, 0);
        Assert.assertEquals(1, newArray.size());
        newArray = adRepo.reportForRents(user_id, 1);
        Assert.assertEquals(0, newArray.size());
        newArray = adRepo.reportForRents(user_id, 2);
        Assert.assertEquals(1, newArray.size());
       
    }
   
    @Test
    public void reportForRentsShouldReturnReturnedBooks() throws UserAlreadyExistsException, BookDoesntExistException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int user_id = user.getInteger("id");
       
        Book book = new Book();
        book.set("title", "Test book");
        book.set("description", "Test Description");
        book.set("pub_year", 1999);
        book.set("ISBN", "1234565432189");
        book.set("copies", 50);
        book.saveIt();
       
        Rent rent = new Rent();
            rent.set("user_id", user_id);
            rent.set("book_id", book.getInteger("id"));
            rent.set("hasReturned", 1);
            rent.set("due_date", "2015-11-17");
            rent.saveIt();
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportForRents(user_id, 2);
        Assert.assertEquals(1, newArray.size());
        newArray = adRepo.reportForRents(user_id, 0);
        Assert.assertEquals(0, newArray.size());
        newArray = adRepo.reportForRents(user_id, 1);
        Assert.assertEquals(1, newArray.size());
       
    }
   
    @Test
    public void reportForAllRentsWorks() throws UserAlreadyExistsException, BookDoesntExistException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
 
        int user_id = user.getInteger("id");
       
        Book book = new Book();
        book.set("title", "Test book");
        book.set("description", "Test Description");
        book.set("pub_year", 1999);
        book.set("ISBN", "1234565432189");
        book.set("copies", 50);
        book.saveIt();
       
        Rent rent = new Rent();
            rent.set("user_id", user_id);
            rent.set("book_id", book.getInteger("id"));
            rent.set("hasReturned", 1);
            rent.set("due_date", "2015-11-17");
            rent.saveIt();
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportForAllRents(1);
        Assert.assertEquals(1, newArray.size());
        newArray = adRepo.reportForRents(user_id, 2);
        Assert.assertEquals(1, newArray.size());
        newArray = adRepo.reportForRents(user_id, 0);
        Assert.assertEquals(0, newArray.size());
       
    }
   
    @Test
    public void reportBooksByAuthorWorks() throws AuthorDoesntExistException, BookDoesntExistException, AuthorHasNoBooksException {
   
        Book book = new Book();
        book.set("title", "Test book");
        book.set("description", "Test Description");
        book.set("pub_year", 1999);
        book.set("ISBN", "1234565432189");
        book.set("copies", 50);
        book.saveIt();
       
        Author author = new Author();
        author.set("name", "Test Author");
        author.saveIt();
       
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.set("book_id", book.getInteger("id"));
        bookAuthor.set("author_id", author.getInteger("id"));
        bookAuthor.saveIt();
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportBooksByAuthor(author.getString("name"));
        Assert.assertEquals(1, newArray.size());
       
    }
   
    @Test(expected = AuthorHasNoBooksException.class)
    public void reportBooksByAuthorShouldThrowExceptionIfAuthorHasNoBooks() throws AuthorDoesntExistException, BookDoesntExistException, AuthorHasNoBooksException {
       
        Author author = new Author();
        author.set("name", "Test Author");
        author.saveIt();
 
        AdminRepo adRepo = new AdminRepo();
        ArrayList<String> newArray = adRepo.reportBooksByAuthor(author.getString("name"));  
    }
   
    @Test
    public void changeAdminPasswordWorks() throws UserAlreadyExistsException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
        user.set("isAdmin", 1);
        user.saveIt();
 
        int user_id = user.getInteger("id");
       
        AdminRepo adRepo = new AdminRepo();
        Boolean success = adRepo.changeAdminPassword(user_id, "testpassword");
        Assert.assertTrue(success);
       
    }
   
    @Test
    public void changeAPasswordWorks() throws UserAlreadyExistsException, UserDoesntExistException {
        UserRepo newrepo = new UserRepo();
        User user = newrepo.createUser("username", "password");
        user.saveIt();
 
        String username = user.getString("username");
       
        AdminRepo adRepo = new AdminRepo();
        Boolean success = adRepo.changeAPassword(username, "testpassword");
        Assert.assertTrue(success);
       
    }
}