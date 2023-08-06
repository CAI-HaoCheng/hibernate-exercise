package web.emp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Dept {
    @Id
    private Integer deptno;
    private String dname;
    private String loc;
    //一對多設定
//    @OneToMany
//    @JoinColumn(name = "DEPTNO",
//            referencedColumnName = "DEPTNO")
    //================================
    @OneToMany(mappedBy = "dept")
    private List<Emp> emps;
}
