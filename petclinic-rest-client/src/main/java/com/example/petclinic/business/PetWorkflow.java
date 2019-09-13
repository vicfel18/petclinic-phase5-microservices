package com.example.petclinic.business;

import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PetWorkflow {

    private PetService petService;

    private static final Logger log = LoggerFactory.getLogger(PetClinicBusinessWorkflow.class.getName());

    public PetWorkflow (PetService petService){
        this.petService = petService;
    }

    public void runPetBusiness () {

        // Pets for Homer
        Pet pet1 = Pet.builder().withName("Strangles").withBirthDate(new Date()).withPetType(PetType.SNAKE).build();
        Pet pet2 = Pet.builder().withName("Mojo").withBirthDate(new Date()).withPetType(PetType.MONKEY).build();
        Pet pet3 = Pet.builder().withName("Pinchy").withBirthDate(new Date()).withPetType(PetType.LOBSTER).build();
        Pet pet4 = Pet.builder().withName("Plopper").withBirthDate(new Date()).withPetType(PetType.PIG).build();

        // Pets for Marge
        Pet pet5 = Pet.builder().withName("Greyhound").withBirthDate(new Date()).withPetType(PetType.DOG).build();

        // Pets for Bart
        Pet pet6 = Pet.builder().withName("Laddie").withBirthDate(new Date()).withPetType(PetType.DOG).build();
        Pet pet7 = Pet.builder().withName("Santa's Little Helper").withBirthDate(new Date()).withPetType(PetType.DOG).build();
        Pet pet8 = Pet.builder().withName("Stampy").withBirthDate(new Date()).withPetType(PetType.ELEPHANT).build();
        Pet pet9 = Pet.builder().withName("Duncan").withBirthDate(new Date()).withPetType(PetType.HORSE).build();

        // Pets for Lisa
        Pet pet10 = Pet.builder().withName("Nibbles").withBirthDate(new Date()).withPetType(PetType.HAMPSTER).build();
        Pet pet11 = Pet.builder().withName("Chirpy Boy").withBirthDate(new Date()).withPetType(PetType.LIZARD).build();
        Pet pet12 = Pet.builder().withName("Bart Junior").withBirthDate(new Date()).withPetType(PetType.LIZARD).build();
        Pet pet13 = Pet.builder().withName("Snowball IV").withBirthDate(new Date()).withPetType(PetType.CAT).build();
        Pet pet14 = Pet.builder().withName("Princess").withBirthDate(new Date()).withPetType(PetType.HORSE).build();

        // Save pets
        petService.savePet(pet1);
        petService.savePet(pet2);
        petService.savePet(pet3);
        petService.savePet(pet4);
        petService.savePet(pet5);
        petService.savePet(pet6);
        petService.savePet(pet7);
        petService.savePet(pet8);
        petService.savePet(pet9);
        petService.savePet(pet10);
        petService.savePet(pet11);
        petService.savePet(pet12);
        petService.savePet(pet13);
        petService.savePet(pet14);

        // Pet WORKFLOW
        // getALLPets
        List<Pet> pets = petService.getAllPets();
        pets.forEach(pet -> log.info(pet.toString()));

        // getPetByName
        String searchForPet = "Strangles";
        List<Pet> strangles = petService.getPetByName(searchForPet);

        AtomicInteger counterPet = new AtomicInteger(1);
        strangles.forEach(strangle -> {

            StringBuilder sb = new StringBuilder();
            sb.append(searchForPet);
            sb.append(" ");
            sb.append(counterPet.getAndIncrement());
            sb.append(": ");
            sb.append(strangle);

            log.info(sb.toString());
        });

        // modify pet
        Pet petModification = strangles.get(0);
        petModification.setName("strangular");
        petService.modifyPet(petModification);
        log.info(petService.getPetByName("strangular").toString());

        // delete pet
        petService.deletePet(petModification);
    }
}
