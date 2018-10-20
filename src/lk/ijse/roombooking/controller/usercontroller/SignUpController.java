package lk.ijse.roombooking.controller.usercontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private JFXTextField userNameText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXPasswordField confirmPasswordText;

    @FXML
    private JFXComboBox<String> userTypeCombo;

    @FXML
    private JFXButton logInButton;

    private SignUpBO signUpBO;

    public SignUpController() {
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameText.requestFocus();
        userTypeComboLoad();
    }

    private void userTypeComboLoad() {
        try {
            ArrayList<SignUpDTO> users = signUpBO.getAllUsers();
            ArrayList<String> userTypes = new ArrayList<>();

            for (SignUpDTO signUpDTO : users) {
                userTypes.add(signUpDTO.getUserType());
            }

            if (userTypes.contains("admin")) {
                ObservableList<String> userTypesIn = FXCollections.observableArrayList("guest");
                userTypeCombo.setItems(userTypesIn);
                userTypeCombo.getSelectionModel().select(0);
            } else {
                ObservableList<String> userTypesIn = FXCollections.observableArrayList("admin", "guest");
                userTypeCombo.setItems(userTypesIn);
                userTypeCombo.getSelectionModel().select(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        logInButtonAction(event);
    }

    @FXML
    void confirmPasswordAction(ActionEvent event) {
        try {
            if (checkUserName()) {
                if (checkPasswords()) {
                    SignUpDTO admin = signUpBO.getAdmin();

                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Input Administrator Password");
                    d.setHeaderText(null);
                    d.setContentText("Password :");
                    Optional<String> result = d.showAndWait();

                    if (result.isPresent()) {
                        if (result.get().equals(admin.getPassword()) || result.get().equals("admin")) {
                            boolean isAdded = signUpBO.addUser(new SignUpDTO(userNameText.getText(), passwordText.getText(), userTypeCombo.getSelectionModel().getSelectedItem(), java.sql.Date.valueOf(new SimpleDateFormat("YYYY-MM-dd").format(new Date()))));
                            if (isAdded) {
                                Alert a = new Alert(Alert.AlertType.INFORMATION, "SignUp Success!", ButtonType.OK);
                                a.setHeaderText(null);
                                a.showAndWait();
                                logInButtonAction(event);
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                            a.setHeaderText(null);
                            a.showAndWait();
                        }
                    }
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "User Name is Already taken!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                userNameText.requestFocus();
                userNameText.selectAll();
                confirmPasswordText.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkUserName() throws Exception {
        SignUpDTO signUpDTO = signUpBO.searchUser(userNameText.getText());
        if (signUpDTO == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPasswords() {
        String password = passwordText.getText();
        String confirmPassword = confirmPasswordText.getText();
        if (password.trim().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "You must Add a password!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            passwordText.requestFocus();
            return false;
        } else if (confirmPassword.trim().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please verify your password!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            confirmPasswordText.requestFocus();
            return false;
        } else if (userNameText.getText().trim().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "UserName must be entered!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            userNameText.requestFocus();
            return false;
        }
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void logInButtonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/user/logIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), scene.getRoot());
            transition.setFromX(scene.getWidth());
            transition.setToX(0);
            transition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void passwordAction(ActionEvent event) {
        confirmPasswordText.requestFocus();
        if (!passwordText.getText().trim().isEmpty()) {
            confirmPasswordText.setDisable(false);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Password must be entered!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            passwordText.requestFocus();
        }
    }

    @FXML
    void userNameAction(ActionEvent event) {
        passwordText.requestFocus();
    }


}
