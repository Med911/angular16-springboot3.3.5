package net.mohamed.demospringang.dtos;

import lombok.*;
import net.mohamed.demospringang.entities.PaymentType;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDTO {
    private double amount;
    private PaymentType type;
    private LocalDate date;
    private String studentCode ;
}
