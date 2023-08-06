package web.member.pojo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
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
}
