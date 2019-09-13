package com.example.petclinic.business;

import com.example.petclinic.model.Speciality;
import com.example.petclinic.model.Vet;
import com.example.petclinic.service.VetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class VetWorkflow {

    private static final Logger log = LoggerFactory.getLogger(PetClinicBusinessWorkflow.class.getName());
    private VetService vetService;

    public VetWorkflow (VetService vetService){
        this.vetService = vetService;
    }

    public void runVetBusiness() {

        // create vets
        Vet vet1 = Vet.builder().withName("SuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();
        Vet vet2 = Vet.builder().withName("SuperDuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).withSpeciality(Speciality.RADIOLOGY).build();
        Vet vet3 = Vet.builder().withName("OutstandingVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();

        // save vets
        vetService.saveVet(vet1);
        vetService.saveVet(vet2);
        vetService.saveVet(vet3);

        // Vet STUFF
        // getALLVets
        List<Vet> vets = vetService.getAllVets();
        vets.forEach(vet -> log.info(vet.toString()));

        // getVetByName
        String searchForVet = "SuperVet";
        List<Vet> SuperVets = vetService.getVetByName(searchForVet);

        AtomicInteger counterVet = new AtomicInteger(1);
        SuperVets.forEach(SuperVet -> {

            StringBuilder sb = new StringBuilder();
            sb.append(searchForVet);
            sb.append(" ");
            sb.append(counterVet.getAndIncrement());
            sb.append(": ");
            sb.append(SuperVet);

            log.info(sb.toString());
        });

        // modify vet
        Vet vetModification = SuperVets.get(0);
        vetModification.setName("testvetty");
        vetService.modifyVet(vetModification);
        log.info(vetService.getVetByName("testvetty").toString());

        // delete vet
        vetService.deleteVet(vetModification);
    }
}
