package emsi.java.halalstocks.web;


import emsi.java.halalstocks.entities.Company;
import emsi.java.halalstocks.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list/{symbol}")
    public String showCompanies(@PathVariable String symbol, Model model) {
        // Récupérer les données de l'API et enregistrer dans la base de données
        Company company = companyService.saveCompanyData(symbol);

        // Si l'entreprise a été récupérée avec succès
        if (company != null) {
            model.addAttribute("company", company);
        } else {
            model.addAttribute("error", "Aucune entreprise trouvée pour ce symbole");
        }

        // Retourner le template Thymeleaf pour afficher les données
        return "company-list";
    }

    @GetMapping("/update-all")
    public String updateAllCompanies() {
        // Récupérer toutes les entreprises et les sauvegarder dans la base de données
        companyService.saveAllCompanyData();

        // Rediriger vers une page de confirmation ou une autre page appropriée
        return "update-confirmation";
    }

    @GetMapping("/list")
    public String listCompanies(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "search", required = false) String search,
                                Model model) {
        Page<Company> companyPage;
        if (search != null && !search.isEmpty()) {
            companyPage = companyService.searchCompaniesBySymbol(search, page, size);
        } else {
            companyPage = companyService.getCompaniesPage(page, size);
        }
        model.addAttribute("companies", companyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", companyPage.getTotalPages());
        model.addAttribute("search", search);
        return "company-list";
    }
}