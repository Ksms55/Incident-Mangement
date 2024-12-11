package Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateIncidentRequest {
    private String incidentType;
    private String description;
    private String severity;
    private String state;
    private String device;
    private String location;
    private String priorityLevel;
    private String tags;
    private String escalationStatus;
    private List<String> attachments;
    private String resolutionSummary;
    private String impactLevel;
    private UUID reportedByTenantUuid;
    private UUID assignedToUserUuid;
}
