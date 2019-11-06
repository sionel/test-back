package leaf.model.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Entity
@Table(schema = "member")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MemberList {

    @Id
    @Column(length = 30, nullable = false)
    private String memberId;

    @Column(length = 30, nullable = false)
    private String memberPw;

    @Column(length = 10, nullable = false)
    private String memberNm;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String companyNm;

    @Column(length = 30, nullable = false)
    private String departmentNm;

    @Column(length = 10, nullable = false)
    private String position;

    @Column(length = 200, nullable = false)
    private String profile;

}
