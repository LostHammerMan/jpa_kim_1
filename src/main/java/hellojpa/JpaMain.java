package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        try {
           /* Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
            em.persist(member1);
            em.persist(member2);*/

          /*  Member member = new Member(200L, "member200");
            em.persist(member);
            em.flush(); // tx.commit() 이전, DB 에 insert 쿼리 날라감
            System.out.println("=======================");*/

            // 영속 상태
            Member findMember = em.find(Member.class, 150L);
            // dirty checking
            findMember.setName("aaaa");

            // 준영속 상태로 전환 --> tx.commit() 시점에 아무일도 일어나지 않음
//            em.detach(findMember);
            // em.clear(); // 영속성 컨텍스트 비우기 --> tx.commit() 시점에 아무일도 일어나지 않음

            // JPA 에서는 필요없는 과정임
//            if (findMember.getName().equals("zzzz")){
//                em.update(findMember);
//            }

            System.out.println("=======================");
//            System.out.println("findMember.Name  = " + findMember.getName());
//            member.setId(101L);
//            member.setName("helloJPAB"); // 비영속 상태
//
//            System.out.println("===== Before =========");
//            em.persist(member); // 영속 상태
            System.out.println("===== After =======");

            // 조회시 select 쿼리 없음
            // 1차 캐시에서 조회하기 때문
           /* Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("result = " + (findMember1 == findMember2));*/
//            System.out.println("findMember.Id = " + findMember1.getId());
//            System.out.println("findMember.Name = " + findMember1.getName());
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

//         read 및 update
//        try {

            // 단순 조회의 경우
//            Member findMember = em.find(Member.class, 1L);

            // 복잡한 조회는 JPQL 사용해서 조회
            // Member table 을 대상으로 쿼리 X, Member 객체를 대상으로 쿼리
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1) // 1 ~ 8 까지의 결과 가져옴
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member findMember : result){
//                System.out.println("member.getName : " + findMember.getName());
//            }
//
//           /* findMember.setName("helloJPA"); // update 의 경우 em.persist 사용 안함, dirty checking(commit 시점에 체크)
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());*/
//            tx.commit();
//
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
        emf.close();
    }
}
