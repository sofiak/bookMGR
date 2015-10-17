package bookmgr.exceptions;

/**
 * Exception for when admin is trying to remove books from the DB that are not 
 * "on the shelf" currently
 */
public class CantRemoveBooksNotOnTheShelfException extends Exception {

}
