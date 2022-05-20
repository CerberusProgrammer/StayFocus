package com.example.stayfocus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private Button timer;

    @FXML
    private Label title;

    Timeline timeline;
    private static final int SECONDS_WORK = 59;
    private static final int MINUTES_WORK = 24;
    private static final int SECONDS_RELAX = 59;
    private static final int MINUTES_RELAX = 5;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(SECONDS_WORK);

    private boolean isCountdown = false;
    boolean timerWork = false;
    int min = 24;

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        Start.stage.close();
    }

    @FXML
    void minimize(ActionEvent event) {
        Start.stage.setIconified(true);
    }

    @FXML
    void moveTimer(ActionEvent event) {
        if (isCountdown) {
            isCountdown = false;
            timeline.stop();
        } else {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeSeconds.set(SECONDS_WORK);
            timeline.play();
            isCountdown = true;
            timerWork = true;
            title.setText("Work time!");
        }
    }

    void updateTime() {
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds - 1);

        if (seconds == 0) {
            timeSeconds.set(SECONDS_WORK);
            min--;
        }

        if (min == 0) {
            timerWork = false;
            min = MINUTES_WORK;
            Dialog dialog = new Dialog();
            dialog.setTitle("Relax time");
            dialog.showAndWait();
        }

        timer.setText("" + min + " : " + seconds);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}