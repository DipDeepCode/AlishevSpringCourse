package ru.ddc.springcourse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component("musicBean")
@Component
@Scope("prototype")
public class RockMusic implements Music {

    @Override
    public String getSong() {
        return "Wind cries";
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }
}
