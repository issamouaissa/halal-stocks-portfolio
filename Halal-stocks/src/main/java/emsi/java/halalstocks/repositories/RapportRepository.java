package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport,Long> {

}
