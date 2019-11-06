package leaf.service.company;

import java.util.Map;
import java.util.Vector;

public interface CompanyService {

    Vector<Map<String,String>> findCompany(String company);
}
