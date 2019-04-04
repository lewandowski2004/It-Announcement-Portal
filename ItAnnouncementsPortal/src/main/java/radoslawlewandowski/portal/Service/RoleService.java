package radoslawlewandowski.portal.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radoslawlewandowski.portal.DAO.RoleRepository;
import radoslawlewandowski.portal.DTO.RoleDto;
import radoslawlewandowski.portal.Model.Role;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /*Dto method*/
    public List<Integer> getRolesIdDto(List<Role> roles) {
        List<Integer> listId = new ArrayList<>();
        for (Role role : roles) {
            listId.add(role.getId());
        }
        return listId;
    }

    public Set<Role> findByIdIn(Set<Integer> listId) {
        return roleRepository.findByIdIn(listId);
    }

    public Role getRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .role(roleDto.getRole())
                .build();
    }
    public RoleDto getRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }

    public Set<Role> getRoles(Set<RoleDto> roleDtos) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : roleDtos) {
            roles.add(getRole(roleDto));
        }
        return roles;
    }

    public Set<RoleDto> getRolesDto(Set<Role> roleList) {
        Set<RoleDto> roles = new HashSet<>();
        for (Role role : roleList) {
            roles.add(getRoleDto(role));
        }
        return roles;
    }
}
