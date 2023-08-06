package web.member.pojo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class Dept {
    @Id
    private Integer deptno;
    private String dname;
    private String loc;
}
