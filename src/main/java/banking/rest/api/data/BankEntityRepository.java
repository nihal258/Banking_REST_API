package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.rest.api.data.entity.BankEntity;

public interface BankEntityRepository extends JpaRepository<BankEntity, Long> {

}
