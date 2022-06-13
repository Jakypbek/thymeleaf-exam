package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;
import thymeleaf.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class GroupRepository {

    private final EntityManager entityManager;

    public GroupRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }

    public List<Group> getAllGroups() {
        return entityManager.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }

    public void save(Group group) {
        System.out.println(group.getCourses().size());
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
    }

    public List<Group> findByCourseId(Long courseId) {
        return entityManager
                .createQuery("select g from Group g where (select c from Course c where c.id = ?1) member of g.courses", Group.class)
                .setParameter(1, courseId)
                .getResultList();
    }

    public Group findById(Long groupId) {
        return entityManager.find(Group.class, groupId);
    }

    public void update(Group group) {
        entityManager.getTransaction().begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();
    }

    public void updated(Group group, Long groupId) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Group where id = ?1")
                .setParameter(1, groupId);
        group.setId(groupId);
        entityManager.merge(group);
        entityManager.getTransaction().commit();
    }

    public void delete(Long groupId) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(groupId));
        entityManager.getTransaction().commit();
    }
}
