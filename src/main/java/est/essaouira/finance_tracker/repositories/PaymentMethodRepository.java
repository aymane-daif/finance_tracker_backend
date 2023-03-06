package est.essaouira.finance_tracker.repositories;

import est.essaouira.finance_tracker.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {}
