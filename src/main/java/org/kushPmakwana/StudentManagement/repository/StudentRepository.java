package org.kushPmakwana.StudentManagement.repository;

import org.kushPmakwana.StudentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByCollegeId(String collegeId);
    boolean existsByStudentEmail(String email);

    @Query("Select s FROM Student s JOIN FETCH s.user WHERE s.collegeId = :collegeId")
    Optional<Student> findByCollegeIdWithUser(Long collegeId);


}
