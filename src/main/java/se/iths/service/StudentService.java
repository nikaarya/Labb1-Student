package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createNewStudent(Student student) {
        entityManager.persist(student);
    }

    public Student updateStudentEmail(Long id, String email) {
        Student findStudent = entityManager.find(Student.class, id);
        findStudent.setEmail(email);
        //return findStudent;
        return entityManager.merge(findStudent);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public List<Student> getStudentByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName LIKE :lastName", Student.class)
                .setParameter("lastName", lastName).getResultList();
    }

    public void deleteStudent(Long id) {
        Student findStudent = entityManager.find(Student.class, id);
        entityManager.remove(findStudent);
    }
}