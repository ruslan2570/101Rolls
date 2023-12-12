package ru.rolls.server.Entity;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="restaurants")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @Column(nullable = false)
    private Integer maxDistance;

    @Column(nullable = false)
    private Double minOrderSum;

    @Column(nullable = false)
    private Boolean isManuallyClosed;

    @OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY)
    private List<Order> orders;


}
