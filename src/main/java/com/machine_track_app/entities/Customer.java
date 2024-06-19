package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.machine_track_app.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;
    private String name;
    private String identification;
    @Column(name = "identification_type")
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;
    private LocalDateTime creation;
    private LocalDateTime modification;
    private Integer state;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OwnerCustomers> ownerCustomers;

}
