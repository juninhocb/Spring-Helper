package com.example.springgraphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

@SpringBootApplication
public class SpringGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlApplication.class, args);
    }

}

@Configuration
@RequiredArgsConstructor
class GraphQLConfig{

    private final CustomerService customerService;

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return new RuntimeWiringConfigurer() {
            @Override
            public void configure(RuntimeWiring.Builder builder) {
                builder.type("Customer", wiring -> wiring.dataFetcher(
                 "profile", environment -> {
                    return customerService.getProfile(environment.getSource());}
                ));
                builder.type("Query", new UnaryOperator<TypeRuntimeWiring.Builder>() {
                    @Override
                    public TypeRuntimeWiring.Builder apply(TypeRuntimeWiring.Builder wiring) {
                        return wiring
                                .dataFetcher("customerById", environment -> customerService.getById(Integer.parseInt(environment.getArgument("id"))))
                                .dataFetcher("customers", environment -> customerService.getAll());
                    }
                });
            }
        };
    }
}

@Builder
record Customer(Integer id, String name){}

@Builder
record Profile(Integer id, Integer customerId){}

@Service
class CustomerService {
    Profile getProfile(Customer customer){
        return Profile.builder().id(customer.id()).customerId(customer.id()).build();
    }
    Customer getById(Integer id){
        return new Customer(id, Math.random() > 0.5 ? "John Doe" : "Peter Black");
    }

    Collection<Customer> getAll(){
        return List.of(Customer.builder().id(1).name("John Doe").build(), Customer.builder().id(2).name("Peter Black").build());
    };
}
