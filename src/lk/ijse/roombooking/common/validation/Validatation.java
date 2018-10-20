package lk.ijse.roombooking.common.validation;

public class Validatation {

    public static boolean nameValidate(String name) {
        if (name.matches("[A-z].[^0-9<>?/!@#$%^&*()_+=]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean nicValidate(String nic) {
        if ((nic.matches("[0-9]{9}[vV]")) || (nic.matches("[0-9]{12}"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean pricesValidate(String price) {
        if (price.matches("[0-9].+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean telephoneNoValidate(String telephoneNo) {
        if ((telephoneNo.matches("[0-9]{10}")) || (telephoneNo.matches("[+][0-9]{11}")) || (telephoneNo.matches("[0-9]{3}[-][0-9]{7}"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean integerValueValidate(String value) {
        if (value.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean stringsValidate(String string) {
        if (string.matches("[A-z\\s,]+")) {
            return true;
        } else {
            return false;
        }
    }

}
