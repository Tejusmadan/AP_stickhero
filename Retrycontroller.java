package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class Retrycontroller {

    @FXML
    private Button Retrybutton;

    @FXML
    private Text text1;

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane anchor2;


    @FXML
    private AnchorPane anchor1;

    @FXML
    private Circle close;


    @FXML
    private Button Revivebutton;

    @FXML
    void retry(MouseEvent event) {
        Scene newsc = Homecontroller.newscene;
        Stage currstg = (Stage) anchor.getScene().getWindow();
        currstg.setScene(newsc);
        FadeTransition fad = new FadeTransition();
        fad.setDuration(Duration.seconds(1));
        fad.setNode(currstg.getScene().getRoot());
        fad.setFromValue(0);
        fad.setToValue(1);
        fad.play();
        HelloApplication.c.getC1().setScore(0);
        HelloApplication.c.getScore().setText("0");

    }


    @FXML
    void revive(MouseEvent event) throws IOException {
        if (HelloApplication.c.getC1().getNumcherries() >= 3) {
            Scene newsc = Homecontroller.newscene;
            Stage currstg = (Stage) anchor.getScene().getWindow();
            currstg.setScene(newsc);
            FadeTransition fad = new FadeTransition();
            fad.setDuration(Duration.seconds(1));
            fad.setNode(currstg.getScene().getRoot());
            fad.setFromValue(0);
            fad.setToValue(1);
            fad.play();
            BufferedReader input = null;
            try {
                input = new BufferedReader(new FileReader("src/main/java/com/example/demo/score.txt"));
                String last = null, line;

                while ((line = input.readLine()) != null) {
                    last = line;
                }
                HelloApplication.c.getC1().setScore(Integer.parseInt(last));
                HelloApplication.c.getScore().setText(Integer.toString(HelloApplication.c.getC1().getScore()));
                HelloApplication.c.getC1().setNumcherries(HelloApplication.c.getC1().getNumcherries() - 3);
                HelloApplication.c.getText1().setText(Integer.toString(HelloApplication.c.getC1().getNumcherries()));
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new FileWriter("src/main/java/com/example/demo/numdiamonds.txt"));
                    out.println(HelloApplication.c.getC1().getNumcherries());
                } catch (Exception e) {
                } finally {
                    out.close();
                }
                System.out.println(last);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                input.close();
            }
        } else if (HelloApplication.c.getC1().getNumcherries() < 3) {
            anchor1.setOpacity(1);
            anchor1.setVisible(true);
            anchor1.setDisable(false);
            GaussianBlur gb = new GaussianBlur();
            gb.setRadius(100);
            anchor2.setDisable(true);
            anchor2.setEffect(gb);
        }
    }

    @FXML
    void close(MouseEvent event) {
        anchor1.setOpacity(0);
        anchor1.setVisible(false);
        anchor1.setDisable(true);
        GaussianBlur gb = new GaussianBlur();
        gb.setRadius(0);
        anchor2.setDisable(false);
        anchor2.setEffect(gb);
    }

}
