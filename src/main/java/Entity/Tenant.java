package Entity;

import Request.CreateTenantRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Tenant") // Table name adjusted to match the SQL query
public class Tenant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "uuid", columnDefinition = "CHAR(36) NOT NULL")
        private UUID uuid;

        @Column(name = "name")
        private String name;

        @Column(name = "description", columnDefinition="TEXT")
        private String description;

        @Column(name = "roles", columnDefinition="TEXT")
        private String roles;

        @Column(name = "parent_soc_tenant_id", columnDefinition = "CHAR(36) NOT NULL") // Adjusted to match the SQL query
        private UUID parentSocTenantId;

        @Column(name ="username", nullable = false)
        private String userName;

        @Column(name = "password")
        private String password;

        @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<User> users;

        @Column(name = "tenant_status", columnDefinition = "VARCHAR(50) DEFAULT 'active'")
        private String tenantStatus;

        public Tenant(CreateTenantRequest createTenantRequest) {
                this.uuid = UUID.randomUUID();
                this.name = createTenantRequest.getName();
                this.description = createTenantRequest.getDescription();
                this.roles = createTenantRequest.getRoles();
                this.parentSocTenantId = createTenantRequest.getParentSocTenantId();
                this.userName = createTenantRequest.getUserName();
                this.password = createTenantRequest.getPassword();
                this.tenantStatus = createTenantRequest.getTenantStatus();
        }
}
