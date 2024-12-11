package Response;

import Entity.Incident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class IncidentResponse {
    private Long id;
    private UUID uuid;
    private String incidentType;
    private String description;
    private String severity;
    private String state;
    private String device;
    private String location;
    private String priorityLevel;
    private String sha256;
    private String tags;
    private String escalationStatus;
    private List<String> attachments;
    private String resolutionSummary;
    private String impactLevel;
    private LocalDateTime dateReported;
    private LocalDateTime dateResolved;
    private UUID reportedByTenantUuid;
    private UUID assignedToUserUuid;

    public IncidentResponse(Incident incident) {
        this.id = incident.getId();
        this.uuid = incident.getUuid();
        this.incidentType = incident.getIncidentType();
        this.description = incident.getDescription();
        this.severity = incident.getSeverity();
        this.state = incident.getState();
        this.device = incident.getDevice();
        this.location = incident.getLocation();
        this.priorityLevel = incident.getPriorityLevel();
        this.sha256 = incident.getSha256();
        this.tags = incident.getTags();
        this.escalationStatus = incident.getEscalationStatus();
        this.attachments = incident.getAttachments();
        this.resolutionSummary = incident.getResolutionSummary();
        this.impactLevel = incident.getImpactLevel();
        this.dateReported = incident.getDateReported();
        this.dateResolved = incident.getDateResolved();
        if (incident.getReportedByTenant() != null) {
            this.reportedByTenantUuid = incident.getReportedByTenant().getUuid();
        }
        if (incident.getAssignedToUser() != null) {
            this.assignedToUserUuid = incident.getAssignedToUser().getUuid();
        }
    }
}
