package engine.result;

public class Result {
    private final boolean success;
    private final String feedback;

    private Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public static Result correctResult = new Result(true,
            "Congratulations, you're right!");

    public static Result incorrectResult = new Result(false,
            "Wrong answer! Please, try again.");

    public boolean getSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
