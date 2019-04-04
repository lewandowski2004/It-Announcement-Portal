package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import radoslawlewandowski.portal.Model.Address;
import radoslawlewandowski.portal.Model.Advertisement;
import radoslawlewandowski.portal.Model.Role;
import radoslawlewandowski.portal.Model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompanyDtoToSave {

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @NotEmpty(message = "Pole nie może być puste")
    private String name;

    @Size(max = 700, message = "Pole może zawierać więcej niż 700 znaków")
    private String description;

    @NotEmpty(message = "Pole nie może być puste")
    private String phoneNumber;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
    private String email;

    private AddressDto addressDto;

    private String password;

    private String confirmPassword;

    private int active;

    private Set<Integer> roles;

    protected CompanyDtoToSave() {
    }
}
