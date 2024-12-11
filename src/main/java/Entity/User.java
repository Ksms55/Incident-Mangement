package Entity;

import Request.CreateUserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tenant_user")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "uuid", columnDefinition = "CHAR(36) NOT NULL")
        private UUID uuid;

        @Column(name = "username")
        private String userName;

        @Column(name = "email")
        private String emailId;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @ManyToOne
        @JoinColumn(name = "tenant_id", referencedColumnName = "uuid")
        private Tenant tenant;

        @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime createdAt = LocalDateTime.now();

        @Column(name = "role")
        private String role;

        // Constructor to populate the user fields from a CreateUserRequest
        public User(CreateUserRequest createUserRequest) {
                this.uuid = UUID.randomUUID();
                this.userName = createUserRequest.getUserName();
                this.emailId = createUserRequest.getEmailId();
                this.firstName = createUserRequest.getFirstName();
                this.lastName = createUserRequest.getLastName();
                this.role = createUserRequest.getRole();
                this.createdAt = LocalDateTime.now();
        }
}
