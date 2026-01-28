package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.Projet;
import emsi.java.halalstocks.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    public List<Projet> findAll() {
        return projetRepository.findAll();
    }

    public Optional<Projet> findById(Long id) {
        return projetRepository.findById(id);
    }

    public Projet save(Projet projet) {
        return projetRepository.save(projet);
    }

    public void deleteById(Long id) {
        projetRepository.deleteById(id);
}
}