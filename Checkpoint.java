package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Checkpoint implements Gamemusic{
    String musicFile2 = "src/main/java/com/example/demo/checkpoint.mp3";     // For example

    Media sound2 = new Media(new File(musicFile2).toURI().toString());
    MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);

    @Override
    public void playmusic() {
        mediaPlayer2.seek(Duration.seconds(0));
        mediaPlayer2.play();
    }

    @Override
    public void stopmusic() {
        mediaPlayer2.stop();
    }
}
