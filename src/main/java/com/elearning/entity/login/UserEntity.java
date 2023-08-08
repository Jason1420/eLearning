package com.elearning.entity.login;

import com.elearning.entity.StudentEntity;
import com.elearning.entity.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean changedPassword = false;

    public UserEntity(String username, String password, String email,
                      StudentEntity student, TeacherEntity teacher) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.student = student;
        this.teacher = teacher;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

}
