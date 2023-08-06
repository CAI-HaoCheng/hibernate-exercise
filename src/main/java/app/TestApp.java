package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//多對一測試
//        Emp emp = session.get(Emp.class, 7369);
//         Dept dept = emp.getDept();
//        System.out.println(dept.getDeptno());
//        System.out.println(dept.getDname());
        //一對多測試
//        Dept dept1 = session.get(Dept.class,30);
//        var emp = dept1.getEmps();
//        for(var emp  : emps){
//            System.out.println(emp.getEname());
//        }
        //雙向測試
        Emp emp = session.get(Emp.class, 7369);
        Dept dept = emp.getDept();
        List<Emp> emps = dept.getEmps();
        emps.forEach(empe -> System.out.println(empe.getEname()));
//        for(Emp empe  : emps){
//            System.out.println(empe.getEname());
//        }

        //======================查詢(測試能否連上資料庫)==========================
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Member member = session.get(Member.class, 1); //取得1號會員
//		System.out.println(member.getNickname());
//		HibernateUtil.shutdown();

        //======================Insert==========================
//		Member member = new Member();
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		
//		TestApp app = new TestApp();
//		app.insert(member);
//		System.out.println(member.getId());

        //======================Delete=========================
//		TestApp app = new TestApp();
//		System.out.println(app.deleteById(3));

        //======================updateById=========================
//		TestApp app = new TestApp();
//		Member member = new Member();
//		member.setId(1);
//		member.setPass(false);
//		member.setRoleId(2);
//		System.out.println(app.updateById(member));

        //======================selectById=========================
//		TestApp app = new TestApp();
//		Member member = app.selectById(2);
//		System.out.println(member.getNickname());

        //======================selectAll=========================
//		TestApp app = new TestApp();

//		app.selectAll().forEach(member -> System.out.println(member.getNickname()));
        //or
//		for(Member member : app.selectAll()) {
//			System.out.println(member.getNickname());
//		}

        //======================Criteria=========================
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		
//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		//from MEMBER
//		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//		Root<Member> root = criteriaQuery.from(Member.class);
//		//where USERNAME=? and PASSWORD=?
//		criteriaQuery.where(
//				criteriaBuilder.and(
//				criteriaBuilder.equal(root.get("username"), "admin"),
//				criteriaBuilder.equal(root.get("password"), "P@ssw0rd")
//				));
//		//select USERNAME, NICKNAME
//		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
//		//order by ID
//		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//		
//		Member member = session.createQuery(criteriaQuery).uniqueResult();
//		System.out.println(member.getNickname());


        //======================Dept to Emp=========================
    }

    public Integer insert(Member member) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(member);
            transaction.commit();
            return member.getId();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    public int deleteById(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Member member = session.get(Member.class, id);
            session.remove(member);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    public int updateById(Member newMember) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Member oldMember = session.get(Member.class, newMember.getId());
            final Boolean pass = newMember.getPass();
            if (pass != null) {
                oldMember.setPass(pass);
            }
            final Integer roleId = newMember.getRoleId();
            if (roleId != null) {
                oldMember.setRoleId(roleId);
            }

            transaction.commit();
            return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    public Member selectById(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Member member = session.get(Member.class, id);
            transaction.commit();
            return member;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    public List<Member> selectAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
//			Query<Member> query = session.createQuery("FROM Member", Member.class);
            Query<Member> query = session.createQuery("SELECT new web.member.pojo.Member(username, nickname) FROM Member", Member.class);
            List<Member> list = query.getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
}
