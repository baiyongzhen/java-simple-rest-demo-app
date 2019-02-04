package tieto.as.pc.crmdemo.util;

/**
 * CRM error codes.
 */
@SuppressWarnings("unused")
public enum CrmErrorCode {

    // CRM application errors 100-
    EMAIL_ALREADY_EXISTS(100),

    // Server errors 500-
    SERVER_ERROR(500);

    private final int errorCode;

    CrmErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
