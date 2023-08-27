package ru.ddc.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        player.playMusic();
        System.out.println(player.getName());
        System.out.println(player.getVolume());
        System.out.println(player.getName2());
        System.out.println(player.getVolume2());
        context.close();
    }
}
