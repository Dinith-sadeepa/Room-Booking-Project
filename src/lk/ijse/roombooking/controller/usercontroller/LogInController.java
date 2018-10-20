package lk.ijse.roombooking.controller.usercontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.LogInBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.LogInDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private JFXTextField userNameText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton signUpButton;

    private LogInBO logInBO;
    private SignUpBO signUpBO;

    public LogInController() {
        logInBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void passwordAction(ActionEvent event) {
        try {
            SignUpDTO signUpDTO = signUpBO.searchUser(userNameText.getText());
            if (!userNameText.getText().trim().isEmpty() && !passwordText.getText().trim().isEmpty()) {
                if (signUpDTO == null) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "User Name is Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    userNameText.setText(null);
                    passwordText.setText(null);

                    userNameText.requestFocus();
                } else {
                    if (passwordText.getText().equals(signUpDTO.getPassword())) {
                        boolean isAdded = logInBO.addLogIn(new LogInDTO(userNameText.getText(), java.sql.Date.valueOf(new SimpleDateFormat("YYYY-MM-dd").format(new Date())), java.sql.Time.valueOf(new SimpleDateFormat("hh:mm:ss").format(new Date()))));
                        if (isAdded) {
                            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/main/mainPanel.fxml"));
                            Scene mainPanelScene = new Scene(root);
                            Stage mainPanelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            mainPanelStage.setScene(mainPanelScene);
                            mainPanelStage.centerOnScreen();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Password Incorrect!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        passwordText.setText(null);
                        passwordText.requestFocus();
                    }
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Please verify your details!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                userNameText.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void signUpButtonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/user/signUp.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), scene.getRoot());
            transition.setFromX(-scene.getWidth());
            transition.setToX(0);
            transition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void userNameAction(ActionEvent event) {
        passwordText.requestFocus();
    }
}
