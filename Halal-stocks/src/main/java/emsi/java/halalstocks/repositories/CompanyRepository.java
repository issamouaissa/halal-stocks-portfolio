package emsi.java.halalstocks.repositories;

import emsi.java.halalstocks.entities.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findBySymbol(String symbol);
    Page<Company> findBySymbolContainingIgnoreCase(String symbol, Pageable pageable);

}
