package com.laborete.LaboreteAPI.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserBackgroundEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column()
    private String name;
    @Column
    private Long size;
    @Column
    private String extension;
}
