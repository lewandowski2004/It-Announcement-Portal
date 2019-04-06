package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class AddressDto {

   // @NotEmpty(message = "Pole nie może być puste")
    private String addressLine1;

    private String addressLine2;

   // @NotEmpty(message = "Pole nie może być puste")
    private String city;

   // @NotEmpty(message = "Pole nie może być puste")
    private String country;

    //@NotEmpty(message = "Pole nie może być puste")
    private String zipCode;

    public AddressDto() {
    }
}
