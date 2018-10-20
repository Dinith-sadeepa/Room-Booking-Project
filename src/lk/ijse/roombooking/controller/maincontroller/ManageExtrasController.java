package lk.ijse.roombooking.controller.maincontroller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageExtrasController implements Initializable {

    @FXML
    private AnchorPane manageExtrasPane;

    @FXML
    private AnchorPane extrasLoadingPane;

    @FXML
    private Label facilityLabel;

    @FXML
    private Label serviceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        try {
            facilitiesPanelClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), manageExtrasPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void facilitiesPanelClicked() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/facility/facilities.fxml"));
        extrasLoadingPane.getChildren().setAll(pane);
        facilityLabel.setStyle("-fx-text-fill: #FF962F");
        serviceLabel.setStyle("-fx-text-fill: #828282");

    }

    @FXML
    private void servicePanelClicked() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/service/services.fxml"));
        extrasLoadingPane.getChildren().setAll(pane);
        serviceLabel.setStyle("-fx-text-fill: #FF962F");
        facilityLabel.setStyle("-fx-text-fill: #828282");
    }
}
