package com.example.carlosjr.mapstruct.repositories;

import com.example.carlosjr.mapstruct.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
