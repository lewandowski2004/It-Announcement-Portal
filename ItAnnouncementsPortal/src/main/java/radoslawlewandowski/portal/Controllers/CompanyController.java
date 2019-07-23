package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DTO.*;
import radoslawlewandowski.portal.Exceptions.CompanyNotFoundException;
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

    @GetMapping("/company/{id}")
    CompanyDto getCompany(@PathVariable UUID id) {
        CompanyDto companyDto = companyService.findById(id);
        if (companyDto == null)
            throw new CompanyNotFoundException("id-" + id);
        return companyDto;
    }

    @PostMapping("/add/company")
    void registerCompany(@Valid @RequestBody CompanyDtoToSave newCompany ) {
        companyService.saveCompanyDto(newCompany);
    }
}
