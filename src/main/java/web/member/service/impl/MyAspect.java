package web.member.service.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Aspect
@Component
public class MyAspect {
    @Before("execution(* web.member.service.impl.MemberServiceImpl.login(..))")
    public void before() {
        System.out.println(new Time(System.currentTimeMillis()));
    }
}
