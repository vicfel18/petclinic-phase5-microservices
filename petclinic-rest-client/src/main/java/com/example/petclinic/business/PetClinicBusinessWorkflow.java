package com.example.petclinic.business;
import org.springframework.stereotype.Component;

@Component
public class PetClinicBusinessWorkflow {

    OwnerWorkflow ownerWorkflow;
    PetWorkflow petWorkflow;
    VetWorkflow vetWorkflow;
    VisitWorkflow visitWorkflow;

    public PetClinicBusinessWorkflow(OwnerWorkflow ownerWorkflow, PetWorkflow petWorkflow, VetWorkflow vetWorkflow,VisitWorkflow visitWorkflow){
        this.ownerWorkflow = ownerWorkflow;
        this.petWorkflow = petWorkflow;
        this.vetWorkflow = vetWorkflow;
        this.visitWorkflow = visitWorkflow;
    }

    public void runBusiness() {

        ownerWorkflow.runOwnerBusiness();
        petWorkflow.runPetBusiness();
        visitWorkflow.runVisitBusiness();
        vetWorkflow.runVetBusiness();

    }
}
