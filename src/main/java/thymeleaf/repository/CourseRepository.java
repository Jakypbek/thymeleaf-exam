package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CourseRepository {

    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }


    public List<Course> findByCompanyId(Long companyId) {
        return entityManager.createQuery("SELECT c FROM Course c WHERE c.company.id = ?1", Course.class)
                .setParameter(1, companyId)
                .getResultList();
    }

    public List<Course> getAllCourses() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    public void save(Course course) {
        entityManager.getTransaction().begin();

        entityManager.persist(course);

        entityManager.getTransaction().commit();
    }

    public Course findById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public List<Course> findByGroupId(Long groupId) {
        return entityManager.createQuery("select c from Course c where (select g from Group g where g.id = ?1) member of c.groups", Course.class)
                .setParameter(1, groupId)
                .getResultList();
    }

    public void update(Course course) {
        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }

    public void updated(Course course, Long courseId) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Course where id = ?1")
                .setParameter(1, courseId);
        course.setId(courseId);
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }

    public void delete(Long courseId) {

            entityManager.getTransaction().begin();
            entityManager.remove(findById(courseId));
            entityManager.getTransaction().commit();

    }

    public Company findByCourseId(Long courseId) {
        try {
            return (Company) entityManager.createQuery("select c.company from Course c where Course.id = ?1")
                    .setParameter(1, courseId).getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }

    }

    public void savea(Course course, Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }
}
