package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.Portefeuille;
import emsi.java.halalstocks.repositories.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortefeuilleService {

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    public List<Portefeuille> findAll() {
        return portefeuilleRepository.findAll();
    }

    public Optional<Portefeuille> findById(Long id) {
        return portefeuilleRepository.findById(id);
    }

    public Portefeuille save(Portefeuille portefeuille) {
        return portefeuilleRepository.save(portefeuille);
    }

    public void deleteById(Long id) {
        portefeuilleRepository.deleteById(id);
}
}