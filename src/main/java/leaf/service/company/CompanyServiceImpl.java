
package leaf.service.company;

import leaf.db.company.CompanyDAO;
import leaf.model.company.CompanyList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO dao;

    public Vector<Map<String,String>> findCompany(String company) {
        String str = "%" + company + "%";
        System.out.println(str);
        Vector<Map<String,String>> list = new Vector<>();
        for (CompanyList item : dao.findByCompanyNmLike(str)) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("companyNm",item.getCompanyNm());
            list.add(map);
        }
        return list;
    }
}
