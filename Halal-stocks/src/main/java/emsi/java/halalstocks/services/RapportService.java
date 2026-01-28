package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.Rapport;
import emsi.java.halalstocks.repositories.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapportService {

    @Autowired
    private RapportRepository rapportRepository;

    public List<Rapport> findAll() {
        return rapportRepository.findAll();
    }

    public Optional<Rapport> findById(Long id) {
        return rapportRepository.findById(id);
    }

    public Rapport save(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public void deleteById(Long id) {
        rapportRepository.deleteById(id);
}
}