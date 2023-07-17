package com.restore.core.entity;

import com.restore.core.dto.app.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class StaffEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity userId;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private boolean active;
    private boolean archive;
}