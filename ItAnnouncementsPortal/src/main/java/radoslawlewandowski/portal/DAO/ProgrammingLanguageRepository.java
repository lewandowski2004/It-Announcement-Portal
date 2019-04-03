package radoslawlewandowski.portal.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.ProgrammingLanguage;

import java.util.List;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {

    ProgrammingLanguage findById(int id);
    List<ProgrammingLanguage> findAll();
    List<ProgrammingLanguage> findByIdIn(List<Integer> id);
    ProgrammingLanguage save(ProgrammingLanguage programmingLanguage);
}
