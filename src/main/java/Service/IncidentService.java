package Service;

import Entity.Incident;
import Entity.Tenant;
import Entity.User;
import Repository.IncidentRepository;
import Repository.TenantRepository;
import Repository.UserRepository;
import Request.CreateIncidentRequest;
import Request.UpdateIncidentRequest;
import Response.IncidentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class IncidentService {

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    UserRepository userRepository;

    // Method to get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Method to create an incident
    public IncidentResponse createIncident(CreateIncidentRequest request) {
        Tenant reportedByTenant = tenantRepository.findByUuid(request.getReportedByTenantUuid()).orElse(null);
        User assignedToUser = userRepository.findByUuid(request.getAssignedToUserUuid()).orElse(null);
        if (reportedByTenant == null || assignedToUser == null) {
            throw new RuntimeException("Tenant or User not found");
        }
        Incident incident = new Incident(request);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String attachmentsJson = objectMapper.writeValueAsString(request.getAttachments());
            incident.setAttachments(Collections.singletonList(attachmentsJson));
        } catch (Exception e) {
            throw new RuntimeException("Error converting attachments to JSON", e);
        }
        incident.setReportedByTenant(reportedByTenant);
        incident.setAssignedToUser(assignedToUser);
        incidentRepository.save(incident);
        return new IncidentResponse(incident);
    }

    //Method to Update an incident(s)
    public Incident updateIncident(UpdateIncidentRequest updateIncidentRequest) {
        Incident incident = incidentRepository.findById(updateIncidentRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Incident with id " + updateIncidentRequest.getId() + " not found"));
        if (updateIncidentRequest.getIncidentType() != null) {
            incident.setIncidentType(updateIncidentRequest.getIncidentType());
        }
        if (updateIncidentRequest.getDescription() != null) {
            incident.setDescription(updateIncidentRequest.getDescription());
        }
        if (updateIncidentRequest.getSeverity() != null) {
            incident.setSeverity(updateIncidentRequest.getSeverity());
        }
        if (updateIncidentRequest.getState() != null) {
            incident.setState(updateIncidentRequest.getState());
        }
        if (updateIncidentRequest.getDevice() != null) {
            incident.setDevice(updateIncidentRequest.getDevice());
        }
        if (updateIncidentRequest.getLocation() != null) {
            incident.setLocation(updateIncidentRequest.getLocation());
        }
        if (updateIncidentRequest.getPriorityLevel() != null) {
            incident.setPriorityLevel(updateIncidentRequest.getPriorityLevel());
        }
        if (updateIncidentRequest.getTags() != null) {
            incident.setTags(updateIncidentRequest.getTags());
        }
        if (updateIncidentRequest.getEscalationStatus() != null) {
            incident.setEscalationStatus(updateIncidentRequest.getEscalationStatus());
        }
        if (updateIncidentRequest.getAttachments() != null && !updateIncidentRequest.getAttachments().isEmpty()) {
            incident.setAttachments(updateIncidentRequest.getAttachments());
        }
        if (updateIncidentRequest.getResolutionSummary() != null) {
            incident.setResolutionSummary(updateIncidentRequest.getResolutionSummary());
        }
        if (updateIncidentRequest.getImpactLevel() != null) {
            incident.setImpactLevel(updateIncidentRequest.getImpactLevel());
        }
        if (updateIncidentRequest.getReportedByTenantUuid() != null) {
            Tenant tenant = tenantRepository.findByUuid(updateIncidentRequest.getReportedByTenantUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Tenant not found for uuid: " + updateIncidentRequest.getReportedByTenantUuid()));
            incident.setReportedByTenant(tenant);
        }
        if (updateIncidentRequest.getAssignedToUserUuid() != null) {
            User user = userRepository.findByUuid(updateIncidentRequest.getAssignedToUserUuid())
                    .orElseThrow(() -> new IllegalArgumentException("User not found for uuid: " + updateIncidentRequest.getAssignedToUserUuid()));
            incident.setAssignedToUser(user);
        }
        return incidentRepository.save(incident);
    }

    //Method to delete the incident
    @Transactional
    @Modifying
    public String deleteIncident(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incident with id " + id + " not found"));
        incidentRepository.delete(incident);
        return "";
    }

}
