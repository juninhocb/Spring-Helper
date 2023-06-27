package com.carlosjr.statemachinecreditcard.repository;

import com.carlosjr.statemachinecreditcard.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
