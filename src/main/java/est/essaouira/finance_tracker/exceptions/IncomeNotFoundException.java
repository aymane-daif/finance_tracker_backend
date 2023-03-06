package est.essaouira.finance_tracker.exceptions;

public class IncomeNotFoundException extends RuntimeException {
    private static final String MESSAGE =
            "No income with the specified id is available";

    public IncomeNotFoundException() {
        super(MESSAGE);
    }
}