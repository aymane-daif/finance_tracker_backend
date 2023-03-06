package est.essaouira.finance_tracker.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    private static final String MESSAGE =
            "No expense with the specified id is available";
    public ExpenseNotFoundException() {
        super(MESSAGE);
    }
}