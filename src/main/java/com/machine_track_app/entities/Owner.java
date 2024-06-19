package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.machine_track_app.enums.IdentificationType;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Long idOwner;
    private String owner;
    private String identification;
    @Column(name = "identification_type")
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;
    private LocalDateTime creation;
    private LocalDateTime modification;
    private Integer state;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OwnerCustomers> ownerCustomers;

}
