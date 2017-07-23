package mvote.rest.model;

/**
 * Created by wahyuade on 16/07/17.
 */
public class LoginModel {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


    public LoginModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
