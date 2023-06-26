package com.carlosjr.relationship;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final Pojo1Repository pojo1Repository;
    private final Pojo2Repository pojo2Repository;
    private final Pojo3Repository pojo3Repository;
    private final Pojo4Repository pojo4Repository;
    private final Pojo5Repository pojo5Repository;

    @Override
    public void run(String... args) throws Exception {

        Pojo1 p11 = Pojo1.builder().name("P11").build();
        Pojo1 p12 = Pojo1.builder().name("P12").build();
        Pojo1 p13 = Pojo1.builder().name("P13").build();

        Pojo2 p21 = Pojo2.builder().name("P21").pojos1(Arrays.asList(p11,p12,p13).stream().collect(Collectors.toSet())).build();
        Pojo2 p22 = Pojo2.builder().name("P22").pojos1(Arrays.asList(p11,p12).stream().collect(Collectors.toSet())).build();
        Pojo2 p23 = Pojo2.builder().name("P23").pojos1(Arrays.asList(p11).stream().collect(Collectors.toSet())).build();

        Pojo5 p51 = Pojo5.builder().name("p51").build();
        Pojo5 p52 = Pojo5.builder().name("p52").build();
        Pojo5 p53 = Pojo5.builder().name("p53").build();

        Pojo4 p41 = Pojo4.builder().name("p41").pojo5(p51).build();
        Pojo4 p42 = Pojo4.builder().name("p42").pojo5(p52).build();
        Pojo4 p43 = Pojo4.builder().name("p43").pojo5(p53).build();
        Pojo4 p44 = Pojo4.builder().name("p44").pojo5(p53).build();

        Pojo3 p31 = Pojo3.builder().name("p31").pojo4(p41).pojo1(p11).build();
        Pojo3 p32 = Pojo3.builder().name("p32").pojo4(p42).pojo1(p12).build();
        Pojo3 p33 = Pojo3.builder().name("p33").pojo4(p43).pojo1(p13).build();


        pojo1Repository.saveAll(Arrays.asList(p11,p12,p13));
        pojo2Repository.saveAll(Arrays.asList(p21,p22,p23));
        pojo5Repository.saveAll(Arrays.asList(p51,p52,p53));
        pojo4Repository.saveAll(Arrays.asList(p41,p42,p43,p44));
        pojo3Repository.saveAll(Arrays.asList(p31,p32,p33));

    }
}
