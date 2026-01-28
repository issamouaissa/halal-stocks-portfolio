package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
