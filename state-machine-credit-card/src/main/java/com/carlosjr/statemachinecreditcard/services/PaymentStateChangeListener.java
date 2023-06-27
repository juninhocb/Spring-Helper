package com.carlosjr.statemachinecreditcard.services;

import com.carlosjr.statemachinecreditcard.domain.Payment;
import com.carlosjr.statemachinecreditcard.domain.PaymentEvent;
import com.carlosjr.statemachinecreditcard.domain.PaymentState;
import com.carlosjr.statemachinecreditcard.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PaymentStateChangeListener extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {

    private final PaymentRepository paymentRepository;

    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message, Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine) {
        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1L)))
                    .ifPresent(paymentId -> {
                        Payment payment = paymentRepository.findById(paymentId).get();
                        payment.setState(state.getId());
                        paymentRepository.save(payment);
                    });
        });
    }
}
