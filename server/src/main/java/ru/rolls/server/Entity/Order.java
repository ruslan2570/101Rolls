package ru.rolls.server.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private ClientAddress deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee deliverer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderPosition> OrderPositions;


}
