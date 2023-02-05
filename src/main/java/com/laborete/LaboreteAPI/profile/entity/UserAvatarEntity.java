package com.laborete.LaboreteAPI.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private Long size;

    @Column
    private String extension;


}
