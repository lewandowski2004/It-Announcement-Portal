package radoslawlewandowski.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import radoslawlewandowski.portal.DAO.UserRepository;
import radoslawlewandowski.portal.DTO.UserDto;
import radoslawlewandowski.portal.DTO.UserDtoToSave;
import radoslawlewandowski.portal.Exceptions.UserNotFoundException;
import radoslawlewandowski.portal.Model.User;
import radoslawlewandowski.portal.Service.UserService;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    UserDto getUser(@PathVariable UUID id) {
        UserDto userDto = userService.findById(id);
        if (userDto == null)
            throw new UserNotFoundException("id-" + id);
        return userDto;
    }

    @PostMapping("/add/users")
    void registerUser(@Valid @RequestBody UserDtoToSave newUser) {
        userService.saveUserDto(newUser);
    }

}
