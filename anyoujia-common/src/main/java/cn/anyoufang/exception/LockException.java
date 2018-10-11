package cn.anyoufang.exception;

/**
 * @author daiping
 */
public class LockException extends  RuntimeException {

    /**
     * Creates a new LockException.
     */
    public LockException() {
        super();
    }

    /**
     * Constructs a new LockException.
     *
     * @param message the reason for the exception
     */
    public LockException(String message) {
        super(message);
    }

    /**
     * Constructs a new LockException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public LockException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new LockException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
