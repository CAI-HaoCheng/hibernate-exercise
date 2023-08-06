package web.emp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
public class Emp {
    @Id
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Timestamp hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
    //多對一設定
//    @ManyToOne
//    @JoinColumn(name = "DEPTNO", insertable = false, updatable = false)
    @ManyToOne
    @JoinColumn(name = "DEPTNO",
            insertable = false,
            updatable = false)
    private Dept dept;
}
