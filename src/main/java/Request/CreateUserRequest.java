package Request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {

    private String userName;
    private String emailId;
    private String firstName;
    private String lastName;
    private String role;
    private CreateTenantRequest tenant;

}
