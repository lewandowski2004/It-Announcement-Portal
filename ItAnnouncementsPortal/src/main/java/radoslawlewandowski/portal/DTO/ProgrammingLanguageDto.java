package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProgrammingLanguageDto {

    private int id;

    private String name;

    protected ProgrammingLanguageDto(){}
}
