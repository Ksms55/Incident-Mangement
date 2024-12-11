package Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTenantRequest {
    private Long id;
    private String name;
    private String description;
    private String roles;
    //private UUID parentSocTenantId;
    private String userName;
    private String password;
    //private String tenantStatus;
}
