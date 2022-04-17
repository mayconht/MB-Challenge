package much.better.errorHandlers.errors;

public abstract class BaseException extends RuntimeException {
    private int status = 500;
    private String errorCode;
    private String errorMessage = "An error occurred. Please contact support.";
    private String errorDetail;
    private String errorDetailUrl;

    public BaseException() {
        // Noop
    }

    public BaseException(final int status, final String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public BaseException(final int status, final String errorCode, final String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(final int status, final String errorCode, final String errorMessage, final String errorDetail) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public BaseException(final int status, final String errorCode, final String errorMessage, final String errorDetail, final String errorDetailUrl) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.errorDetailUrl = errorDetailUrl;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetail() {
        return this.errorDetail;
    }

    public void setErrorDetail(final String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorDetailUrl() {
        return this.errorDetailUrl;
    }

    public void setErrorDetailUrl(final String errorDetailUrl) {
        this.errorDetailUrl = errorDetailUrl;
    }
}
