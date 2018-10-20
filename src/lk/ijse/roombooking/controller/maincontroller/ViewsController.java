package lk.ijse.roombooking.controller.maincontroller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.roombooking.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ViewsController implements Initializable {

    @FXML
    private AnchorPane viewsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), viewsPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void chequeDetailFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("lk/ijse/roombooking/common/reports/payment/Cheque_Detail_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void departedFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/departing/Departing_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void facilityFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/facility&services/Facility_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void guestFullReportButtonAction(ActionEvent event) {
        String path = "/lk/ijse/roombooking/common/reports/guest/Guest_Full_Report.jasper";
        try {
            viewReport(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void notDepartedFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/departing/Not_Departed_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paymentFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/payment/Payment_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reservationFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/reservation/Reservation_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void roomFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/room/Room_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookedRoomsFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/room/RoomDetail_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void serviceFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/facility&services/Service_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reservedRoomsFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/reserved/Reservaed_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reservedFaciltyFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/facility&services/Facility_Detail_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reservedServiceFullReportButtonAction(ActionEvent event) {
        try {
            viewReport("/lk/ijse/roombooking/common/reports/facility&services/Service_Detail_Full_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewReport(String path) throws Exception {

        InputStream reportStream = getClass().getResourceAsStream(path);

        HashMap map = new HashMap();

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, map, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
