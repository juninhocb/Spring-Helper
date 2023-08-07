package com.example.carlosjr.mapstruct.bootstrap;

import com.example.carlosjr.mapstruct.model.Address;
import com.example.carlosjr.mapstruct.model.Person;
import com.example.carlosjr.mapstruct.repositories.AddressRepository;
import com.example.carlosjr.mapstruct.repositories.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResourceSimulator {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    @PostConstruct
    public void tryToCreateNewPerson(){
        Address addressToPersist;
        String bodyRequestCity = "Blumenau";
        Optional<Address> addressOpt = addressRepository
                .findById(bodyRequestCity);

        if (addressOpt.isEmpty()){
            addressToPersist = Address.builder()
                    .city(bodyRequestCity)
                    .province("Undefined").build();
        } else {
            addressToPersist = addressOpt.get();
        }

        Person person = Person.builder()
                .id(6L)
                .name("Anna Brown")
                .age(29)
                .isAlive(true)
                .address(addressToPersist)
                .document(UUID.randomUUID()).build();

        personRepository.save(person);
    }

}
