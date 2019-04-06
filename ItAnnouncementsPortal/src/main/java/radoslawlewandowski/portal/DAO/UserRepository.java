package radoslawlewandowski.portal.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);
    User findById(UUID id);
    User findByEmail(String email);

    @Query(value = "SELECT email, password, active FROM user WHERE email = :em", nativeQuery = true)
    String email(@Param("em") String em);
}
