package com.carlosjr.statemachinecreditcard.services.components;

import com.carlosjr.statemachinecreditcard.domain.PaymentEvent;
import com.carlosjr.statemachinecreditcard.domain.PaymentState;
import com.carlosjr.statemachinecreditcard.services.PaymentServiceImpl;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class SmGuard {

    public Guard<PaymentState, PaymentEvent> paymentIdGuard(){
        return stateContext -> {
            return stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
        };
    }

}
