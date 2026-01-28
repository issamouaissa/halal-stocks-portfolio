package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
