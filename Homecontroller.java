package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Homecontroller {


    @FXML
    private AnchorPane Anchorpane;
    @FXML
    private Text High;
    @FXML
    private ImageView bgbutton;

    public static AnchorPane ap;
    public static Cherry cherry;
    private int selchar;

    static HelloController controller;


    @FXML
    private Circle close;

    @FXML
    private AnchorPane restbg;


    @FXML
    private Rectangle charmenu;

    @FXML
    private ImageView switchchar;

    static Scene newscene;

    @FXML
    private Circle playbutton;

    @FXML
    private ImageView stickheroicon;


    @FXML
    private ImageView char1;


    @FXML
    private ImageView char11;

    public static HelloApplication application;


    @FXML
    private Text playtext;


    private Image im1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/idle/Untitled-100.png")));
    private ImageView spivchar1 = new ImageView(im1);

    private Image im2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/idle2/Untitled-100.png")));
    private ImageView spivchar2 = new ImageView(im2);


    @FXML
    void play(MouseEvent event) {
        FadeTransition fad = new FadeTransition();
        fad.setDuration(Duration.seconds(1));
        fad.setNode(Anchorpane);
        fad.setFromValue(1);
        fad.setToValue(0);
        fad.play();
        fad.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    loadnextscreen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loadnextscreen() throws IOException {
        Scene newsc = Homecontroller.newscene;
        Stage currstg = (Stage) Anchorpane.getScene().getWindow();
        currstg.setScene(newsc);
    }

    public ImageView getStickheroicon() {
        return stickheroicon;
    }

    public void setStickheroicon(ImageView stickheroicon) {
        this.stickheroicon = stickheroicon;
    }

    public void initialize() throws IOException {
        ap = Anchorpane;
        selchar = 1;
        // Load the image (adjust the path accordingly)
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/icon1.png")));

        // Set the image on the ImageView
        stickheroicon.setImage(image);
        BufferedReader input = null;
        int max = 0;
        try {
            input = new BufferedReader(new FileReader("src/main/java/com/example/demo/score.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                if(Integer.parseInt(line) > max){
                    max = Integer.parseInt(line);
                }
            }
        }catch (Exception e){

        }finally {
            if(input!= null) {
                input.close();
            }
        }
        System.out.println(max);
        High.setText(Integer.toString(max));

    }

    @FXML
    void switchchar(MouseEvent event) {
        /*char1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/idle/Untitled-100.png"))));*/
        charmenu.setOpacity(0.95);
        char1.setVisible(true);
        char1.setDisable(false);
        if(selchar ==1) {
            char11.setOpacity(1);
            char1.setOpacity(0.2);
        } else if (selchar == 0) {
            char11.setOpacity(0.2);
            char1.setOpacity(1);
        }
        char11.setVisible(true);
        char11.setDisable(false);
        charmenu.setVisible(true);
        charmenu.setDisable(false);
        GaussianBlur gb = new GaussianBlur();
        gb.setRadius(100);
        restbg.setEffect(gb);
        close.setOpacity(1);
    }


    @FXML
    void close(MouseEvent event) {
        char1.setOpacity(0);
        char1.setVisible(false);
        char1.setDisable(true);
        char11.setOpacity(0);
        char11.setVisible(false);
        char11.setDisable(true);
        charmenu.setOpacity(0);
        charmenu.setDisable(true);
        charmenu.setVisible(false);
        GaussianBlur gb1 = new GaussianBlur();
        gb1.setRadius(0);
        restbg.setEffect(gb1);
        close.setOpacity(0);
    }


    @FXML
    void Clickedc1(MouseEvent event) throws IOException {
        if(selchar == 1) {
            char1.setOpacity(1);
            char11.setOpacity(0.2);
            controller.getC1().setSprite(spivchar1);
            controller.getC1().setFolder("idle");
            controller.getC1().setRunfolder("running");
            controller.getC1().setA(application);
            URL url = null;
            ResourceBundle rb = null;
            controller.initialize(url, rb);
        }
        selchar = 0;
    }

    @FXML
    void Clickedc2(MouseEvent event) {
        if(selchar == 0) {
            char1.setOpacity(0.2);
            char11.setOpacity(1);
            controller.getC1().setSprite(spivchar2);
            controller.getC1().setFolder("idle2");
            controller.getC1().setRunfolder("running2");
            controller.getC1().setA(application);
            URL url = null;
            ResourceBundle rb = null;
            controller.initialize(url, rb);
        }
        selchar = 1;
    }

    public Text getHigh() {
        return High;
    }

    public void setHigh(Text high) {
        High = high;
    }


    @FXML
    void bgmenu(MouseEvent event) {

    }
}

