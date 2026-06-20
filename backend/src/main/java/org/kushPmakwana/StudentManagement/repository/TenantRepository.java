package org.kushPmakwana.StudentManagement.repository;

import org.kushPmakwana.StudentManagement.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    boolean existsByAisheCode(String aisheCode);
}
