package emsi.java.halalstocks.services;
import emsi.java.halalstocks.entities.Operation;
import emsi.java.halalstocks.enums.OperationType;
import emsi.java.halalstocks.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    public List<Operation> findByType(OperationType type) {
        return operationRepository.findByType(type);
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public void deleteById(Long id) {
        operationRepository.deleteById(id);
    }
}
