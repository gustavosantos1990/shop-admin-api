package org.gdas.bigreportsapi.movement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.entity.Acquisition;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.movement.domain.enumeration.MovementLink;
import org.gdas.bigreportsapi.movement.domain.enumeration.MovementReason;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movement")
public class Movement {

    @Id
    @Column(name = "mvt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private MovementReason reason;

    //MVT_TYPE VARCHAR/ENUM NN

    @Column(name = "link", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private MovementLink link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mvt_rqt_id")
    private Request request;
    @ManyToOne(optional = false)
    @JoinColumn(name = "mvt_acq_id")
    private Acquisition acquisition;

    @Column(name = "pdt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "mvt_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "mvt_description", nullable = false)
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "voucher_path")
    private String voucherPath;

}
