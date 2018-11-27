package by.bsuir.recipeforum.exception;

/**
 * Own exception class.
 */
public class ApplicationException extends Exception {

    /**
     * Public default constructor.
     */
    public ApplicationException() {

        super();

    }

    /**
     * Public initialize constructor.
     *
     * @param message value of the exception message
     */
    public ApplicationException(final String message) {

        super(message);

    }

    /**
     * Public initialize constructor.
     *
     * @param throwable value of the object Throwable
     */
    public ApplicationException(final Throwable throwable) {

        super(throwable);

    }

    /**
     * Public initialize constructor.
     *
     * @param message   value of the exception message
     * @param throwable value of the object Throwable
     */
    public ApplicationException(final String message,
                                final Throwable throwable) {

        super(message, throwable);

    }

}
