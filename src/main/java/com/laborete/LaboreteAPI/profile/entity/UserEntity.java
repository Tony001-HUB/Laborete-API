package com.laborete.LaboreteAPI.profile.entity;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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
    @Column
    private Integer age;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "avatar_id")
    private UserAvatarEntity userAvatarEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "background_id")
    private UserBackgroundEntity userBackgroundEntity;

    @OneToMany(mappedBy = "user")
    private Set<PostEntity> posts;
}
