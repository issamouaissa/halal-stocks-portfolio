package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.Portefeuille;
import emsi.java.halalstocks.services.PortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portefeuilles")
public class PortefeuilleController {

    @Autowired
    private PortefeuilleService portefeuilleService;

    @GetMapping
    public List<Portefeuille> getAllPortefeuilles() {
        return portefeuilleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Portefeuille> getPortefeuilleById(@PathVariable Long id) {
        return portefeuilleService.findById(id);
    }

    @PostMapping
    public Portefeuille createPortefeuille(@RequestBody Portefeuille portefeuille) {
        return portefeuilleService.save(portefeuille);
    }

    @PutMapping("/{id}")
    public Portefeuille updatePortefeuille(@PathVariable Long id, @RequestBody Portefeuille portefeuilleDetails) {
        Portefeuille portefeuille = portefeuilleService.findById(id).orElseThrow(() -> new RuntimeException("Portefeuille not found"));
        portefeuille.setValeurTotal(portefeuilleDetails.getValeurTotal());
        portefeuille.setDateCreation(portefeuilleDetails.getDateCreation());
        return portefeuilleService.save(portefeuille);
    }

    @DeleteMapping("/{id}")
    public void deletePortefeuille(@PathVariable Long id) {
        portefeuilleService.deleteById(id);
}
}