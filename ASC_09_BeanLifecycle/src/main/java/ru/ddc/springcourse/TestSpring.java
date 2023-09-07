package ru.ddc.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassicalMusic classicalMusic = context.getBean("musicBean", ClassicalMusic.class);
        System.out.println(classicalMusic.getSong());

        PrototypeBean prototypeBean = context.getBean("prototypeBean", PrototypeBean.class);
        prototypeBean.beanAction();

        PrototypeBean prototypeBean2 = context.getBean("prototypeBean", PrototypeBean.class);
        prototypeBean2.beanAction();

        context.close();
    }
}
