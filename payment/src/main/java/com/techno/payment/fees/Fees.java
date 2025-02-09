package com.techno.payment.fees;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private String studentName;
    private String className;
    private String feesType;
    private String duration;
    private String datePayment;
    private String paymenType;
    private String invoiceNo;
    private String status;
    private String amount;
    private  String details;

}
