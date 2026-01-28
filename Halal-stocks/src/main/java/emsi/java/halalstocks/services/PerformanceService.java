package emsi.java.halalstocks.services;
import emsi.java.halalstocks.entities.Performance;
import emsi.java.halalstocks.repositories.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public List<Performance> findAll() {
        return performanceRepository.findAll();
    }

    public Optional<Performance> findById(Long id) {
        return performanceRepository.findById(id);
    }

    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    public void deleteById(Long id) {
        performanceRepository.deleteById(id);
}
}