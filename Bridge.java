package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;

public class Bridge {
    private Rectangle Bridgerect;

    public Rectangle getBridgerect() {
        return Bridgerect;
    }

    public void setBridgerect(Rectangle bridgerect) {
        Bridgerect = bridgerect;
    }

    public void fallsshortanim(Character c1){
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(getBridgerect().getHeight() / 2);

        Timeline timeline = new Timeline();
        Duration frameGap = Duration.millis(5);
        Duration frameTime = Duration.millis(0.2);
        int a1;
        for (a1 = 270; a1 <= 360; a1++) {
            final int currentAngle = a1;
            frameTime = frameTime.add(frameGap);
            KeyFrame keyFrame = new KeyFrame(frameTime, e -> {
                r.setAngle(currentAngle);
                getBridgerect().getTransforms().setAll(r);
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                c1.run(c1.getCurrentbridge().getBridgerect().getLayoutX() + c1.getCurrentbridge().getBridgerect().getWidth() - 20, true);
            }
        });
    }
    String musicFile1 = "src/main/java/com/example/demo/block falling.mp3";     // For example

    Media sound1 = new Media(new File(musicFile1).toURI().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
    public void fallscorrect(Character c1){
            System.out.println("Done");

            Rotate r = new Rotate();
            r.setPivotX(0);
            r.setPivotY(c1.getCurrentbridge().getBridgerect().getHeight() / 2);

            Timeline timeline = new Timeline();
            Duration frameGap = Duration.millis(5);
            Duration frameTime = Duration.millis(0.2);
            int a1;
            for (a1 = 270; a1 <= 360; a1++) {
                final int currentAngle = a1;
                frameTime = frameTime.add(frameGap);
                KeyFrame keyFrame = new KeyFrame(frameTime, e -> {
                    r.setAngle(currentAngle);
                    c1.getCurrentbridge().getBridgerect().getTransforms().setAll(r);
                });
                timeline.getKeyFrames().add(keyFrame);
            }

            mediaPlayer1.setVolume(0.7);
            mediaPlayer1.play();


            timeline.setCycleCount(1);
            timeline.play();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    double tox = c1.getNextblock().getLayoutX() + c1.getNextblock().getWidth() - 50;
                    System.out.println(tox);
                    c1.run(tox, false);
                    c1.setScore(c1.getScore()+1);
                    c1.getA().getC().getScore().setText(String.valueOf(c1.getScore()));
                }
            });
    }
    public void toolong(Character c1){
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(c1.getCurrentbridge().getBridgerect().getHeight() / 2);

        Timeline timeline = new Timeline();
        Duration frameGap = Duration.millis(5);
        Duration frameTime = Duration.millis(0.2);
        int a1;
        for (a1 = 270; a1 <= 360; a1++) {
            final int currentAngle = a1;
            frameTime = frameTime.add(frameGap);
            KeyFrame keyFrame = new KeyFrame(frameTime, e -> {
                r.setAngle(currentAngle);
                c1.getCurrentbridge().getBridgerect().getTransforms().setAll(r);
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                c1.run(c1.getCurrentbridge().getBridgerect().getLayoutX() + c1.getCurrentbridge().getBridgerect().getWidth() - 20, true);
            }
        });
    }
}
