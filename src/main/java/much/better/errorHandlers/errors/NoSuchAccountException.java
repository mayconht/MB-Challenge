package much.better.errorHandlers.errors;

public class NoSuchAccountException extends BaseException {
    public NoSuchAccountException() {
        super(404, "5678", "No Such Account Exception", String.format("Account does not exist or Token was not provided!"));
    }
}
