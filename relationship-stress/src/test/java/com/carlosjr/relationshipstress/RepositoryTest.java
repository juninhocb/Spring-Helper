package com.carlosjr.relationshipstress;

import com.carlosjr.relationshipstress.models.User;
import com.carlosjr.relationshipstress.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.carlosjr.relationshipstress.boostrap"})
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Commit
    @Order(1)
    @Test
    void createNewUser() {
        long beforeCount = userRepository.count();
        userRepository.save(User.builder().name("Test user").build());
        long afterCount = userRepository.count();
        assertThat(beforeCount).isLessThan(afterCount);
    }

    @Order(2)
    @Test
    void verifyPersistedData(){
        long count = userRepository.count();
        assertThat(count).isEqualTo(4);
    }

}
