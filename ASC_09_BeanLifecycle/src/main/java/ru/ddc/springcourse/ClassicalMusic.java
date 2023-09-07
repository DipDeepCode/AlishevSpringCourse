package ru.ddc.springcourse;

public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Rhapsody";
    }

    private void init() {
        System.out.println("Bean initialization");
    }

    private void destroy() {
        System.out.println("Bean destruction");
    }
}
