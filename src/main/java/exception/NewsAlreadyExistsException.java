package exception;

public class NewsAlreadyExistsException extends Exception {

    private String message;

    public String getMessage() {
        return message;
    }

    public NewsAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "NewsAlreadyExistsException [message=" + message + "]";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
