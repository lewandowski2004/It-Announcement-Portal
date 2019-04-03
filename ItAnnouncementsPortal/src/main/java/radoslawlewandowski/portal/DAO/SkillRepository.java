package radoslawlewandowski.portal.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radoslawlewandowski.portal.Model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Skill findById(int id);
}
