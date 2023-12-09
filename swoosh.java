package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class swoosh implements Gamemusic{
    String musicFile5 = "src/main/java/com/example/demo/swoosh.mp3";     // For example

    Media sound5 = new Media(new File(musicFile5).toURI().toString());
    MediaPlayer mediaPlayer5 = new MediaPlayer(sound5);

    @Override
    public void playmusic() {
        mediaPlayer5.seek(Duration.seconds(0));
        mediaPlayer5.play();
    }

    @Override
    public void stopmusic() {

    }
}
