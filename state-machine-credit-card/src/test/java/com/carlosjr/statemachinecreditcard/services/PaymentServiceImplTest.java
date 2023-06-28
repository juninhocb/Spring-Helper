package com.carlosjr.statemachinecreditcard.services;

import com.carlosjr.statemachinecreditcard.domain.Payment;
import com.carlosjr.statemachinecreditcard.domain.PaymentEvent;
import com.carlosjr.statemachinecreditcard.domain.PaymentState;
import com.carlosjr.statemachinecreditcard.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentRepository paymentRepository;
    private Payment payment;
    @BeforeEach
    void setUp() {
        payment  = Payment.builder()
                .amount(new BigDecimal("12.99")).build();
    }

    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        assertThat(savedPayment.getState()).isEqualTo(PaymentState.NEW);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        Payment preAuthedPayment = paymentRepository.findById(savedPayment.getId()).get();

        assertThat(sm.getState().getId()).isEqualTo(PaymentState.PRE_AUTH);

        System.out.println(preAuthedPayment);

    }

    @Test
    @RepeatedTest(10)
    void authorize(){
        Payment savedPayment = paymentService.newPayment(payment);

        assertThat(savedPayment.getState()).isEqualTo(PaymentState.NEW);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        assertThat(sm.getState().getId()).isEqualTo(PaymentState.PRE_AUTH);

        StateMachine<PaymentState, PaymentEvent> sm2 = paymentService.authorizePayment(savedPayment.getId());

        System.out.println(sm2.getState().getId());

        Payment authedPayment = paymentRepository.findById(savedPayment.getId()).get();

        System.out.println(authedPayment);

        assertThat(sm2.getState().getId()).isEqualTo(PaymentState.AUTH);



    }
}