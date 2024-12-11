package Response;

import Entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String roles;
    private UUID parentSocTenantId;
    private String tenantStatus;
    private String userName;
    private String password;


    public TenantResponse(Tenant tenant) {
        this.id = tenant.getId();
        this.uuid = tenant.getUuid();
        this.name = tenant.getName();
        this.description = tenant.getDescription();
        this.roles = tenant.getRoles();
        this.parentSocTenantId = tenant.getParentSocTenantId();
        this.tenantStatus = tenant.getTenantStatus();
        this.userName = tenant.getUserName();
        this.password = tenant.getPassword();
    }
}
