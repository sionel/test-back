package leaf.db.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import leaf.model.company.CompanyList;

@Repository
public interface CompanyDAO extends JpaRepository<CompanyList, String> {

    List<CompanyList> findByCompanyNmLike(String companyNm);

}
