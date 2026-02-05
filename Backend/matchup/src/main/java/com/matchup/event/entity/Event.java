package com.matchup.event.entity;

import com.matchup.common.entity.BaseEntity;
import com.matchup.location.entity.Location;
import com.matchup.sport.entity.Sport;
import com.matchup.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mc_event")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_sport_id", nullable = false)
    private Sport sport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_user_id", nullable = false)
    private User user;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "max_players")
    private Integer maxPlayers;

    @Column(name = "status", nullable = false, length = 50)
    private String status;
}
