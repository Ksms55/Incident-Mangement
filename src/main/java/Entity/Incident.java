package Entity;

import Request.CreateIncidentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", columnDefinition = "CHAR(36) NOT NULL")
    private UUID uuid;

    @Column(name = "incident_type")
    private String incidentType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "severity")
    private String severity;

    @Column(name = "state")
    private String state;

    @Column(name = "device")
    private String device;

    @Column(name = "location")
    private String location;

    @Column(name = "priority_level")
    private String priorityLevel;

    @Column(name = "sha256", columnDefinition = "CHAR(64) NOT NULL")
    private String sha256;

    @Column(name = "date_reported")
    private java.time.LocalDateTime dateReported;

    @Column(name = "date_resolved")
    private java.time.LocalDateTime dateResolved;

    @ManyToOne
    @JoinColumn(name = "reported_by_tenant_id", referencedColumnName = "uuid")
    private Tenant reportedByTenant;

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id", referencedColumnName = "uuid")
    private User assignedToUser;

    @Column(name = "tags", columnDefinition = "TEXT")
    private String tags;

    @Column(name = "escalation_status")
    private String escalationStatus;

    //@Column(name = "attachments", columnDefinition = "JSON")
    //private String attachments;
    @ElementCollection
    @CollectionTable(name = "incident_attachments", joinColumns = @JoinColumn(name = "incident_id"))
    @Column(name = "attachment")
    private List<String> attachments;


    @Column(name = "resolution_summary", columnDefinition = "TEXT")
    private String resolutionSummary;

    @Column(name = "impact_level")
    private String impactLevel;

    // Constructor to populate the incident fields from a CreateIncidentRequest
    public Incident(CreateIncidentRequest createIncidentRequest) {
        this.uuid = UUID.randomUUID();
        this.incidentType = createIncidentRequest.getIncidentType();
        this.description = createIncidentRequest.getDescription();
        this.severity = createIncidentRequest.getSeverity();
        this.state = createIncidentRequest.getState();
        this.device = createIncidentRequest.getDevice();
        this.location = createIncidentRequest.getLocation();
        this.priorityLevel = createIncidentRequest.getPriorityLevel();
        this.sha256 = UUID.randomUUID().toString();
        this.tags = createIncidentRequest.getTags();
        this.escalationStatus = createIncidentRequest.getEscalationStatus();
        this.attachments = Collections.singletonList(createIncidentRequest.getAttachments().toString());
        this.resolutionSummary = createIncidentRequest.getResolutionSummary();
        this.impactLevel = createIncidentRequest.getImpactLevel();
        this.dateReported = java.time.LocalDateTime.now();
        /*this.reportedByTenant = new Tenant();
        this.reportedByTenant.setUuid(createIncidentRequest.getReportedByTenantUuid());

        this.assignedToUser = new User();
        this.assignedToUser.setUuid(createIncidentRequest.getAssignedToUserUuid());*/
    }

}
