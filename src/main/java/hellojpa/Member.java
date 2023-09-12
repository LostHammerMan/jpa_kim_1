package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA 가 관리하는 객체
@Table(name = "MBR")
public class Member {

    @Id
    private Long id;

//    @Column(name = "username")
    private String name;

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
    }


}
