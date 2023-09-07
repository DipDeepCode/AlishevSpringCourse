package ru.ddc.springcourse;

public class PrototypeBean {

    private void init() {
        System.out.println("init");
    }

    public void beanAction() {
        System.out.println("action");
    }

    private void destroy() {
        System.out.println("destroy");
    }
}
