package com.laborete.LaboreteAPI.profile.entity;

import javax.persistence.*;
import java.util.UUID;

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

    public UserAvatarEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
