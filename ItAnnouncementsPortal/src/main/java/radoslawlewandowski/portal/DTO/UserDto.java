package radoslawlewandowski.portal.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private UUID id;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @NotEmpty(message = "Pole nie może być puste")
    private String name;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @NotEmpty(message = "Pole nie może być puste")
    private String lastName;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
    private String email;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")
    private String password;

  /*  @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")*/
    private String confirmPassword;

    private int active;

    private Set<RoleDto> roles;

    private int nrRoli;

    private String newPassword;

    private String oldPassword;

    protected UserDto(){}


}
