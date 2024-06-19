package com.machine_track_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "machine_gps")
public class MachineGPS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gps")
    private Long idGPS;

    @Column(name = "plate", nullable = false)
    private String plate;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "serie_ma", nullable = false)
    private String serieMa;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = false)
    private ProviderGPS providerGPS;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "installation_date", nullable = false)
    private LocalDateTime installationDate;

    @Column(name = "renewal_date")
    private LocalDateTime renewalDate;

}
