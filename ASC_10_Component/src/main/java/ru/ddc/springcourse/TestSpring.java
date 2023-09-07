package ru.ddc.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//        Music music = context.getBean("musicBean", RockMusic.class);
        Music music = context.getBean("rockMusic", RockMusic.class);
        MusicPlayer player = new MusicPlayer(music);
        player.playMusic();

        Music music1 = context.getBean("classicalMusic", ClassicalMusic.class);
        player.setMusic(music1);
        player.playMusic();

        context.close();
    }
}
