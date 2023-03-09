package est.essaouira.finance_tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {

    private Long id;

    private LocalDate date;
    private double amount;
    private String source;
    private Long userId;

}
