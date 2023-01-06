package com.laborete.LaboreteAPI.profile.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class UserAvatarEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column()
    private String name;

    @Column
    private Long size;

    @OneToOne(mappedBy="userAvatarEntity")
    private UserEntity userEntity;

    public UserAvatarEntity(UUID id, String name, Long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public UserAvatarEntity() {}
}
