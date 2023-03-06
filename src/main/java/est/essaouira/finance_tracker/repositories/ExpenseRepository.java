package est.essaouira.finance_tracker.repositories;

import est.essaouira.finance_tracker.models.Expense;
import est.essaouira.finance_tracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserOrderByDateDesc(User user);
}
