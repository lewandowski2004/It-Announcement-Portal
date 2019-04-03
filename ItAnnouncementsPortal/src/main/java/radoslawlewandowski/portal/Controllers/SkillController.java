package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DTO.SkillDto;
import radoslawlewandowski.portal.Service.SkillServise;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillServise skillServise;

    @GetMapping("/skills")
    List<SkillDto> all() {
        return skillServise.findAllSkillsDto();
    }
    @GetMapping("/skill/{id}")
    SkillDto getSkills(@PathVariable int id) {
        return skillServise.findById(id);
    }

    @PostMapping("/addSkill")
    public void saveSkill(@Valid @RequestBody SkillDto newSkill) {
        skillServise.saveSkillDto(newSkill);
    }
}
