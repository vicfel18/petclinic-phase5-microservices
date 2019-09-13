package com.example.petclinic.business;

import com.example.petclinic.model.Visit;
import com.example.petclinic.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class VisitWorkflow {

    private static final Logger log = LoggerFactory.getLogger(PetClinicBusinessWorkflow.class.getName());
    private VisitService visitService;

    public VisitWorkflow (VisitService visitService){
        this.visitService = visitService;
    }

    public void runVisitBusiness() {

        // Create Visit
        Visit visit1 = Visit.builder().withDateOfVisit(new Date()).withDescription("Nice Visit!").build();
        Visit visit2 = Visit.builder().withDateOfVisit(new Date()).withDescription("Bad Visit!").build();
        Visit visit3 = Visit.builder().withDateOfVisit(new Date()).withDescription("Bad Visit!").build();
        Visit visit4 = Visit.builder().withDateOfVisit(new Date()).withDescription("Bad Visit!").build();

        // save visits
        visitService.saveVisit(visit1);
        visitService.saveVisit(visit2);
        visitService.saveVisit(visit3);
        visitService.saveVisit(visit4);

        // Visit WORKFLOW
        // getALLVisits
        List<Visit> visits = visitService.getAllVisits();
        visits.forEach(visit -> log.info(visit.toString()));

        // modify visit
        Visit visitModification = visits.get(0);
        visitModification.setDescription("changeddescription");
        visitService.modifyVisit(visitModification);
        log.info(visitService.getAllVisits().toString());

        // delete visit
        visitService.deleteVisit(visitModification);

    }
}
