//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.laborete.LaboreteAPI.posts.entity;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "post_id",
            nullable = false,
            columnDefinition = "UUID default gen_random_uuid()"
    )
    private UUID id;
    @Column
    private @NotBlank String text;
    @Column
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(
            name = "id"
    )
    private UserEntity user;


}
