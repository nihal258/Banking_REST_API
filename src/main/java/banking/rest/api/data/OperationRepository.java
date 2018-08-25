package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import banking.rest.api.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}