package emsi.java.halalstocks.web;




import emsi.java.halalstocks.entities.ShariaComplianceResult;
import emsi.java.halalstocks.services.ShariaComplianceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShariaComplianceController {

    private static final Logger logger = LoggerFactory.getLogger(ShariaComplianceController.class);

    private final ShariaComplianceService shariaComplianceService;

    @Autowired
    public ShariaComplianceController(ShariaComplianceService shariaComplianceService) {
        this.shariaComplianceService = shariaComplianceService;
    }

    @GetMapping("/shariah-compliance")
    public ResponseEntity<ShariaComplianceResult> getShariaCompliance(@RequestParam String symbol) {
        ShariaComplianceResult result = shariaComplianceService.calculateShariaCompliance(symbol);

        // Vérification si le résultat est null (symbole non trouvé)
        if (result == null) {
            logger.warn("Shariah Compliance Result not found for symbol: {}", symbol);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ShariaComplianceResult("Data not available for this stock"));
        }

        logger.info("Shariah Compliance Result for symbol {}: {}", symbol, result.getStatus());
        return ResponseEntity.ok(result);  // Renvoie un statut HTTP 200 avec le résultat
    }
}