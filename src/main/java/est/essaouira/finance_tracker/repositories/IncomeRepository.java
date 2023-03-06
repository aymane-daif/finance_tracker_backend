package est.essaouira.finance_tracker.repositories;

import est.essaouira.finance_tracker.models.Income;
import est.essaouira.finance_tracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserOrderByDateDesc(User user);
}