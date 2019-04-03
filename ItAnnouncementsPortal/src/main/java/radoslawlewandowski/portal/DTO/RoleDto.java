package radoslawlewandowski.portal.DTO;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RoleDto {

    private int id;

    private String role;
}