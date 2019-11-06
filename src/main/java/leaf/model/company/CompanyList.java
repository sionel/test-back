package leaf.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Entity
@Table(schema = "company")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CompanyList {

    @Id
    @Column(length = 30, nullable = false)
    private String companyNm;

    @Column(length = 10, nullable = false)
    private String ceo;

    @Column(length = 15, nullable = false)
    private String phone;

}
