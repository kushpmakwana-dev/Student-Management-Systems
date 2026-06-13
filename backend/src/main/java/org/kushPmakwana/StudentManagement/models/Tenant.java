package org.kushPmakwana.StudentManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tenant_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String collegeName;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String pinCode;

    @Column(nullable = false, unique = true)
    private String aisheCode;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    private void addCreationTime(){
        this.createdAt = LocalDateTime.now();
    }
}
