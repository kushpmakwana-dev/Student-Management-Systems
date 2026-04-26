package org.kushPmakwana.StudentManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "student_table")
@Data
@NoArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String collegeId;

    private String yearOfAdmission;

    private String admissionCap;

    @Size(min = 10, max = 10)
    @Column(nullable = false)
    private String fatherPhone;

    @Size(min = 10, max = 10)
    @Column(nullable = false)
    private String motherPhone;

    private String parentEmail;

    private String studentEmail;

    private String Address;

    private String city;

    private String pinCode;

    private String hobbies;


}
