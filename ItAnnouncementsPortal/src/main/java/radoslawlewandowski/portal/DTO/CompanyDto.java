package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import radoslawlewandowski.portal.Model.Address;
import radoslawlewandowski.portal.Model.Advertisement;
import radoslawlewandowski.portal.Model.Role;
import radoslawlewandowski.portal.Model.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompanyDto {

    private UUID id;

    private String name;

    private String description;

    private String phone_number;

    private String email;

    private AddressDto addressDto;

    private String password;

    private String confirmPassword;

    private int active;

    private Set<RoleDto> roles;

    private int nrRoli;

    private Set<AdvertisementDto> advertisements;

    private String newPassword;

    private String oldPassword;

}
