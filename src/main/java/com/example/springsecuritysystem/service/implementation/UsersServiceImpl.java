package com.example.springsecuritysystem.service.implementation;

import com.example.springsecuritysystem.entity.Teritory;
import com.example.springsecuritysystem.entity.system.user.Permission;
import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.entity.system.user.Users;
import com.example.springsecuritysystem.model.AddUserModel;
import com.example.springsecuritysystem.repository.UsersRepository;
import com.example.springsecuritysystem.service.PermissionService;
import com.example.springsecuritysystem.service.RoleService;
import com.example.springsecuritysystem.service.TerritoryService;
import com.example.springsecuritysystem.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final TerritoryService territoryService;

    private final ZoneId defaultZoneId = ZoneId.systemDefault();

    public UsersServiceImpl(UsersRepository usersRepository,
                            PermissionServiceImpl permissionService,
                            RoleServiceImpl roleService,
                            TerritoryServiceImpl territoryService) {
        this.usersRepository = usersRepository;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.territoryService = territoryService;
    }


    @Override
    @Transactional
    public Users addNewUser(AddUserModel addUserModel) {
        /* Get current user authentication */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /* Check if user already present */
        getUserByUserName(addUserModel.getUserName()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exist with username " + addUserModel.getUserName());
        });

        /* Match password with confirm password */
        if (!addUserModel.getPassword().equals(addUserModel.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirm password don't match with password");
        }

        /* Get User object for currently authorized */
        Users currentAuthorizedUser = getUserByUserName(authentication.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not found current user information"));

        /* Validate department */
        if (addUserModel.getDepartment() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please select department");
        }

       /* *//* Get department list by department ids *//*
        Department department = departmentService.getDepartmentById(addUserModel.getDepartment()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departments not found"));

        *//* Get department list by department ids *//*
        Designation designation = designationService.getDesignationById(addUserModel.getDesignation()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Designation not found"));
*/
        /* Get department list by department ids */
        Teritory territory = territoryService.getTerritoryById(addUserModel.getTerritory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Territory not found"));

        /* Create new user */
        Users users = new Users();
        users.setUserName(addUserModel.getUserName());
        users.setEmail(addUserModel.getEmail());
        users.setPassword(new BCryptPasswordEncoder().encode(addUserModel.getPassword()));
        users.setAddedBy(currentAuthorizedUser);
        users.setAddedDate(LocalDateTime.now());
        users.setIsAccountNonExpired(true);
        users.setIsCredentialsNonExpired(true);
        users.setIsAccountNonLocked(true);
        users.setIsEnabled(true);
        users.setIsVerified(true);
        users.setTeritory(territory);

        /* Get permission list by permission ids */
        if (!addUserModel.getPermissions().isEmpty()) {
            List<Permission> permissionList = permissionService.getAppPermissionByIds(addUserModel.getPermissions());
            if (!permissionList.isEmpty()) {
                Set<Permission> permissions = new HashSet<>(permissionList);
                users.setPermissions(permissions);
            }
        }

        /* Get Role list by permission ids */
        List<Role> roleList = roleService.getAppRoleByIds(addUserModel.getRoles());
        if (!roleList.isEmpty()) {
            Set<Role> roles = new HashSet<>(roleList);
            users.setRoles(roles);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please select a roll");
        }

       /* *//* Create new employee department *//*
        EmpDepartment empDepartment = new EmpDepartment();
        empDepartment.setDepartment(department);
        empDepartment.setAddUser(authentication.getName());
        empDepartment.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        empDepartment.setEd(new Date());
        empDepartment.setTd(new Date());

        *//* Create new employee info *//*
        EmpInfo empInfo = new EmpInfo();
        empInfo.setEmpName(addUserModel.getName());
        empInfo.setEmpDepartments(Collections.singletonList(empDepartment));
        empInfo.setUsers(users);
        empInfo.setEmpOfficeId("AKG_" + users.getUserId());
        empInfo.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        empInfo.setAddUser(authentication.getName());

        *//* Create new empDepartment *//*
        EmpDesignation newEmpDesignation = new EmpDesignation();
        newEmpDesignation.setDesignation(designation);
        newEmpDesignation.setAddUser(authentication.getName());
        newEmpDesignation.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        newEmpDesignation.setEd(new Date());
        newEmpDesignation.setEmpInfo(empInfo);

        empInfo.setEmpDesignations(Collections.singletonList(newEmpDesignation));
        empDepartment.setEmpInfo(empInfo);
        users.setEmpInfo(empInfo);
*/
        /*TeritoryUserMap teritoryUserMap = new TeritoryUserMap();
        teritoryUserMap.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        teritoryUserMap.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        teritoryUserMap.setEd(new Date());
        teritoryUserMap.setTd(Date.from(LocalDate.of(2040, 12, 31).atStartOfDay(defaultZoneId).toInstant()));
        teritoryUserMap.setTeritory(territory);
        teritoryUserMap.setUsers(users);

        users.setTeritoryUserMap(teritoryUserMap);*/

        return usersRepository.save(users);
    }

    @Override
    public Optional<Users> getUserById(long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<Users> getUserByUserName(String userName) {
        return usersRepository.findByUserNameIgnoreCase(userName);
    }

    @Override
    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Optional<Users> getUserByTerritory(long territoryId) {
        return usersRepository.findByTeritory_IdAndIsAccountNonExpired(territoryId, true);
    }
}
