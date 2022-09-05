package com.unla.agroecologiaiot.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sector")
@EntityListeners(AuditingEntityListener.class)
public class Sector extends AuditableEntity<Long>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sectorId;

    private String name;
    private String centralizerKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gardenId", nullable = false, foreignKey = @ForeignKey(name = "FK_Garden_Sector"))
    private Garden garden;

    @ManyToMany
    @JoinTable(
    name = "gardencrop", 
    joinColumns = @JoinColumn(name = "sectorId"), 
    inverseJoinColumns = @JoinColumn(name = "cropId"))
    private Set<Crop> sectorCrops;

    @Transient
    private List<Long> cropIds;


    // private Set<MetricReading> metricReadings;
    // private HoseSystem hoseSystem;
}
