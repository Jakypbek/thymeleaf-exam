package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CompanyRepository {

    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }


    public List<Company> getAllCompanies() {
        return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    public void save(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }

    public Company findById(Long companyId) {

        return entityManager.find(Company.class, companyId);
    }


    public void update(Company company) {
        entityManager.getTransaction().begin();
        entityManager.merge(company);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Long companyId) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(companyId));
        entityManager.getTransaction().commit();
    }

    public void saveL(Company company, Long companyId) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Company where id = ?1")
                .setParameter(1, companyId);
        company.setId(companyId);
        entityManager.merge(company);
        entityManager.getTransaction().commit();
    }


}
