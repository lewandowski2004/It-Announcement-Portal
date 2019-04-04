package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DTO.*;
import radoslawlewandowski.portal.Exceptions.UserNotFoundException;
import radoslawlewandowski.portal.Service.CompanyService;
import radoslawlewandowski.portal.Service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/allCompany")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    List<CompanyDto> all() {
        return companyService.findAllCompanyDto();
    }

    @GetMapping("/company/{id}")
    //@PreAuthorize("hasRole('ROLE_COMPANY')")
    CompanyDto getCompany(@PathVariable UUID id) {
        CompanyDto companyDto = companyService.findById(id);
       /* if (companyDto == null)
            throw new UserNotFoundException("id-" + id);*/
        return companyDto;
    }

    @PostMapping("/addCompany")
    void saveCompany(@Valid @RequestBody CompanyDtoToSave newCompany) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine1("Ciółkówko");
        addressDto.setCity("Płock");
        addressDto.setCountry("Polska");
        addressDto.setZipCode("09-400");
        newCompany.setAddressDto(addressDto);
        companyService.saveCompanyDto(newCompany);
    }

    @PostMapping("/deleteCompany/{id}")
    //@PreAuthorize("hasRole('ROLE_COMPANY')")
    public void deleteCompany(
            @PathVariable UUID id) {
        companyService.deleteCompanyById(id);
    }

}
