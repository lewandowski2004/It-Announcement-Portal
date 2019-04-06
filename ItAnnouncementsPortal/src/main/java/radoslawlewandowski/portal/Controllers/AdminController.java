package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import radoslawlewandowski.portal.DTO.CompanyDto;
import radoslawlewandowski.portal.DTO.UserDto;
import radoslawlewandowski.portal.Service.CompanyService;
import radoslawlewandowski.portal.Service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<UserDto> allUsers() {
        return userService.findAllUsersDto();
    }

    @PostMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(
            @PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/allCompany")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<CompanyDto> allCompany() {
        return companyService.findAllCompanyDto();
    }

    @PostMapping("/deleteCompany/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCompany(
            @PathVariable UUID id) {
        companyService.deleteCompanyById(id);
    }


}
