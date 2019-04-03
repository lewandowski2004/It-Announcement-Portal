package radoslawlewandowski.portal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.ProgrammingLanguageRepository;
import radoslawlewandowski.portal.DTO.ProgrammingLanguageDto;
import radoslawlewandowski.portal.Model.ProgrammingLanguage;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProgrammingLanguageService {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveProgrammingLanguageDto(ProgrammingLanguageDto programmingLanguageDto){
        ProgrammingLanguage programmingLanguage= ProgrammingLanguage.builder()
                .name(programmingLanguageDto.getName())
                .build();
        programmingLanguageRepository.save(programmingLanguage);
    }

    public ProgrammingLanguageDto findById(int id) {
        ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id);
        return getProgrammingLanguageDto(programmingLanguage);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProgrammingLanguageById(int id){
        ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id);
        programmingLanguageRepository.delete(programmingLanguage);
    }

    public List<ProgrammingLanguageDto> findAllSkillsDto() {
        return getAllProgrammingLanguageDto(programmingLanguageRepository.findAll());
    }

    public List<ProgrammingLanguage> findByIdIn(List<Integer> listId) {
        return programmingLanguageRepository.findByIdIn(listId);
    }

    public ProgrammingLanguage getProgrammingLanguage(ProgrammingLanguageDto programmingLanguageDto) {
        return ProgrammingLanguage.builder()
                .id(programmingLanguageDto.getId())
                .name(programmingLanguageDto.getName())
                .build();
    }
    public ProgrammingLanguageDto getProgrammingLanguageDto(ProgrammingLanguage programmingLanguageDto) {
        return ProgrammingLanguageDto.builder()
                .id(programmingLanguageDto.getId())
                .name(programmingLanguageDto.getName())
                .build();
    }

    public List<ProgrammingLanguage> getAllProgrammingLanguage(List<ProgrammingLanguageDto> programmingLanguageDtos) {
        List<ProgrammingLanguage> programmingLanguageList = new ArrayList<>();
        for (ProgrammingLanguageDto programmingLanguageDto : programmingLanguageDtos) {
            programmingLanguageList.add(getProgrammingLanguage(programmingLanguageDto));
        }
        return programmingLanguageList;
    }

    public List<ProgrammingLanguageDto> getAllProgrammingLanguageDto(List<ProgrammingLanguage> programmingLanguageList) {
        List<ProgrammingLanguageDto> programmingLanguageDtoList = new ArrayList<>();
        for (ProgrammingLanguage programmingLanguage : programmingLanguageList) {
            programmingLanguageDtoList.add(getProgrammingLanguageDto(programmingLanguage));
        }
        return programmingLanguageDtoList;
    }
}
