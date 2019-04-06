package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DTO.*;
import radoslawlewandowski.portal.Exceptions.AdvertisementNotFoundException;
import radoslawlewandowski.portal.Service.AdvertisementService;
import radoslawlewandowski.portal.Service.CompanyService;
import radoslawlewandowski.portal.Service.UserService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/advertisements")
    List<AdvertisementDto> allAdvertisements() {
        return advertisementService.findAllAdvertisementDto();
    }

    @GetMapping("/advertisementsByCompany")
    List<AdvertisementDto> alladvertisementsByCompany() {
        CompanyDto companyDto = companyService.getLoggedCompanyDto();
        return advertisementService.findAllAdvertisementsByCompany_Id(companyDto.getId());
    }

    /*
     * http://localhost:8080/advertisements/city-programmingLanguage-filter?city=Warszawa&programmingLanguage=Java
     */
    List<AdvertisementDto> allAdvertisementsByProgrammingLanguageAndCityAndExpLevel(
            @RequestParam(value="city") String city,
            @RequestParam(value="programmingLanguage") String programmingLanguage,
            @RequestParam(value="expLevel") String expLevel) {
        return advertisementService.findAllAdvertisementsDtoByProgrammingLanguageAndCityAndExpLevel(city, programmingLanguage, expLevel);
    }

    /*
    * http://localhost:8080/advertisements/city-programmingLanguage-filter?city=Warszawa&programmingLanguage=Java
    */
    @GetMapping("/advertisements/city-programmingLanguage-filter")
    List<AdvertisementDto> allAdvertisementsByProgrammingLanguageAndCity(
            @RequestParam(value="city") String city,
            @RequestParam(value="programmingLanguage") String programmingLanguage) {
        return advertisementService.findAllAdvertisementsDtoByProgrammingLanguageAndCity(city, programmingLanguage);
    }

    /*
     * http://localhost:8080/advertisements/programmingLanguage-filter?programmingLanguage=Java
     */
    @GetMapping("/advertisements/programmingLanguage-filter")
    List<AdvertisementDto> allAdvertisementsByProgrammingLanguage(
            @RequestParam(value="programmingLanguage") String programmingLanguage) {
        return advertisementService.findAllAdvertisementsDtoByProgrammingLanguage(programmingLanguage);
    }

    /*
     * http://localhost:8080/advertisements/city-filter?city=Warszawa
     */
    @GetMapping("/advertisements/city-filter")
    List<AdvertisementDto> allAdvertisementsByCity(
            @RequestParam(value="city") String city) {
        return advertisementService.findAllAdvertisementsDtoByCity(city);
    }

   /* @GetMapping("/my-advertisements")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    List<AdvertisementDto> allAdvertisementsByUserId() {
        UserDto userDto = userService.getLoggedEmployeeDto();
        return advertisementService.findByUserId(userDto.getId());
    }
*/
    @GetMapping("/advertisement/{id}")
    AdvertisementDto getAdvertisement(
            @PathVariable Long id) {
        AdvertisementDto advertisementDto = advertisementService.findById(id);
        if (advertisementDto == null)
            throw new AdvertisementNotFoundException("id-" + id);
        return advertisementDto;
    }

    @PostMapping("/addAdvertisement")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public void saveAdvertisment(
            @Valid @RequestBody AdvertisementDtoToSave newAdvertisement) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        newAdvertisement.setExperienceLevel(ExperienceLevelDto.JUNIOR);
        newAdvertisement.setProgrammingLanguages(list);
        advertisementService.saveAdvertisementDto(newAdvertisement);
    }

    @PostMapping("/deleteAdvertisement/{id}")
    public void deleteAdvertisement(
            @PathVariable Long id) {
        AdvertisementDto advertisement = advertisementService.findById(id);
        advertisementService.deleteAdvertisementById(advertisement);
    }
}
