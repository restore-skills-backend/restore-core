package com.restore.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    private String email;

    @Column(name = "iam_id")
    private String iamId;

    private String avatar;

    private boolean archive;

    @JsonIgnore
    @CreatedDate
    @Builder.Default
    private Instant created = LocalDateTime.now().toInstant(ZoneOffset.UTC);

    @JsonIgnore
    @LastModifiedDate
    @Builder.Default
    private Instant modified = LocalDateTime.now().toInstant(ZoneOffset.UTC);

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}