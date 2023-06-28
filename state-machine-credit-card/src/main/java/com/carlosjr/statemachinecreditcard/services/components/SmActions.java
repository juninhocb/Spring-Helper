package com.carlosjr.statemachinecreditcard.services.components;

import com.carlosjr.statemachinecreditcard.domain.PaymentEvent;
import com.carlosjr.statemachinecreditcard.domain.PaymentState;
import com.carlosjr.statemachinecreditcard.services.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SmActions {
    public Action<PaymentState, PaymentEvent> preAuthAction(){
        return stateContext -> {
            System.out.println("PreAuth was called!");
            if (new Random().nextInt(10) < 8){
                System.out.println("Approved");
                sendMessageToMachine(stateContext, PaymentEvent.PRE_AUTH_APPROVED);
            } else {
                System.out.println("Declined! No credit");
                sendMessageToMachine(stateContext, PaymentEvent.PRE_AUTH_DECLINED);
            }
        };
    }

    public Action<PaymentState, PaymentEvent> authorizeAction(){
        return stateContext -> {
            System.out.println("Auth was called!");
            if (new Random().nextInt(10) < 8){
                System.out.println("Approved");
                sendMessageToMachine(stateContext, PaymentEvent.AUTH_APPROVED);
            } else {
                System.out.println("Declined!");
                sendMessageToMachine(stateContext, PaymentEvent.AUTH_DECLINED);
            }
        };
    }

    public Action<PaymentState, PaymentEvent> preApprovedAction(){
        return stateContext -> {
            System.out.println("Pre approved!");
        };
    }

    public Action<PaymentState, PaymentEvent> preDeclinedAction(){
        return stateContext -> {
            System.out.println("Pre declined!");
        };
    }

    public Action<PaymentState, PaymentEvent> approvedAction(){
        return stateContext -> {
            System.out.println("Auth Approved!");
        };
    }

    public Action<PaymentState, PaymentEvent> declinedAction(){
        return stateContext -> {
            System.out.println("Auth Declined!");
        };
    }


    private void sendMessageToMachine(StateContext stateContext, PaymentEvent payload){
        stateContext.getStateMachine().sendEvent(MessageBuilder
                .withPayload(payload)
                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                .build());

    }
}
