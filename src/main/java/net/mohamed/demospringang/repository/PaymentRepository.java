package net.mohamed.demospringang.repository;

import net.mohamed.demospringang.entities.Payment;
import net.mohamed.demospringang.entities.PaymentStatus;
import net.mohamed.demospringang.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String Code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
