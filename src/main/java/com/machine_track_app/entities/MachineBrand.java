package com.machine_track_app.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "machine_brands")
public class MachineBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand")
    private Integer idBrand;

    @Column(name = "brand", nullable = false)
    private String brand;

}
