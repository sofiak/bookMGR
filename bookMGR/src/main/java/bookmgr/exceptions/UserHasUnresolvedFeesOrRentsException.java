package bookmgr.exceptions;

/**
 * Exception for admin is trying to remove a user that still has unreturned books
 * or pending fees
 */
public class UserHasUnresolvedFeesOrRentsException extends Exception {

}
