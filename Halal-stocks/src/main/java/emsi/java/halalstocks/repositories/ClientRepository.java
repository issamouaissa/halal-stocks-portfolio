package emsi.java.halalstocks.repositories;
import emsi.java.halalstocks.dtos.ClientDTO;
import emsi.java.halalstocks.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByClientname(String clientname);
    // Optional<Client> findOneByEmailAndPassword(String email, String password);
    Optional<Client> findById(Integer id);

}
