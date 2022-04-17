package much.better.errorHandlers.errors;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFieldException extends RuntimeException {
    private int status;
    private String errorCode;
    private String errorMessage;
    private String errorDetail;
    private String errorDetailUrl;
    private List<FieldExceptionDetail> fieldErrors;

    public BaseFieldException(final int status, final String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public BaseFieldException(final int status, final String errorMessage, final List<FieldExceptionDetail> fieldErrors) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.fieldErrors = fieldErrors;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage, final List<FieldExceptionDetail> fieldErrors) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.fieldErrors = fieldErrors;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage, final String errorDetail) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage, final String errorDetail, final List<FieldExceptionDetail> fieldErrors) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.fieldErrors = fieldErrors;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage, final String errorDetail, final String errorDetailUrl) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.errorDetailUrl = errorDetailUrl;
    }

    public BaseFieldException(final int status, final String errorCode, final String errorMessage, final String errorDetail, final String errorDetailUrl, final List<FieldExceptionDetail> fieldErrors) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.errorDetailUrl = errorDetailUrl;
        this.fieldErrors = fieldErrors;
    }

    public synchronized void addField(final String field, final String fieldErrorMessage) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList<>();
        }

        this.fieldErrors.add(new FieldExceptionDetail(field, fieldErrorMessage));
    }

    public synchronized void addField(final String field, final String fieldErrorCode, final String fieldErrorMessage) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList<>();
        }

        final FieldExceptionDetail fieldExceptionDetail = new FieldExceptionDetail(field, fieldErrorMessage);
        fieldExceptionDetail.setErrorCode(fieldErrorCode);

        this.fieldErrors.add(fieldExceptionDetail);
    }

    public synchronized void addField(final String field, final String fieldErrorCode, final String fieldErrorMessage, final String fieldErrorDetail) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList<>();
        }

        final FieldExceptionDetail fieldExceptionDetail = new FieldExceptionDetail(field, fieldErrorMessage);
        fieldExceptionDetail.setErrorCode(fieldErrorCode);
        fieldExceptionDetail.setErrorDetail(fieldErrorDetail);

        this.fieldErrors.add(fieldExceptionDetail);
    }

    public synchronized void addField(final String field, final String fieldErrorCode, final String fieldErrorMessage, final String fieldErrorDetail, final String fieldErrorDetailUrl) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList<>();
        }

        final FieldExceptionDetail fieldExceptionDetail = new FieldExceptionDetail(field, fieldErrorMessage);
        fieldExceptionDetail.setErrorCode(fieldErrorCode);
        fieldExceptionDetail.setErrorDetail(fieldErrorDetail);
        fieldExceptionDetail.setErrorDetailUrl(fieldErrorDetailUrl);

        this.fieldErrors.add(fieldExceptionDetail);
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

    public List<FieldExceptionDetail> getFieldErrors() {
        return this.fieldErrors;
    }

    public void setFieldErrors(final List<FieldExceptionDetail> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public static class FieldExceptionDetail {
        private String field;
        private String errorCode;
        private String errorMessage;
        private String errorDetail;
        private String errorDetailUrl;

        public FieldExceptionDetail(final String field, final String errorMessage) {
            this.field = field;
            this.errorMessage = errorMessage;
        }

        public String getField() {
            return this.field;
        }

        public void setField(final String field) {
            this.field = field;
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
}
