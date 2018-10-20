package lk.ijse.roombooking.controller.usercontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.LogInBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.LogInDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {

    @FXML
    private JFXTextField userNameText;

    @FXML
    private JFXPasswordField newPasswordText;

    @FXML
    private JFXButton changeButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView closeImage;

    @FXML
    private JFXPasswordField currentPasswordText;

    private SignUpBO signUpBO;
    private LogInBO logInBO;

    public ChangePasswordController() {
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
        logInBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentPasswordText.requestFocus();
        userNameTextLoad();
    }

    private void userNameTextLoad() {
        try {
            ArrayList<LogInDTO> allLogins = logInBO.getAllLogins();
            for (LogInDTO logInDTO : allLogins) {
                userNameText.setText(logInDTO.getUserName());
            }
            currentPasswordText.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        emptyFields();
    }

    private void emptyFields() {
        currentPasswordText.setText(null);
        newPasswordText.setText(null);
        currentPasswordText.requestFocus();
    }

    @FXML
    void changeButtonAction(ActionEvent event) {
        try {
            SignUpDTO signUpDTO = signUpBO.searchUser(userNameText.getText());
            if (currentPasswordText.getText().equals(signUpDTO.getPassword())) {
                boolean isUpdated = signUpBO.updateUser(new SignUpDTO(userNameText.getText(), newPasswordText.getText(), signUpDTO.getUserType(), signUpDTO.getSignUpDate()));
                if (isUpdated) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Password Changed!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    closeImageCliked();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Incorrect!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                emptyFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeImageCliked() {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    void currentPasswordAction(ActionEvent event) {
        newPasswordText.requestFocus();
    }

    @FXML
    void newPasswordAction(ActionEvent event) {
        changeButtonAction(event);
    }


}
