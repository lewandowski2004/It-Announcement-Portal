package radoslawlewandowski.portal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.SkillRepository;
import radoslawlewandowski.portal.DTO.SkillDto;
import radoslawlewandowski.portal.Model.Skill;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SkillServise {

    @Autowired
    private SkillRepository skillRepository;

    public void saveSkillDto(SkillDto skillDto) {
        Skill skill = Skill.builder()
                .name(skillDto.getName())
                //.experienceLevel(skillDto.getExperienceLevel())
                .build();

        skillRepository.save(skill);
    }

    public List<SkillDto> findAllSkillsDto() {
        return getAllSkillsDtoList(skillRepository.findAll());
    }

    public SkillDto findById(int id) {
        Skill skill = skillRepository.findById(id);
        return getSkillDto(skill);
    }

    public List<SkillDto> getAllSkillsDtoList(List<Skill> skillsList){
        List<SkillDto> skillsDtoList = new ArrayList<>();
        for (Skill skills : skillsList){
            SkillDto skillDto = getSkillDto(skills);
            skillsDtoList.add(skillDto);
        }
        return skillsDtoList;
    }
    public Skill getSkill(SkillDto skillDto) {
        return Skill.builder()
                .id(skillDto.getId())
                .name(skillDto.getName())
                //.advertisement(skillDto.getAdvertisement())
                .build();
    }
    public SkillDto getSkillDto(Skill skill) {
        return SkillDto.builder()
                .id(skill.getId())
                .name(skill.getName())
                //.advertisement(skill.getAdvertisement())
                .build();
    }
    public SkillDto getSkillDtoWithoutAdvertisment(Skill skill) {
        return SkillDto.builder()
                .id(skill.getId())
                .name(skill.getName())
                //.advertisement(skill.getAdvertisement())
                .build();
    }
    public Set<Skill> getSkills(Set<SkillDto> skillsDto) {
        Set<Skill> skills = new HashSet<>();
        for (SkillDto skillDto : skillsDto) {
            skills.add(getSkill(skillDto));
        }
        return skills;
    }
}
