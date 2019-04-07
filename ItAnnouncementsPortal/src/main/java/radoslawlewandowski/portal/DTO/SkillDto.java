package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import radoslawlewandowski.portal.Model.Advertisement;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SkillDto {

    private int id;

    private String name;

    private Set<AdvertisementDto> advertisements;

    protected SkillDto(){}
}
