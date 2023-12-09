package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class extension implements Gamemusic{
    private String musicFile3 = "src/main/java/com/example/demo/blip.mp3";     // For example

    private Media sound3 = new Media(new File(musicFile3).toURI().toString());
    private MediaPlayer mediaPlayer3 = new MediaPlayer(sound3);
    @Override
    public void playmusic() {
        mediaPlayer3.play();
        mediaPlayer3.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer3.setRate(3);
    }

    @Override
    public void stopmusic() {
        mediaPlayer3.stop();
    }
}
