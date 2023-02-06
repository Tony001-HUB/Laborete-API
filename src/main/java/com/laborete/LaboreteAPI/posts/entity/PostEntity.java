package com.laborete.LaboreteAPI.posts.entity;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

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
