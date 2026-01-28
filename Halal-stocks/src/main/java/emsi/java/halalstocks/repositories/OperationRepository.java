package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Operation;
import emsi.java.halalstocks.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByType(OperationType type);
}
