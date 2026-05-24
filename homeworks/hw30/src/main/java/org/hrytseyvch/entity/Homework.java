package org.hrytseyvch.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homework")
@Getter @Setter
@NoArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate deadline;
    private int mark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


    public Homework(String description, LocalDate deadline, int mark) {
        this.description = description;
        this.deadline = deadline;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homework homework = (Homework) o;
        // Якщо id = null, вони не рівні (дозволяє додавати нові об'єкти в Set)
        return id != null && Objects.equals(id, homework.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Homework{id=" + id + ", description='" + description + "', mark=" + mark + "}";
    }
}