package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

    public Company findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    @Transactional
    public void update(Company company, Long companyId) {

        companyRepository.saveL(company, companyId);
    }

    public void delete(Long companyId) {
        companyRepository.deleteById(companyId);
    }


}
