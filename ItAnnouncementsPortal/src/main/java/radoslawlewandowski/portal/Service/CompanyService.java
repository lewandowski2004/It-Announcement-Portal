package radoslawlewandowski.portal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.CompanyRepository;
import radoslawlewandowski.portal.DAO.RoleRepository;
import radoslawlewandowski.portal.DTO.AddressDto;
import radoslawlewandowski.portal.DTO.CompanyDto;
import radoslawlewandowski.portal.DTO.CompanyDtoToSave;
import radoslawlewandowski.portal.Model.Address;
import radoslawlewandowski.portal.Model.Company;
import radoslawlewandowski.portal.Model.Role;

import java.util.*;

@Service
public class CompanyService {


    @Autowired
    private RoleService roleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveCompanyDto(CompanyDtoToSave companyDtoToSave) {
        Role role = roleRepository.findByRole("ROLE_COMPANY");
        Company company = Company.builder()
                .name(companyDtoToSave.getName())
                .description(companyDtoToSave.getDescription())
                .phoneNumber(companyDtoToSave.getPhoneNumber())
                .email(companyDtoToSave.getEmail())
                .address(addressService.getAddress(companyDtoToSave.getAddressDto()))
                .password(bCryptPasswordEncoder.encode(companyDtoToSave.getPassword()))
                .roles(new HashSet<Role>(Arrays.asList(role)))
                .active(1)
                .build();

        companyRepository.save(company);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCompanyById(UUID id){
        Company company = companyRepository.findById(id);
        companyRepository.delete(company);
    }

    public CompanyDto getLoggedCompanyDto() {
        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }
        CompanyDto companyDto = findByEmailDto(username);
        return companyDto;
    }

    public Company getLoggedCompany() {
        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }
        Company company = findByEmail(username);
        return company;
    }

    public CompanyDto findById(UUID id) {
        if (!id.equals("")) {
            Company company = companyRepository.findById(id);
            if (company != null) {
                return getCompanyDto(company);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Company findByEmail(String email) {
        if (!email.equals("")) {
            Company company = companyRepository.findByEmail(email);
            if (company != null) {
                return company;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public CompanyDto findByEmailDto(String email) {
        if (!email.equals("")) {
            Company user = companyRepository.findByEmail(email);
            if (user != null) {
                return getCompanyDto(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean emailIsUnique(String email) {
        if(companyRepository.findByEmail(email) == null){
            return true;
        }else {
            return false;
        }
    }

    public List<CompanyDto> findAllCompanyDto() {
        return findAllCompanyDtoList(companyRepository.findAll());
    }

    public Company getCompany(CompanyDto companyDto) {
        return  Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .phoneNumber(companyDto.getPhone_number())
                .email(companyDto.getEmail())
                .address(addressService.getAddress(companyDto.getAddressDto()))
                .password(bCryptPasswordEncoder.encode(companyDto.getPassword()))
                .roles(roleService.getRoles(companyDto.getRoles()))
                .active(1)
                .build();

    }

    public CompanyDto getCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .phone_number(company.getPhoneNumber())
                .email(company.getEmail())
                .addressDto(addressService.getAddressDto(company.getAddress()))
                .password(bCryptPasswordEncoder.encode(company.getPassword()))
                .roles(roleService.getRolesDto(company.getRoles()))
                .active(1)
                .build();
    }

    public List<CompanyDto> findAllCompanyDtoList(List<Company> companyList) {
        List<CompanyDto> companyDtoListDtoList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyDto companyDto = getCompanyDto(company);
            companyDtoListDtoList.add(companyDto);
        }
        return companyDtoListDtoList;
    }
}
