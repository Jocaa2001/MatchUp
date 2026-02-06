package com.matchup.participation.entity;

import com.matchup.common.entity.BaseEntity;
import com.matchup.event.entity.Event;
import com.matchup.participation.enums.ParticipationStatus;
import com.matchup.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "mc_participation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mc_event_id", nullable = false)
    private Event event;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ParticipationStatus status;

}
