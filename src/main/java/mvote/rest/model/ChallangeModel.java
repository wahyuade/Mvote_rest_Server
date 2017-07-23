package mvote.rest.model;

/**
 * Created by wahyuade on 16/07/17.
 */
public class ChallangeModel {
    private boolean success;
    private String message;
    private Long c;

    public ChallangeModel(boolean success, String message, Long c) {
        this.success = success;
        this.message = message;
        this.c = c;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getC() {
        return c;
    }
}
