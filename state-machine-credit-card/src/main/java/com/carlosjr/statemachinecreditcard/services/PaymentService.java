package com.carlosjr.statemachinecreditcard.services;

import com.carlosjr.statemachinecreditcard.domain.Payment;
import com.carlosjr.statemachinecreditcard.domain.PaymentEvent;
import com.carlosjr.statemachinecreditcard.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {

    Payment newPayment(Payment payment);
    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);


}
