package org.hrytseyvch.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hrytseyvch.entity.Student;
import org.hrytseyvch.util.HibernateUtil;

import java.util.List;

public class StudentDaoImpl implements GenericDao<Student, Long> {

    @Override
    public void save(Student student) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(student);

            // тут ручна прив'язка домашок
            if (student.getHomeworks() != null) {
                student.getHomeworks().forEach(hw -> {
                    hw.setStudent(student);
                    em.persist(hw);
                });
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Student findById(Long id) {
        try (EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager()) {
            Student student = em.find(Student.class, id);
            if (student != null) {
                student.setFullName(student.getFullName());
            }
            return student;
        }
    }

    @Override
    public Student findByEmail(String email) {
        try (EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            // Якщо не знайдено - повертаємо null
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        }
    }

    @Override
    public Student update(Student student) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Student updatedStudent = null;

        try {
            transaction.begin();
            // merge() оновлює існуючий запис у базі
            updatedStudent = em.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return updatedStudent;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean isDeleted = false;

        try {
            transaction.begin();
            // Спочатку потрібно знайти сутність у контексті поточної сесії
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student); // Видаляємо
                isDeleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return isDeleted;
    }
}