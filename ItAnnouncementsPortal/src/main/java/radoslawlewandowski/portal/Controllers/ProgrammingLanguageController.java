package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DTO.ProgrammingLanguageDto;
import radoslawlewandowski.portal.Exceptions.ProgrammingLanguageNotFoundException;
import radoslawlewandowski.portal.Service.ProgrammingLanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProgrammingLanguageController {

    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @GetMapping("/programmingLanguages")
    List<ProgrammingLanguageDto> getProgrammingLanguages() {
        return programmingLanguageService.findAllSkillsDto();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/programmingLanguage/{id}")
    ProgrammingLanguageDto getProgrammingLanguage(@PathVariable int id) {
        ProgrammingLanguageDto programmingLanguageDto = programmingLanguageService.findById(id);
        if (programmingLanguageDto == null)
            throw new ProgrammingLanguageNotFoundException("id-" + id);
        return programmingLanguageDto;
    }

    @PostMapping("/addProgrammingLanguage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveProgrammingLanguage(@Valid @RequestBody ProgrammingLanguageDto newProgrammingLanguage) {
        programmingLanguageService.saveProgrammingLanguageDto(newProgrammingLanguage);
    }

    @PostMapping("/deleteProgrammingLanguage/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProgrammingLanguage(
            @PathVariable int id) {
        programmingLanguageService.deleteProgrammingLanguageById(id);
    }
}
