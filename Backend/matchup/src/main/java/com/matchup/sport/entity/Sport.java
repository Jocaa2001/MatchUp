package com.matchup.sport.entity;


import com.matchup.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "mc_sport")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sport extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
