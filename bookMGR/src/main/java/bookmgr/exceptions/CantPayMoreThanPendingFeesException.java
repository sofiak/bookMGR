package bookmgr.exceptions;

/**
 * Exception for when admin is receiving payment from a user and trying to enter a
 * sum larger than the user has in pending fees
 */
public class CantPayMoreThanPendingFeesException extends Exception {

}
