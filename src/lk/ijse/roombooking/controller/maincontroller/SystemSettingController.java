package lk.ijse.roombooking.controller.maincontroller;

import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.LogInBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.LogInDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SystemSettingController implements Initializable {

    @FXML
    private AnchorPane settingPane;

    private LogInBO logInBO;
    private SignUpBO signUpBO;

    public SystemSettingController() {
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
        logInBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), settingPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void changePasswordAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/user/changePassword.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changePriceFacilitiesAction(ActionEvent event) {
        Parent root = null;
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/facility/changePriceOfFacilities.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root));
                    stage.show();

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changePriceRoomsAction(ActionEvent event) {
        Parent root = null;
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/room/changePriceOfRooms.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changePriceServicesAction(ActionEvent event) {
        Parent root = null;
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/service/changePriceOfServices.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteUserAction(ActionEvent event) {
        try {
            ArrayList<LogInDTO> allLogins = logInBO.getAllLogins();
            String userName = null;
            for (LogInDTO logInDTO : allLogins) {
                userName = logInDTO.getUserName();
            }

            SignUpDTO signUpDTO = signUpBO.searchUser(userName);

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input your Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(signUpDTO.getPassword())) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure want to Delete this account?", ButtonType.YES, ButtonType.NO);
                    a.setHeaderText(null);
                    Optional<ButtonType> action = a.showAndWait();
                    if (action.get() == ButtonType.YES) {
                        boolean isDeleted = signUpBO.deleteUser(userName);
                        if (isDeleted) {
                            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/user/logIn.fxml"));
                            Scene logInScenes = new Scene(root);
                            Stage logInStages = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            logInStages.setScene(logInScenes);
                            logInStages.centerOnScreen();
                        } else {
                            Alert wrong = new Alert(Alert.AlertType.INFORMATION, "Something is Wrong!", ButtonType.OK);
                            wrong.setHeaderText(null);
                            wrong.showAndWait();
                        }
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
