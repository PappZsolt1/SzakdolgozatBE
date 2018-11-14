package myapp.SzakdolgozatBE;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyValidator {

    public MyValidator() {
    }
    
    public boolean validateText(String text, int max) {
        return (text != null && text.trim().isEmpty() == false && text.length() <= max
                && text.substring(0, 1).matches("\\S") == true
                && text.substring(text.length() - 1).matches("\\S") == true);
    }
    
    public boolean validateNumber(int number, int min, int max) {
        return (number >= min && number <= max);
    }
    
    public boolean validateDate(String date, int min, int max) {
        int year;
        try {
            year = Integer.parseInt(date.substring(0, 4));
        } catch (NumberFormatException e) {
            return false;
        }
        if (year < min || year > max) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd.");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public boolean validateLength(String length) {
        if (length.matches("^[0-9]{1,3} óra [0-9]{1,2} perc$|^[0-9]{1,2} perc$") == false) {
            return false;
        }
        if (length.length() > 10) {
            int hour;
            try {
                hour = Integer.parseInt(length.substring(0, length.indexOf("ó") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (hour > 100 || hour < 0) {
                return false;
            }
            int minute;
            try {
                minute = Integer.parseInt(length.substring(length.indexOf("a") + 2, length.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        } else {
            int minute;
            try {
                minute = Integer.parseInt(length.substring(0, length.indexOf("p") - 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        }
        return true;
    }
}
