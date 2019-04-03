package radoslawlewandowski.portal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.UserRepository;
import radoslawlewandowski.portal.DTO.UserDto;
import radoslawlewandowski.portal.DTO.UserDtoToSave;
import radoslawlewandowski.portal.Model.Advertisement;
import radoslawlewandowski.portal.Model.User;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUserDto(UserDtoToSave userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .roles(roleService.findByIdIn(Arrays.asList(1)))
                .active(1)
                .build();

        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUserById(UUID id){
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }

    public UserDto getLoggedEmployeeDto() {
        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }
        UserDto userDto = findByEmailDto(username);
        return userDto;
    }

    public User getLoggedEmployee() {
        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }
        User user = findByEmail(username);
        return user;
    }

    public UserDto findById(UUID id) {
        if (!id.equals("")) {
            User user = userRepository.findById(id);
            if (user != null) {
                return getUserDto(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public User findByEmail(String email) {
        if (!email.equals("")) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public UserDto findByEmailDto(String email) {
        if (!email.equals("")) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return getUserDto(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean emailIsUnique(String email) {
        if(userRepository.findByEmail(email) == null){
            return true;
        }else {
            return false;
        }
    }

    public List<UserDto> findAllUsersDto() {
        return findAllUsersDtoList(userRepository.findAll());
    }

    public User getUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .confirmPassword(userDto.getConfirmPassword())
                .active(userDto.getActive())
                .roles(roleService.getRoles(userDto.getRoles()))
                .nrRoli(userDto.getNrRoli())
                .build();

    }

    public UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .confirmPassword(user.getConfirmPassword())
                .active(user.getActive())
                .roles(roleService.getRolesDto(user.getRoles()))
                .nrRoli(user.getNrRoli())
                .build();
    }

    public List<UserDto> findAllUsersDtoList(List<User> usersList) {
        List<UserDto> userDtoListDtoList = new ArrayList<>();
        for (User user : usersList) {
            UserDto userDto = getUserDto(user);
            userDtoListDtoList.add(userDto);
        }
        return userDtoListDtoList;
    }

}
