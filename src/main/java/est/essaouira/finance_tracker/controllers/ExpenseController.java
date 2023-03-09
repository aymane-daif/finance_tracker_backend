package est.essaouira.finance_tracker.controllers;

import est.essaouira.finance_tracker.dtos.ExpenseDto;
import est.essaouira.finance_tracker.models.Expense;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByUser(@PathVariable Long userId) {
        User user = new User(userId, null, null, null);
        return ResponseEntity.ok(expenseService.getExpensesByUser(user));
    }
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expense) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expense) {
        ExpenseDto updatedExpense = expenseService.updateExpense(id, expense);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping ("/{id}")
    ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }
}