package ru.ddc.springcourse;

public class MusicPlayer {

    private Music music;
    private String name;
    private int volume;
    private String name2;
    private int volume2;

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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getVolume2() {
        return volume2;
    }

    public void setVolume2(int volume2) {
        this.volume2 = volume2;
    }
}
