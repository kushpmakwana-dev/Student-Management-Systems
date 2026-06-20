package org.kushPmakwana.StudentManagement.repository;

import org.kushPmakwana.StudentManagement.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUserEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
