package hellojpa;

import javax.persistence.*;
import java.util.Date;

//@Table(name = "MBR", uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE",
//        columnNames = {"NAME", "AGE"})})
@Entity // JPA 가 관리하는 객체
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient // DB 랑 관계없이 메모리에서만 계산하고 싶을떄
    private int temp;

    public Member(){}





   /* @Id
    private Long id;

//    @Column(name = "username")
    @Column(nullable = false, length = 10, unique = true)
    private String name;
    private int oooo; // 테이블에 존재하지 않는 필드이므로 missing column에러 발생

//    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // jpa 는 내부적으로 reflection 사용 --> 동적으로 객체 생성 필요 -> 기본 생성자 필요
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }*/


}
