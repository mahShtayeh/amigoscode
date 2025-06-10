package com.amigoscode.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TO_CUSTOMER_ID")
    private Long toCustomerId;

    @Column(name = "TO_CUSTOMER_EMAIL")
    private String toCustomerEmail;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "SENT_AT")
    private LocalDateTime sentAt;
}