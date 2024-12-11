package Response;

import Entity.Tenant;
import Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String uuid;
    private String userName;
    private String emailId;
    private String firstName;
    private String lastName;
    private String role;
    private String createdAt;
    private TenantResponse tenant;

    public UserResponse(User user) {
        this.id = user.getId();
        this.uuid = user.getUuid().toString(); // Convert UUID to String
        this.userName = user.getUserName();
        this.emailId = user.getEmailId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt().toString(); // Convert LocalDateTime to String
        if (user.getTenant() != null) {
            Tenant tenant = user.getTenant();
            this.tenant = new TenantResponse(tenant);
        }
    }
}
