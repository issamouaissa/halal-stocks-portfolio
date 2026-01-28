package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.Performance;
import emsi.java.halalstocks.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping
    public List<Performance> getAllPerformances() {
        return performanceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Performance> getPerformanceById(@PathVariable Long id) {
        return performanceService.findById(id);
    }

    @PostMapping
    public Performance createPerformance(@RequestBody Performance performance) {
        return performanceService.save(performance);
    }

    @PutMapping("/{id}")
    public Performance updatePerformance(@PathVariable Long id, @RequestBody Performance performanceDetails) {
        Performance performance = performanceService.findById(id).orElseThrow(() -> new RuntimeException("Performance not found"));
        performance.setValeur(performanceDetails.getValeur());
        performance.setDateCalcul(performanceDetails.getDateCalcul());
        return performanceService.save(performance);
    }

    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable Long id) {
        performanceService.deleteById(id);
}
}