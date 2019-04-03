package radoslawlewandowski.portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import radoslawlewandowski.portal.Model.User;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AdvertisementDtoToSave {

    private long id;

    private String title;

    private String companyName;

    private String salary;

    private String city;

    private String description;

    private Date dateOfAddition;

    private UserDto user;

    private List<Integer> programmingLanguages ;

    private ExperienceLevelDto experienceLevel;

    protected AdvertisementDtoToSave(){}
}
