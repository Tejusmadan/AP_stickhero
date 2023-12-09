package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Bgm implements Gamemusic{
    private String musicFile = "src/main/java/com/example/demo/bgm.mp3";     // For example

    private Media sound = new Media(new File(musicFile).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @Override
    public void playmusic() {
        mediaPlayer.setVolume(0.7);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void stopmusic() {
        mediaPlayer.stop();
    }
}
