package est.essaouira.finance_tracker.services;


import est.essaouira.finance_tracker.exceptions.ExpenseNotFoundException;
import est.essaouira.finance_tracker.models.Expense;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findByUserOrderByDateDesc(user);
    }

    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(ExpenseNotFoundException::new);

        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDate(expense.getDate());
        existingExpense.setPaymentMethod(expense.getPaymentMethod());
        existingExpense.setUser(expense.getUser());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpenseById(long id) {
        expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException());
        expenseRepository.deleteById(id);
    }
}
