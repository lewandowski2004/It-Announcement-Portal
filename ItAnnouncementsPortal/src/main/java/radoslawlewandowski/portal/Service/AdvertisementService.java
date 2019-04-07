package radoslawlewandowski.portal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.AdvertisementRepository;
import radoslawlewandowski.portal.DTO.AdvertisementDto;
import radoslawlewandowski.portal.DTO.AdvertisementDtoToSave;
import radoslawlewandowski.portal.Model.Advertisement;


import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private SkillServise skillServise;

    @Autowired
    private CompanyService companyService;

    public void saveAdvertisementDto(AdvertisementDtoToSave saveAdvertisementDto) {
        Advertisement advertisement = Advertisement.builder()
                .title(saveAdvertisementDto.getTitle())
                .companyName(companyService.getLoggedCompany().getName())
                .salary(saveAdvertisementDto.getSalary())
                .city(saveAdvertisementDto.getCity())
                .description(saveAdvertisementDto.getDescription())
                .dateOfAddition(new Date())
                .programmingLanguages(programmingLanguageService.findByIdIn(saveAdvertisementDto.getProgrammingLanguages()))
                .experienceLevel(levelService.getLevel(saveAdvertisementDto.getExperienceLevel()))
                .company(companyService.getLoggedCompany())
                //.skills(skillServise.getSkills(saveAdvertisementDto.getSkills()))
                .build();

        advertisementRepository.save(advertisement);
    }

    public AdvertisementDto findById(long id) {
        Advertisement advertisement = advertisementRepository.findById(id);
        return getAdvertisementDto(advertisement);
    }

   /* public List<AdvertisementDto> findByUserId(UUID id) {
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByUser_Id(id));
    }*/

    @PreAuthorize("#advertisement.company.email == authentication.name or hasRole('ROLE_ADMIN')")
    public void deleteAdvertisementById(AdvertisementDto advertisement){
        advertisementRepository.delete(getAdvertisement(advertisement));
    }

    public List<AdvertisementDto> findAllAdvertisementDto() {
        return findAllAdvertisementDto(advertisementRepository.findAllByOrderByDateOfAdditionDesc());
    }

    public List<AdvertisementDto> findAllAdvertisementsByCompany_Id(UUID companyId){
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByCompany_Id(companyId));
    }

    public List<AdvertisementDto> findAllAdvertisementsDtoByProgrammingLanguageAndCityAndExpLevel(String city, String programmingLanguage, String expLevel) {
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByCityAndProgrammingLanguagesAndExpLevel(city, programmingLanguage, expLevel));
    }

    public List<AdvertisementDto> findAllAdvertisementsDtoByProgrammingLanguageAndCity(String city, String programmingLanguage) {
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByCityAndProgrammingLanguages(city, programmingLanguage));
    }

    public List<AdvertisementDto> findAllAdvertisementsDtoByProgrammingLanguage(String programmingLanguage) {
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByProgrammingLanguages(programmingLanguage));
    }

    public List<AdvertisementDto> findAllAdvertisementsDtoByCity(String city) {
        return findAllAdvertisementDto(advertisementRepository.findAdvertisementsByCityOrderByDateOfAdditionDesc(city));
    }

    public Advertisement getAdvertisement(AdvertisementDto advertisementDto) {
        return Advertisement.builder()
                .id(advertisementDto.getId())
                .title(advertisementDto.getTitle())
                .companyName(advertisementDto.getCompanyName())
                .salary(advertisementDto.getSalary())
                .city(advertisementDto.getCity())
                .description(advertisementDto.getDescription())
                .dateOfAddition(advertisementDto.getDateOfAddition())
                .programmingLanguages(programmingLanguageService.getProgrammingLanguages(advertisementDto.getProgrammingLanguages()))
                .experienceLevel(levelService.getLevel(advertisementDto.getExperienceLevel()))
                .company(companyService.getCompany(advertisementDto.getCompany()))
                //.skills(skillServise.getSkills(advertisementDto.getSkills()))
                .build();
    }

    public AdvertisementDto getAdvertisementDto(Advertisement advertisement) {
        return AdvertisementDto.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .companyName(advertisement.getCompanyName())
                .salary(advertisement.getSalary())
                .city(advertisement.getCity())
                .description(advertisement.getDescription())
                .dateOfAddition(advertisement.getDateOfAddition())
                .programmingLanguages(programmingLanguageService.getProgrammingLanguagesDto(advertisement.getProgrammingLanguages()))
                .experienceLevel(levelService.getLevelDto(advertisement.getExperienceLevel()))
                .company(companyService.getCompanyDto(advertisement.getCompany()))
                //.skills(skillServise.getSkillsDto(advertisement.getSkills()))
                .build();
    }

    public List<AdvertisementDto> findAllAdvertisementDto(List<Advertisement> advertisementsList) {
        List<AdvertisementDto> advertisementDtoListDtoList = new ArrayList<>();
        for (Advertisement advertisement : advertisementsList) {
            AdvertisementDto advertisementDto = getAdvertisementDto(advertisement);
            advertisementDtoListDtoList.add(advertisementDto);
        }
        return advertisementDtoListDtoList;
    }
}
