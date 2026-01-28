package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.Company;
import emsi.java.halalstocks.entities.StockList;
import emsi.java.halalstocks.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${financialmodelingprep.apikey}")
    private String apiKey;

    private final String BASE_URL = "https://financialmodelingprep.com/api/v3/";
    private RestTemplate restTemplate = new RestTemplate();

    public Company[] getCompanyData(String symbol) {
        String url = BASE_URL + "quote/" + symbol + "?apikey=" + apiKey;
        return restTemplate.getForObject(url, Company[].class);
    }
    public Company saveCompanyData(String symbol) {
        // Récupérer les données de l'entreprise via l'API externe
        String url = BASE_URL + "quote/" + symbol + "?apikey=" + apiKey;
        Company[] companyArray = restTemplate.getForObject(url, Company[].class);

        if (companyArray != null && companyArray.length > 0) {
            Company company = companyArray[0];

            // Vérifier si l'entreprise existe déjà dans la base
            Optional<Company> existingCompany = companyRepository.findBySymbol(company.getSymbol());

            try {
                if (existingCompany.isPresent()) {
                    // Mettre à jour les informations de l'entreprise existante
                    Company companyToUpdate = existingCompany.get();
                    companyToUpdate.setName(company.getName());
                    companyToUpdate.setPrice(company.getPrice());
                    companyToUpdate.setChangesPercentage(company.getChangesPercentage());
                    companyToUpdate.setChanges(company.getChanges());
                    companyToUpdate.setDayLow(company.getDayLow());
                    companyToUpdate.setDayHigh(company.getDayHigh());
                    companyToUpdate.setYearHigh(company.getYearHigh());
                    companyToUpdate.setYearLow(company.getYearLow());
                    companyToUpdate.setMarketCap(company.getMarketCap());
                    companyToUpdate.setPriceAvg50(company.getPriceAvg50());
                    companyToUpdate.setPriceAvg200(company.getPriceAvg200());
                    companyToUpdate.setExchange(company.getExchange());
                    companyToUpdate.setVolume(company.getVolume());
                    companyToUpdate.setAvgVolume(company.getAvgVolume());
                    companyToUpdate.setOpen(company.getOpen());
                    companyToUpdate.setPreviousClose(company.getPreviousClose());
                    companyToUpdate.setEps(company.getEps());
                    companyToUpdate.setPe(company.getPe());
                    companyToUpdate.setEarningsAnnouncement(company.getEarningsAnnouncement());
                    companyToUpdate.setSharesOutstanding(company.getSharesOutstanding());
                    companyToUpdate.setTimestamp(company.getTimestamp());
                    companyToUpdate.setEarningsPerShare(company.getEarningsPerShare());
                    companyToUpdate.setDividendYield(company.getDividendYield());
                    companyToUpdate.setBeta(company.getBeta());
                    companyToUpdate.setDividendRate(company.getDividendRate());
                    companyToUpdate.setForwardPE(company.getForwardPE());
                    companyToUpdate.setPegRatio(company.getPegRatio());
                    companyToUpdate.setFiftyTwoWeekRange(company.getFiftyTwoWeekRange());
                    companyToUpdate.setAnnualDividend(company.getAnnualDividend());
                    companyToUpdate.setPriceToSales(company.getPriceToSales());
                    companyToUpdate.setPriceToBook(company.getPriceToBook());
                    companyToUpdate.setExDividendDate(company.getExDividendDate());
                    companyToUpdate.setTargetMeanPrice(company.getTargetMeanPrice());
                    companyToUpdate.setTargetHighPrice(company.getTargetHighPrice());
                    companyToUpdate.setTargetLowPrice(company.getTargetLowPrice());
                    companyToUpdate.setRecommendationMean(company.getRecommendationMean());
                    companyToUpdate.setRecommendationKey(company.getRecommendationKey());
                    companyToUpdate.setTrailingPE(company.getTrailingPE());
                    companyToUpdate.setTrailingAnnualDividendYield(company.getTrailingAnnualDividendYield());
                    companyToUpdate.setFiftyDayAverage(company.getFiftyDayAverage());
                    companyToUpdate.setTwoHundredDayAverage(company.getTwoHundredDayAverage());

                    return companyRepository.save(companyToUpdate);
                } else {
                    // Enregistrer la nouvelle entreprise
                    return companyRepository.save(company);
                }
            } catch (DataIntegrityViolationException e) {
                // Gérer l'exception, par exemple en enregistrant les erreurs ou en alertant l'utilisateur
                System.err.println("Erreur lors de la sauvegarde des données de l'entreprise : " + e.getMessage());
            }
        }
        return null; // Aucune donnée récupérée ou erreur de traitement
    }

    public Page<Company> getCompaniesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable);
    }

    public Page<Company> searchCompaniesBySymbol(String symbol, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findBySymbolContainingIgnoreCase(symbol, pageable);
    }

    public void saveAllCompanyData() {
        // Récupérer la liste de toutes les entreprises
        String url = BASE_URL + "stock/list?apikey=" + apiKey;
        StockList[] stockList = restTemplate.getForObject(url, StockList[].class);

        if (stockList != null) {
            for (StockList stock : stockList) {
                String symbol = stock.getSymbol();
                Company[] companyArray = getCompanyData(symbol);

                if (companyArray != null && companyArray.length > 0) {
                    Company company = companyArray[0];
                    try {
                        // Vérifier si l'entreprise existe déjà dans la base
                        Optional<Company> existingCompany = companyRepository.findBySymbol(company.getSymbol());

                        if (existingCompany.isPresent()) {
                            // Mettre à jour les informations de l'entreprise existante
                            Company companyToUpdate = existingCompany.get();
                            companyToUpdate.updateFrom(company); // Utiliser une méthode d'update pour simplifier
                            companyRepository.save(companyToUpdate);
                        } else {
                            // Enregistrer la nouvelle entreprise
                            companyRepository.save(company);
                        }
                    } catch (DataIntegrityViolationException e) {
                        // Gérer l'exception
                        System.err.println("Erreur lors de la sauvegarde des données de l'entreprise : " + e.getMessage());
                    }
                }
            }
        }
    }
}