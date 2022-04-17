package much.better.errorHandlers.errors;

public class NoSuchAccountException extends BaseException {
    private final String id;

    public NoSuchAccountException(final String id) {
        super(404, "5678", "NoSuchUserException", String.format("Account '%s' does not exist!", id));
        this.id = id;
    }

    public String getUsername() {
        return this.id;
    }
}
