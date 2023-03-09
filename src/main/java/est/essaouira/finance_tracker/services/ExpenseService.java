package est.essaouira.finance_tracker.services;


import est.essaouira.finance_tracker.dtos.ExpenseDto;
import est.essaouira.finance_tracker.exceptions.ExpenseNotFoundException;
import est.essaouira.finance_tracker.models.Expense;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.repositories.ExpenseRepository;
import est.essaouira.finance_tracker.repositories.PaymentMethodRepository;
import est.essaouira.finance_tracker.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private static ModelMapper mapper = new ModelMapper();


    public ExpenseService(ExpenseRepository expenseRepository, PaymentMethodRepository paymentMethodRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }

    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = getExpenseFromDto(expenseDto);
        expense.setPaymentMethod(paymentMethodRepository.findByName(expenseDto.getPaymentMethodName()));
        expense.setUser(userRepository.findById(expenseDto.getUserId()).orElseThrow());
        Expense createdExpense = expenseRepository.save(expense);
        return getExpenseDto(createdExpense);
    }

    private static Expense getExpenseFromDto(ExpenseDto expenseDto) {
        return mapper.map(expenseDto, Expense.class);
    }

    private static ExpenseDto getExpenseDto(Expense expense) {
        return mapper.map(expense, ExpenseDto.class);
    }

    public List<ExpenseDto> getExpensesByUser(User user) {
        return expenseRepository.findByUserOrderByDateDesc(user)
                .stream()
                .map(ExpenseService::getExpenseDto)
                .collect(Collectors.toList());
    }

    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(ExpenseNotFoundException::new);

        Expense expense = getExpenseFromDto(expenseDto);
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDate(expense.getDate());
        existingExpense.setPaymentMethod(paymentMethodRepository.findByName(expenseDto.getPaymentMethodName()));
        existingExpense.setUser(userRepository.findById(expenseDto.getUserId()).orElseThrow());
        return getExpenseDto(expenseRepository.save(existingExpense));
    }

    public void deleteExpenseById(long id) {
        expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException());
        expenseRepository.deleteById(id);
    }
}
