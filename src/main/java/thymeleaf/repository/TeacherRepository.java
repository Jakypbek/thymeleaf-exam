package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Company;
import thymeleaf.model.Student;
import thymeleaf.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TeacherRepository {

    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    public void save(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public Teacher findByCourseId(Long courseId) {
        try {
            return entityManager.createQuery("SELECT t FROM Teacher t where t.course.id = ?1", Teacher.class)
                    .setParameter(1, courseId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public void updated(Teacher teacher, Long teacherId) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Teacher where id = ?1")
                .setParameter(1, teacherId);
        teacher.setId(teacherId);
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
    }

    public Teacher findById(Long teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public void delete(Long teacherId) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(teacherId));
        entityManager.getTransaction().commit();
    }
}
