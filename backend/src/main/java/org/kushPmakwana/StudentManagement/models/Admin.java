package org.kushPmakwana.StudentManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_table", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;
}
