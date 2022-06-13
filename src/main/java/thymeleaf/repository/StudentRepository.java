package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Group;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }

    public void save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public Student findById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }


    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    public void deleteById(Long studentId) {
        entityManager.remove(findById(studentId));
    }


    public List<Student> findByGroupId(Long groupId) {
        return entityManager.createQuery("SELECT s FROM Student s where s.group.id = ?1", Student.class)
                .setParameter(1, groupId)
                .getResultList();
    }

    public void updated(Student student, Long studentId) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Student where id = ?1")
                .setParameter(1, studentId);
        student.setId(studentId);
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }
}
