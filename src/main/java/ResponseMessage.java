/**
 * Created by Harjinder Singh on 4/30/2016.
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseMessage(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
