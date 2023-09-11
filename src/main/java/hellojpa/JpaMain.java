package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager 는 웹서버 로딩 시점에 딱 하나만 생성되어 있어야 함
        // EntityManager 는 쓰레드간 공유 X (사용하고 버려얗 ㅏㅁ)
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 객체를 불러와 시작
        // JPA 의 모든 데이터 변경은 트랜잭션 안에서 수행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 정석 코딩 -> 스프링에서는 em.persist 호출하면 나머지는 알아서 처리
        // create
//        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("Lee");
//            em.persist(member);
//            tx.commit();
//
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//            emf.close();
//        }

//         read 및 update
        try {
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloJPA"); // update 의 경우 em.persist 사용 안함, dirty checking(commit 시점에 체크)

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
