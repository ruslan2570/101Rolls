package ru.rolls.server.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private IssueType issueType;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryAddress deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee deliverer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Column(length = 5)
    private String flat;


}
