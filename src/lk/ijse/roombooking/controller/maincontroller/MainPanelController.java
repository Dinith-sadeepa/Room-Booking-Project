package lk.ijse.roombooking.controller.maincontroller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.LogInBO;
import lk.ijse.roombooking.business.custom.LogOutBO;
import lk.ijse.roombooking.model.LogInDTO;
import lk.ijse.roombooking.model.LogOutDTO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {
    @FXML
    private AnchorPane mainPanelPane;

    @FXML
    private AnchorPane loaderPanel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label reservationLabel;

    @FXML
    private Label departingLabel;

    @FXML
    private Label reservedLabel;

    @FXML
    private Label roomSearchLabel;

    @FXML
    private Label roomMasterLabel;

    @FXML
    private Label viewsLabel;

    @FXML
    private Label guestsLabel;

    @FXML
    private Label manageExtrasLabel;

    @FXML
    private Label settingLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label exitLabel;

    @FXML
    private Label userTypeLabel;

    @FXML
    private ImageView dashboardImage;

    @FXML
    private ImageView reservationImage;

    @FXML
    private ImageView departingImage;

    @FXML
    private ImageView reservedImage;

    @FXML
    private ImageView roomSearchImage;

    @FXML
    private ImageView roomMasterImage;

    @FXML
    private ImageView viewImage;

    @FXML
    private ImageView guestsImage;

    @FXML
    private ImageView manageExtrasImage;

    @FXML
    private ImageView settingImage;

    @FXML
    private ImageView logOutImage;

    @FXML
    private ImageView exitImage;

    private LogInBO logInBO;
    private LogOutBO logOutBO;

    public MainPanelController() {
        logInBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
        logOutBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGOUT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeDate();
        transition();
        dashboardLabelClicked();
        userTypeLabelSet();
    }

    @FXML
    void mainPanelKey(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            dashboardLabelClicked();
        } else if (event.getCode() == KeyCode.F2) {
            reservationLabelClicked();
        } else if (event.getCode() == KeyCode.F3) {
            departingLabelClicked();
        } else if (event.getCode() == KeyCode.F4) {
            reservedLabelClicked();
        } else if (event.getCode() == KeyCode.F5) {
            roomSearchLabelClicked();
        } else if (event.getCode() == KeyCode.F6) {
            roomMasterLabelClicked();
        } else if (event.getCode() == KeyCode.F7) {
            viewsLabelClicked();
        } else if (event.getCode() == KeyCode.F8) {
            guestsLabelClicked();
        } else if (event.getCode() == KeyCode.F9) {
            manageExtrasLabelClicked();
        } else if (event.getCode() == KeyCode.F10) {
            systemSettingLabelClicked();
        }
    }

    private void userTypeLabelSet() {
        try {
            ArrayList<LogInDTO> logInDTOArrayList = logInBO.getAllLogins();
            for (LogInDTO logInDTO : logInDTOArrayList) {
                userTypeLabel.setText(logInDTO.getUserName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), mainPanelPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void setTimeDate() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
                dateLabel.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    @FXML
    private void dashboardLabelMouseEnterd() {
        mouseEnterd(dashboardImage);
    }

    @FXML
    private void reservationLabelMouseEnterd() {
        mouseEnterd(reservationImage);
    }

    @FXML
    private void departingLabelMouseEnterd() {
        mouseEnterd(departingImage);
    }

    @FXML
    private void reserveddLabelMouseEnterd() {
        mouseEnterd(reservedImage);
    }

    @FXML
    private void roomSearchLabelMouseEnterd() {
        mouseEnterd(roomSearchImage);
    }

    @FXML
    private void roomMasterLabelMouseEnterd() {
        mouseEnterd(roomMasterImage);
    }

    @FXML
    private void viewsLabelMouseEnterd() {
        mouseEnterd(viewImage);
    }

    @FXML
    private void guestsLabelMouseEnterd() {
        mouseEnterd(guestsImage);
    }

    @FXML
    private void manageExtrasLabelMouseEnterd() {
        mouseEnterd(manageExtrasImage);
    }

    @FXML
    private void settingLabelMouseEnterd() {
        mouseEnterd(settingImage);
    }

    @FXML
    private void logOutLabelMouseEnterd() {
        mouseEnterd(logOutImage);
    }

    @FXML
    private void exitLabelMouseEnterd() {
        mouseEnterd(exitImage);
    }

    @FXML
    private void mouseEnterd(ImageView image) {
        ScaleTransition sca = new ScaleTransition(Duration.millis(200), image);
        sca.setToX(1.2);
        sca.setToY(1.2);
        sca.play();

        DropShadow d = new DropShadow();
        d.setColor(Color.DARKORANGE);
        d.setHeight(20);
        d.setWidth(20);
        d.setRadius(20);
        image.setEffect(d);
    }

    @FXML
    private void dashboardLabelMouseExited() {
        mouseExited(dashboardImage);
    }

    @FXML
    private void reservationLabelMouseExited() {
        mouseExited(reservationImage);
    }

    @FXML
    private void departingLabelMouseExited() {
        mouseExited(departingImage);
    }

    @FXML
    private void reserveddLabelMouseExited() {
        mouseExited(reservedImage);
    }

    @FXML
    private void roomSearchLabelMouseExited() {
        mouseExited(roomSearchImage);
    }

    @FXML
    private void roomMasterLabelMouseExiteed() {
        mouseExited(roomMasterImage);
    }

    @FXML
    private void viewsLabelMouseExited() {
        mouseExited(viewImage);
    }

    @FXML
    private void guestsLabelMouseExited() {
        mouseExited(guestsImage);
    }

    @FXML
    private void manageExtrasLabelMouseExited() {
        mouseExited(manageExtrasImage);
    }

    @FXML
    private void settingLabelMouseExited() {
        mouseExited(settingImage);
    }

    @FXML
    private void logOutLabelMouseExited() {
        mouseExited(logOutImage);
    }

    @FXML
    private void exitLabelMouseExited() {
        mouseExited(exitImage);
    }

    @FXML
    private void mouseExited(ImageView image) {
        image.setEffect(null);

        ScaleTransition s = new ScaleTransition(Duration.millis(200), image);
        s.setToX(1.0);
        s.setToY(1.0);
        s.play();
    }


    @FXML
    private void dashboardLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/main/dashboard.fxml", loaderPanel, dashboardLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reservationLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/booking/reservation.fxml", loaderPanel, reservationLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void departingLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/booking/departing.fxml", loaderPanel, departingLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reservedLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/booking/reservedRooms.fxml", loaderPanel, reservedLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void roomSearchLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/room/roomSearch.fxml", loaderPanel, roomSearchLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void roomMasterLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/room/roomMaster.fxml", loaderPanel, roomMasterLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewsLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/main/views.fxml", loaderPanel, viewsLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guestsLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/guest/guests.fxml", loaderPanel, guestsLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void manageExtrasLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/main/manageExtras.fxml", loaderPanel, manageExtrasLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void systemSettingLabelClicked() {
        try {
            loadPane("/lk/ijse/roombooking/view/main/systemSetting.fxml", loaderPanel, settingLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logOutLabelClicked(MouseEvent event) {
        logOutLabel.setStyle("-fx-text-fill: #FF962F");
        defaultColor(logOutLabel);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure want to logOut?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText(null);
        Optional<ButtonType> action = a.showAndWait();
        if (action.get() == ButtonType.YES) {
            try {
                boolean isAdded = logOutBO.addLogOut(new LogOutDTO(userTypeLabel.getText(), java.sql.Time.valueOf(new SimpleDateFormat("hh:mm:ss").format(new Date()))));
                if (isAdded) {
                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/user/logIn.fxml"));
                    Scene logInScene = new Scene(root);
                    Stage logInStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    logInStage.setScene(logInScene);
                    logInStage.centerOnScreen();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            logOutLabel.setStyle(null);
        }
    }

    @FXML
    private void exitLabelClicked() {
        exitLabel.setStyle("-fx-text-fill: #FF962F");
        defaultColor(exitLabel);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure want to exit?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText(null);
        Optional<ButtonType> action = a.showAndWait();
        if (action.get() == ButtonType.YES) {
            try {
                boolean isAdded = logOutBO.addLogOut(new LogOutDTO(userTypeLabel.getText(), java.sql.Time.valueOf(new SimpleDateFormat("hh:mm:ss").format(new Date()))));
                if (isAdded) {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            exitLabel.setStyle(null);
        }

    }


    private void loadPane(String path, AnchorPane pane, Label label) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource(path));
        pane.getChildren().setAll(root);
        label.setStyle("-fx-text-fill: #FF962F");
        defaultColor(label);
    }

    private void defaultColor(Label label) {

        ArrayList<Label> labelList = new ArrayList<>();
        labelList.add(dashboardLabel);
        labelList.add(reservationLabel);
        labelList.add(departingLabel);
        labelList.add(reservedLabel);
        labelList.add(roomMasterLabel);
        labelList.add(roomSearchLabel);
        labelList.add(viewsLabel);
        labelList.add(guestsLabel);
        labelList.add(manageExtrasLabel);
        labelList.add(settingLabel);
        labelList.add(logOutLabel);
        labelList.add(exitLabel);

        for (Label labels : labelList) {
            if (labels == label) {
                continue;
            }
            labels.setStyle("-fx-text-fill: #828282");
        }

    }

}
