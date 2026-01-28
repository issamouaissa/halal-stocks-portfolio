package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.Rapport;
import emsi.java.halalstocks.services.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rapports")
public class RapportController {

    @Autowired
    private RapportService rapportService;

    @GetMapping
    public List<Rapport> getAllRapports() {
        return rapportService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rapport> getRapportById(@PathVariable Long id) {
        return rapportService.findById(id);
    }

    @PostMapping
    public Rapport createRapport(@RequestBody Rapport rapport) {
        return rapportService.save(rapport);
    }

    @PutMapping("/{id}")
    public Rapport updateRapport(@PathVariable Long id, @RequestBody Rapport rapportDetails) {
        Rapport rapport = rapportService.findById(id).orElseThrow(() -> new RuntimeException("Rapport not found"));
        rapport.setContenu(rapportDetails.getContenu());
        rapport.setDateCreation(rapportDetails.getDateCreation());
        return rapportService.save(rapport);
    }

    @DeleteMapping("/{id}")
    public void deleteRapport(@PathVariable Long id) {
        rapportService.deleteById(id);
}
}