package com.machine_track_app.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "municipalities")
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipality")
    private Long idMunicipality;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_departments")
    private Department department;

}
