package est.essaouira.finance_tracker.services;

import est.essaouira.finance_tracker.exceptions.IncomeNotFoundException;
import est.essaouira.finance_tracker.models.Income;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getIncomesByUser(User user) {
        return incomeRepository.findByUserOrderByDateDesc(user);
    }

    public Income updateIncome(Long id, Income income) {
        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new IncomeNotFoundException());

        existingIncome.setAmount(income.getAmount());
        existingIncome.setDate(income.getDate());
        existingIncome.setSource(income.getSource());

        return incomeRepository.save(existingIncome);
    }

    public void deleteIncomeById(long id) {
        incomeRepository.findById(id).orElseThrow(IncomeNotFoundException::new);
        incomeRepository.deleteById(id);
    }
}
