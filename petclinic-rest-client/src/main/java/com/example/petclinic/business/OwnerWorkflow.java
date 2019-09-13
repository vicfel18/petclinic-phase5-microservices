package com.example.petclinic.business;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OwnerWorkflow {

    private static final Logger log = LoggerFactory.getLogger(OwnerWorkflow.class.getName());
    private OwnerService ownerService;

    public OwnerWorkflow(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    public void runOwnerBusiness () {

        // Create Owners
        Owner owner1 = Owner.builder().withName("Homer Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner2 = Owner.builder().withName("Marge Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner3 = Owner.builder().withName("Bart Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner4 = Owner.builder().withName("Lisa Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();

        // saveOwner
        ownerService.saveOwner(owner1);
        ownerService.saveOwner(owner2);
        ownerService.saveOwner(owner3);
        ownerService.saveOwner(owner4);

        // Owner WORKFLOW
        // getAllOwners
        List<Owner> owners = ownerService.getAllOwners();
        owners.forEach(owner -> log.info(owner.toString()));

        // getOwnerByName
        String searchFor = "Homer Simpson";
        List<Owner> homers = ownerService.getOwnerByName(searchFor);

        AtomicInteger counter = new AtomicInteger(1);
        homers.forEach(homer -> {

            StringBuilder sb = new StringBuilder();
            sb.append(searchFor);
            sb.append(" ");
            sb.append(counter.getAndIncrement());
            sb.append(": ");
            sb.append(homer);

            log.info(sb.toString());
        });

        // modify owner
        Owner ownerModification = homers.get(0);
        ownerModification.setName("Homerus");
        ownerService.modifyOwner(ownerModification);
        log.info(ownerService.getOwnerByName("Homerus").toString());

        // delete owner
        ownerService.deleteOwner(ownerModification);
    }
}
