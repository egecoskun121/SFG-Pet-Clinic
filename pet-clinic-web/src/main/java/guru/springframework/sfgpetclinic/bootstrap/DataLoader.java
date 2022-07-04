package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.sfgpetclinic.services.OwnerService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType= petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("Ege");
        owner1.setLastName("Coskun");
        owner1.setAddress("Tuna mah");
        owner1.setCity("Izmir");
        owner1.setTelephone("5070773735");

        Pet egesPet= new Pet();
        egesPet.setPetType(savedDogType);
        egesPet.setOwner(owner1);
        egesPet.setBirthDate(LocalDate.now());
        egesPet.setName("Roi");
        owner1.getPets().add(egesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Hazal");
        owner2.setLastName("Coskun");
        owner2.setAddress("Tuna mah");
        owner2.setCity("Izmir");
        owner2.setTelephone("515899125");

        Pet hazalsCat= new Pet();
        hazalsCat.setName("Boncuk");
        hazalsCat.setOwner(owner2);
        hazalsCat.setBirthDate(LocalDate.now());
        hazalsCat.setPetType(savedCatType);
        owner2.getPets().add(hazalsCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Fatih");
        vet1.setLastName("kek");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tommy");
        vet2.setLastName("Egan");

        vetService.save(vet2);

        System.out.println("Loaded vets..");


    }
}
