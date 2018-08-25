package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import banking.rest.api.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
