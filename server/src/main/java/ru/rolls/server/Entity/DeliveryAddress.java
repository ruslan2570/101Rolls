package ru.rolls.server.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="addresses")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false, length = 5)
    private String home;

    private String building;

    @Column(nullable = false)
    private Boolean isChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee operator;

    private LocalDateTime checkingDate;

    @OneToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    private List<ClientAddress> clientAddresses;


}
