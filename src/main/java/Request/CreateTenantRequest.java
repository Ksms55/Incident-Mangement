package Request;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateTenantRequest {

    private String name;
    private String description;
    private String userName;
    private String password;
    private String roles;
    private UUID parentSocTenantId;
    private String tenantStatus;

}
