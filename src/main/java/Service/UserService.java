package Service;

import Entity.Tenant;
import Entity.User;
import Repository.TenantRepository;
import Repository.UserRepository;
import Request.CreateTenantRequest;
import Request.CreateUserRequest;
import Request.UpdateTenantRequest;
import Request.UpdateUserRequest;
import Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TenantRepository tenantRepository;

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Method to create User
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        Tenant tenant = createTenant(createUserRequest.getTenant());
        User user = new User(createUserRequest);
        user.setTenant(tenant);
        userRepository.save(user);
        return new UserResponse(user);
    }

    private Tenant createTenant(CreateTenantRequest createTenantRequest) {
        Tenant tenant = new Tenant();
        tenant.setUuid(UUID.randomUUID());
        tenant.setName(createTenantRequest.getName());
        tenant.setDescription(createTenantRequest.getDescription());
        tenant.setRoles(createTenantRequest.getRoles());
        tenant.setParentSocTenantId(createTenantRequest.getParentSocTenantId());
        tenant.setUserName(createTenantRequest.getUserName());
        tenant.setPassword(createTenantRequest.getPassword());
        tenant.setTenantStatus(createTenantRequest.getTenantStatus() != null ? createTenantRequest.getTenantStatus() : "active");
        tenantRepository.save(tenant);
        return tenant;
    }

    //Method to Update User(s)
    public User updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(updateUserRequest.getUserName()!= null && !updateUserRequest.getUserName().isEmpty()){
            user.setUserName((updateUserRequest.getUserName()));
        }
        if(updateUserRequest.getEmailId()!= null && !updateUserRequest.getEmailId().isEmpty()){
            user.setEmailId((updateUserRequest.getEmailId()));
        }
        if(updateUserRequest.getFirstName()!= null && !updateUserRequest.getFirstName().isEmpty()){
            user.setFirstName((updateUserRequest.getFirstName()));
        }
        if(updateUserRequest.getLastName()!= null && !updateUserRequest.getLastName().isEmpty()){
            user.setLastName((updateUserRequest.getLastName()));
        }
        if(updateUserRequest.getRole()!= null && !updateUserRequest.getRole().isEmpty()){
            user.setRole((updateUserRequest.getRole()));
        }
        if (updateUserRequest.getUpdateTenantRequest() != null) {
            UpdateTenantRequest tenantRequest = updateUserRequest.getUpdateTenantRequest();
            Tenant tenant = user.getTenant();
            if (tenant == null) {
                throw new RuntimeException("Tenant not found for the user");
            }
            if (tenantRequest.getName() != null && !tenantRequest.getName().isEmpty()) {
                tenant.setName(tenantRequest.getName());
            }
            if (tenantRequest.getDescription() != null && !tenantRequest.getDescription().isEmpty()) {
                tenant.setDescription(tenantRequest.getDescription());
            }
            if (tenantRequest.getRoles() != null && !tenantRequest.getRoles().isEmpty()) {
                tenant.setRoles(tenantRequest.getRoles());
            }
            if (tenantRequest.getUserName() != null && !tenantRequest.getUserName().isEmpty()) {
                tenant.setUserName(tenantRequest.getUserName());
            }
            if (tenantRequest.getPassword() != null && !tenantRequest.getPassword().isEmpty()) {
                tenant.setPassword(tenantRequest.getPassword());
            }
            tenantRepository.save(tenant);
            user.setTenant(tenant);
        }
        userRepository.save(user);
        return user;
    }

    //Method to Delete User
    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
        tenantRepository.deleteById(id);
    }

}
