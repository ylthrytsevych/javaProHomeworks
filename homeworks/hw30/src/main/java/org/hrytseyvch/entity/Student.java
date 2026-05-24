package org.hrytseyvch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.IdGeneratorType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Homework> homeworks = new HashSet<>();

    @Transient
    private String fullName;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addHomework(final Homework homework) {
        this.homeworks.add(homework);
        // Залишаємо так, оскільки зворотну прив'язку (setStudent) ми робимо в StudentDAO
    }

    public void removeHomework(final Homework homework) {
        this.homeworks.remove(homework);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        // Якщо id = null, вони не рівні (дозволяє додавати нові об'єкти в Set)
        return id != null && Objects.equals(id, student.id);
//        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                // ми не виводимо тут homeworks, щоб програма не зациклилась!
                '}';
    }
}
