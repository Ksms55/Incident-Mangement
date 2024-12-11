package Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserRequest{
    private Long id;
    private String userName;
    private String emailId;
    private String firstName;
    private String lastName;
    private String role;
    private UpdateTenantRequest updateTenantRequest;
}
