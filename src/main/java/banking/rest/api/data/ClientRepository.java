package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import banking.rest.api.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}