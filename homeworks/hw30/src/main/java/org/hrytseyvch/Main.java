package org.hrytseyvch;


import org.hrytseyvch.repository.StudentDaoImpl;
import org.hrytseyvch.entity.Homework;
import org.hrytseyvch.entity.Student;
import org.hrytseyvch.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();

        /*
         * !!!! обов'язково в плагіні db browser ставити auto-commit on, бо він успішно блокує конект апці до бази
         */
        Student student1 = new Student("АААА", "ВВВВВ", "alex@mail.com");
        Student student2 = new Student("XXXX", "YYYY", "marty@mail.com");
        studentDao.save(student2);

        Homework hw1 = new Homework("Вивчити Hibernate", LocalDate.now(), 10);
        Homework hw2 = new Homework("Написати програму", LocalDate.now(), 12);

        student1.getHomeworks().add(hw1);
        student1.getHomeworks().add(hw2);

        // Зберігаємо (DAO сам зробить прив'язку через forEach)
        studentDao.save(student1);

        System.out.println("Студенти і домашки успішно збережені в БД!");

        System.out.println("\n--- 1.  ТЕСТУВАННЯ FIND ALL ---");
        List<Student> allStudents = studentDao.findAll();
        System.out.println("Всі студенти в базі:");
        allStudents.forEach(s -> System.out.println(s.toString()));

        System.out.println("\n--- 2. ТЕСТУВАННЯ FIND BY EMAIL ---");
        Student foundByEmail = studentDao.findByEmail("alex@mail.com");
        System.out.println("Знайдено по email: " + foundByEmail);

        System.out.println("\n--- 3. ТЕСТУВАННЯ UPDATE ---");
        if (foundByEmail != null) {
            foundByEmail.setFirstName("Олександр");
            studentDao.update(foundByEmail); // Зберігаємо зміни в базу

            Student updatedStudent = studentDao.findById(foundByEmail.getId());
            System.out.println("Студент після оновлення: " + updatedStudent);
        }

        System.out.println("\n--- 4. ТЕСТУВАННЯ DELETE ---");
        if (foundByEmail != null) {
            boolean isDeleted = studentDao.deleteById(foundByEmail.getId());
            System.out.println("Студента видалено? " + isDeleted);

            Student deletedStudent = studentDao.findById(foundByEmail.getId());
            System.out.println("Спроба знайти видаленого студента: " + deletedStudent); // Має бути null
        }

        System.out.println("\n--- 5. ТЕСТУВАННЯ DELETE ІСНУЮЧОГО ОБ'ЄКТА СТУДЕНТА ---");
//тут на цей момент можна звертатись до існуючого об'єкта, бо hibernate вже на моменті сейв приділив йому ід і переніс в ентіті
        boolean isDeleted2 = studentDao.deleteById(student2.getId());
        System.out.println("Студента2 видалено? " + isDeleted2);

        Student deletedStudent2 = studentDao.findById(student2.getId());
        System.out.println("Спроба знайти видаленого студента2: " + deletedStudent2); // Має бути null


    }
}