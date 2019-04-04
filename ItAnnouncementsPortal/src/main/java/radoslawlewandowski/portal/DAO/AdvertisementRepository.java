package radoslawlewandowski.portal.DAO;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.Advertisement;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Advertisement findById(long id);
    //List<Advertisement> findAdvertisementsByUser_Id(UUID userId);
    List<Advertisement> findAllByOrderByDateOfAdditionDesc();

    @Query(value = "SELECT * FROM advertisement " +
            "INNER JOIN advertisement_programming_language " +
            " ON advertisement.advertisement_id = advertisement_programming_language.advertisement_id " +
            "INNER JOIN programming_language " +
            "ON advertisement_programming_language.programming_language_id = programming_language.programming_language_id " +
            "WHERE city = :city And programming_language.name = :programmingLanguage AND expLevel = :expLevel " +
            "ORDER BY date_of_addition DESC ", nativeQuery = true)
    List<Advertisement> findAdvertisementsByCityAndProgrammingLanguagesAndExpLevel(@Param("city") String city,
                                                                                   @Param("programmingLanguage") String programmingLanguage,
                                                                                   @Param("expLevel") String expLevel);

    @Query(value = "SELECT * FROM advertisement " +
            "INNER JOIN advertisement_programming_language " +
            " ON advertisement.advertisement_id = advertisement_programming_language.advertisement_id " +
            "INNER JOIN programming_language " +
            "ON advertisement_programming_language.programming_language_id = programming_language.programming_language_id " +
            "WHERE city = :city And programming_language.name = :programmingLanguage " +
            "ORDER BY date_of_addition DESC ", nativeQuery = true)
    List<Advertisement> findAdvertisementsByCityAndProgrammingLanguages(@Param("city") String city,
                                                                        @Param("programmingLanguage") String programmingLanguage );

    @Query(value = "SELECT * FROM advertisement "+
            "INNER JOIN advertisement_programming_language " +
            "ON advertisement.advertisement_id = advertisement_programming_language.advertisement_id " +
            "INNER JOIN programming_language " +
            "ON advertisement_programming_language.programming_language_id = programming_language.programming_language_id " +
            "WHERE programming_language.name = :programmingLanguage " +
            "ORDER BY date_of_addition DESC ", nativeQuery = true)
    List<Advertisement> findAdvertisementsByProgrammingLanguages(@Param("programmingLanguage") String programmingLanguage);
    List<Advertisement> findAdvertisementsByCityOrderByDateOfAdditionDesc(String city);
}
