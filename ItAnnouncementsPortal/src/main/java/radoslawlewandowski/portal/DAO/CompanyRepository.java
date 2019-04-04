package radoslawlewandowski.portal.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.Company;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company save(Company company);
    Company findById(UUID id);
    Company findByEmail(String email);
}
