package com.carlosjr.relationshipstress.repository;

import com.carlosjr.relationshipstress.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
