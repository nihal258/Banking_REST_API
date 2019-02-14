package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.rest.api.data.entity.OperationEntity;

public interface OperationEntityRepository extends JpaRepository<OperationEntity, Long> {

}