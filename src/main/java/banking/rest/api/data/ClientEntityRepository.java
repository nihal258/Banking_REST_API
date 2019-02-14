package banking.rest.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.rest.api.data.entity.ClientEntity;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {

}