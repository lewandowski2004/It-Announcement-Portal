package radoslawlewandowski.portal.Service;

import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DTO.LevelDto;
import radoslawlewandowski.portal.Model.Level;

@Service
public class LevelService {

    public LevelDto getLevelDto(Level level) {
        return LevelDto.valueOf(level.name());
    }

    public Level getLevel(LevelDto levelDto) {
        return Level.valueOf(levelDto.name());
    }
}

