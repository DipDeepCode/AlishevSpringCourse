package ru.ddc.springcourse;

public class MusicPlayer {

    private Music music;

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
