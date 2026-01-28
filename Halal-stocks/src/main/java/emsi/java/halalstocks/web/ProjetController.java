package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.Projet;
import emsi.java.halalstocks.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Projet> getProjetById(@PathVariable Long id) {
        return projetService.findById(id);
    }

    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        return projetService.save(projet);
    }

    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet projetDetails) {
        Projet projet = projetService.findById(id).orElseThrow(() -> new RuntimeException("Projet not found"));
        projet.setNom(projetDetails.getNom());
        projet.setDescription(projetDetails.getDescription());
        return projetService.save(projet);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteById(id);
}
}