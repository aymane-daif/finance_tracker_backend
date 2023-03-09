package est.essaouira.finance_tracker.controllers;

import est.essaouira.finance_tracker.dtos.IncomeDto;
import est.essaouira.finance_tracker.models.Income;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.services.IncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<IncomeDto> createIncome(@RequestBody IncomeDto income) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incomeService.createIncome(income));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<IncomeDto>> getIncomesByUser(@PathVariable Long userId) {
        User user = new User(userId, null, null, null);
        List<IncomeDto> incomes = incomeService.getIncomesByUser(user);
        return ResponseEntity.ok(incomes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDto> updateIncome(@PathVariable Long id, @RequestBody IncomeDto income) {
        IncomeDto updatedIncome = incomeService.updateIncome(id, income);
        return ResponseEntity.ok(updatedIncome);
    }

    @DeleteMapping ("/{id}")
    ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncomeById(id);
        return ResponseEntity.noContent().build();
    }

}
