package Controller;


import Entity.Incident;
import Request.CreateIncidentRequest;
import Request.UpdateIncidentRequest;
import Response.IncidentResponse;
import Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/incidents/")
public class IncidentController {

    @Autowired
    IncidentService incidentService;

    //Method to Get All Incidents
    @GetMapping("/getAll")
    public List<IncidentResponse> getAllIncidents() {
        List<Incident> incidentList = incidentService.getAllIncidents();
        List<IncidentResponse> incidentResponseList = new ArrayList<IncidentResponse>();

        incidentList.stream().forEach(incident -> {
            incidentResponseList.add(new IncidentResponse(incident));
        });

        return incidentResponseList;
    }

    //Method to Create Incident
    @PostMapping("/create")
    public IncidentResponse createIncident(@RequestBody CreateIncidentRequest createIncidentRequest) {
        return incidentService.createIncident(createIncidentRequest);
    }

    //Method to Update Incident(s)
    @PutMapping("/update")
    public IncidentResponse updateIncident(@RequestBody UpdateIncidentRequest updateIncidentRequest){
        Incident incident = incidentService.updateIncident(updateIncidentRequest);
        return new IncidentResponse(incident);
    }

    //Method to Delete Incident
    @DeleteMapping("/delete")
    public String deleteIncident(@RequestParam Long id) {
        //System.out.println("1 incident deleted successfully with id = " +id);
        return incidentService.deleteIncident(id) + "1 incident deleted successfully with id =" +id;
    }


}
