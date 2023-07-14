package com.restore.core.dto.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Base {

    @JsonIgnore
    @CreatedBy
    private String createdBy = "";

    @JsonIgnore
    @LastModifiedBy
    private String modifiedBy = "";

    @JsonIgnore
    @CreatedDate
    @Builder.Default
    private Instant created = LocalDateTime.now().toInstant(ZoneOffset.UTC);

    @JsonIgnore
    @LastModifiedDate
    @Builder.Default
    private Instant modified = LocalDateTime.now().toInstant(ZoneOffset.UTC);

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
