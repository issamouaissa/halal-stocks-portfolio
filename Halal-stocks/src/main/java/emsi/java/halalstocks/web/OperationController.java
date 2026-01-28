package emsi.java.halalstocks.web;
import emsi.java.halalstocks.entities.Operation;
import emsi.java.halalstocks.enums.OperationType;
import emsi.java.halalstocks.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Operation> getOperationById(@PathVariable Long id) {
        return operationService.findById(id);
    }

    @GetMapping("/type/{type}")
    public List<Operation> getOperationsByType(@PathVariable OperationType type) {
        return operationService.findByType(type);
    }

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation) {
        return operationService.save(operation);
    }

    @PutMapping("/{id}")
    public Operation updateOperation(@PathVariable Long id, @RequestBody Operation operationDetails) {
        Operation operation = operationService.findById(id)
                .orElseThrow(() -> new RuntimeException("Operation not found"));
        operation.setDate(operationDetails.getDate());
        operation.setMontant(operationDetails.getMontant());
        operation.setType(operationDetails.getType());
        return operationService.save(operation);
    }

    @DeleteMapping("/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteById(id);
    }
}
