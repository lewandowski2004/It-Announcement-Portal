package radoslawlewandowski.portal.Service;

import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DTO.ExperienceLevelDto;
import radoslawlewandowski.portal.Model.ExperienceLevel;

@Service
public class ExperienceLevelService {

    public ExperienceLevelDto getExperienceLevelDto(ExperienceLevel experienceLevel) {
        return ExperienceLevelDto.valueOf(experienceLevel.name());
    }

    public ExperienceLevel getExperienceLevel(ExperienceLevelDto experienceLevelDto) {
        return ExperienceLevel.valueOf(experienceLevelDto.name());
    }
}

