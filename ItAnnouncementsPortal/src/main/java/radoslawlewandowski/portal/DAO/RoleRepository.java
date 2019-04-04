package radoslawlewandowski.portal.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
    List<Role> findAll();
    Set<Role> findByIdIn(Set<Integer> id);
}
