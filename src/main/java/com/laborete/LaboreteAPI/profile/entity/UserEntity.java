package com.laborete.LaboreteAPI.profile.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column
    @NotBlank
    private String firstName;
    @Column
    @NotBlank
    private String secondName;
    @Column
    @NotBlank
    private String position;
    @Column
    @NotBlank
    private String location; //Todo Location API will be integrate
    @Column
    private String generalInfo;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn (name="avatar_id")
    private UserAvatarEntity userAvatarEntity;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    public UserAvatarEntity getUserAvatarEntity() {
        return userAvatarEntity;
    }

    public void setUserAvatarEntity(UserAvatarEntity userAvatarEntity) {
        this.userAvatarEntity = userAvatarEntity;
    }
}
