package much.better.errorHandlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import much.better.errorHandlers.errors.BaseException;
import much.better.errorHandlers.errors.BaseFieldException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.error.internal.ErrorHandler;
import ratpack.handling.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class GlobalErrorHandler implements ErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorHandler.class);

    private final ObjectMapper mapper;

    public GlobalErrorHandler() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void error(final Context context, final int statusCode) throws Exception {
        context.getResponse().status(statusCode).send();
    }

    @Override
    public void error(final Context context, final Throwable throwable) throws Exception {
        if (throwable instanceof BaseException) {
            final ErrorResponse error = new ErrorResponse(((BaseException) throwable).getStatus(), ((BaseException) throwable).getErrorMessage());
            error.setErrorCode(((BaseException) throwable).getErrorCode());
            error.setErrorDetail(((BaseException) throwable).getErrorDetail());
            error.setErrorDetailUrl(((BaseException) throwable).getErrorDetailUrl());

            // Expose sensitive information if running in development mode
            if (context.getServerConfig().isDevelopment()) {
                error.setStacktrace(Throwables.getStackTraceAsString(throwable));
            }

            context.getResponse().status(((BaseException) throwable).getStatus());
            context.getResponse().send(this.mapper.writeValueAsString(error));
        } else if (throwable instanceof BaseFieldException) {
            final FieldErrorResponse error = new FieldErrorResponse(((BaseFieldException) throwable).getStatus(), ((BaseFieldException) throwable).getErrorMessage());
            error.setErrorCode(((BaseFieldException) throwable).getErrorCode());
            error.setErrorDetail(((BaseFieldException) throwable).getErrorDetail());
            error.setErrorDetailUrl(((BaseFieldException) throwable).getErrorDetailUrl());

            final List<FieldError> fieldErrors = new ArrayList<>();

            ((BaseFieldException) throwable).getFieldErrors().forEach(new Consumer<BaseFieldException.FieldExceptionDetail>() {
                @Override
                public void accept(final BaseFieldException.FieldExceptionDetail fieldExceptionDetail) {
                    final FieldError fieldError = new FieldError(fieldExceptionDetail.getField());
                    fieldError.setErrorCode(fieldExceptionDetail.getErrorCode());
                    fieldError.setErrorMessage(fieldExceptionDetail.getErrorMessage());
                    fieldError.setErrorDetail(fieldExceptionDetail.getErrorDetail());
                    fieldError.setErrorDetailUrl(fieldExceptionDetail.getErrorDetailUrl());

                    fieldErrors.add(fieldError);
                }
            });

            error.setFieldErrors(fieldErrors);

            // Expose sensitive information if running in development mode
            if (context.getServerConfig().isDevelopment()) {
                error.setStacktrace(Throwables.getStackTraceAsString(throwable));
            }

            context.getResponse().status(((BaseFieldException) throwable).getStatus());
            context.getResponse().send(this.mapper.writeValueAsString(error));
        } else {
            final ErrorResponse error = new ErrorResponse(500, "An error occurred. Please contact support.");

            // Expose sensitive information if running in development mode
            if (context.getServerConfig().isDevelopment()) {
                error.setErrorDetail(throwable.getMessage());
                error.setStacktrace(Throwables.getStackTraceAsString(throwable));
            }

            context.getResponse().status(500);
            context.getResponse().send(this.mapper.writeValueAsString(error));
        }
    }

    /**
     * Error response
     */
    @JsonPropertyOrder(value = {
            "id",
            "status",
            "statusMessage",
            "errorCode",
            "errorMessage",
            "errorDetail",
            "errorDetailUrl",
            "stacktrace"
    })
    private static class ErrorResponse implements Serializable {
        private static final long serialVersionUID = -9089646869966970667L;

        private String id;
        private int status;
        private String errorCode;
        private String errorMessage;
        private String errorDetail;
        private String errorDetailUrl;
        private String stacktrace;


        ErrorResponse(final int status, final String errorMessage) {
            this.id = UUID.randomUUID().toString();
            this.status = status;
            this.errorMessage = errorMessage;
        }

        public String getId() {
            return this.id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(final int status) {
            this.status = status;
        }

        @JsonProperty("statusMessage")
        public String getStatusMessage() {
            return HttpStatusMessage.of(this.status);
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

        public String getStacktrace() {
            return this.stacktrace;
        }

        public void setStacktrace(final String stacktrace) {
            this.stacktrace = stacktrace;
        }
    }

    /**
     * Field-level error response
     */
    @JsonPropertyOrder(value = {
            "id",
            "status",
            "statusMessage",
            "errorCode",
            "errorMessage",
            "errorDetail",
            "errorDetailUrl",
            "fieldErrors",
            "stacktrace"
    })
    private static class FieldErrorResponse implements Serializable {
        private static final long serialVersionUID = 171077544021179023L;

        private String id;
        private int status;
        private String errorCode;
        private String errorMessage;
        private String errorDetail;
        private String errorDetailUrl;
        private List<FieldError> fieldErrors;
        private String stacktrace;

        public FieldErrorResponse(final int status, final String errorMessage) {
            this.id = UUID.randomUUID().toString();
            this.status = status;
            this.errorMessage = errorMessage;
        }

        public synchronized void addFieldError(final FieldError fieldError) {
            if (this.fieldErrors == null) {
                this.fieldErrors = new ArrayList<>();
            }

            this.fieldErrors.add(fieldError);
        }

        public String getId() {
            return this.id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(final int status) {
            this.status = status;
        }

        @JsonProperty("statusMessage")
        public String getStatusMessage() {
            return HttpStatusMessage.of(this.status);
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

        public List<FieldError> getFieldErrors() {
            return this.fieldErrors;
        }

        public void setFieldErrors(final List<FieldError> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }

        public String getStacktrace() {
            return this.stacktrace;
        }

        public void setStacktrace(final String stacktrace) {
            this.stacktrace = stacktrace;
        }
    }

    /**
     * Field-level error details
     */
    private static class FieldError implements Serializable {
        private static final long serialVersionUID = 4451792396257629282L;

        private String field;
        private String errorCode;
        private String errorMessage;
        private String errorDetail;
        private String errorDetailUrl;

        public FieldError(final String field) {
            this.field = field;
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
