package tieto.as.pc.crmdemo.util;


/**
 * The type Crm exception.
 */
@SuppressWarnings("unused")
public class CrmException extends RuntimeException {

    private static final long serialVersionUID = -1931005380014655090L;

    private final CrmErrorCode errorCode;


    /**
     * Instantiates a new Simple Server exception.
     *
     * @param errorCode the error code
     */
    public CrmException(CrmErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }


    /**
     * Instantiates a new Simple Server exception.
     *
     * @param message   the message
     * @param cause     the cause
     * @param errorCode the error code
     */
    public CrmException(String message, Throwable cause, CrmErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    /**
     * Instantiates a new Simple Server exception.
     *
     * @param message   the message
     * @param errorCode the error code
     */
    public CrmException(String message, CrmErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    /**
     * Instantiates a new Simple Server exception.
     *
     * @param cause     the cause
     * @param errorCode the error code
     */
    public CrmException(Throwable cause, CrmErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }


    /**
     * Gets code.
     *
     * @return the code
     */
    public CrmErrorCode getCode() {
        return this.errorCode;
    }
}
