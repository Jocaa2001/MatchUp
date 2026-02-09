package com.matchup.user.entity;

import com.matchup.common.entity.BaseEntity;
import com.matchup.user.enums.UserRole;
import com.matchup.userprofile.entity.UserProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mc_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mc_user_profile_id")
    private UserProfile profile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

}
