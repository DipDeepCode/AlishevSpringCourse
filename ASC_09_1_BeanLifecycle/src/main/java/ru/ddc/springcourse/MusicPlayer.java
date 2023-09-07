package ru.ddc.springcourse;

public class MusicPlayer {

    private Music music;
    private String name;
    private int volume;

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
