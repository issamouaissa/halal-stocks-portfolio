package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Portefeuille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortefeuilleRepository extends JpaRepository<Portefeuille,Long> {
}
