package ru.ddc.springcourse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
//@Scope("singleton")
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Rhapsody";
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("INIT");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("DESTROY");
//    }
}
