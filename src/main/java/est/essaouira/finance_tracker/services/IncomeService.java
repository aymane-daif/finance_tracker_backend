package est.essaouira.finance_tracker.services;

import est.essaouira.finance_tracker.dtos.IncomeDto;
import est.essaouira.finance_tracker.exceptions.IncomeNotFoundException;
import est.essaouira.finance_tracker.models.Income;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.repositories.IncomeRepository;
import est.essaouira.finance_tracker.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    public IncomeService(IncomeRepository incomeRepository, UserRepository userRepository, ModelMapper mapper) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public IncomeDto createIncome(IncomeDto incomeDto) {
        Income income = getIncome(incomeDto);
        income.setUser(userRepository.findById(incomeDto.getUserId()).orElseThrow());
        Income createdIncome = incomeRepository.save(income);
        return getIncomeDto(createdIncome);
    }

    private IncomeDto getIncomeDto(Income createdIncome) {
        return mapper.map(createdIncome, IncomeDto.class);
    }

    private Income getIncome(IncomeDto incomeDto) {
        return mapper.map(incomeDto, Income.class);
    }

    public List<IncomeDto> getIncomesByUser(User user) {
        return incomeRepository.findByUserOrderByDateDesc(user)
                .stream()
                .map(this::getIncomeDto)
                .collect(Collectors.toList());
    }

    public IncomeDto updateIncome(Long id, IncomeDto incomeDto) {
        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new IncomeNotFoundException());

        existingIncome.setAmount(incomeDto.getAmount());
        existingIncome.setDate(incomeDto.getDate());
        existingIncome.setSource(incomeDto.getSource());
        existingIncome.setUser(userRepository.findById(incomeDto.getUserId()).orElseThrow());

        return getIncomeDto(incomeRepository.save(existingIncome));
    }

    public void deleteIncomeById(long id) {
        incomeRepository.findById(id).orElseThrow(IncomeNotFoundException::new);
        incomeRepository.deleteById(id);
    }
}
