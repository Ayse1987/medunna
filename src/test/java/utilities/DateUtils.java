package utilities;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static void main(String[] args) {
        LocalDateTime dateObj= LocalDateTime.now();
        System.out.println(getDate());
    }

    public static String getDate(){

        LocalDateTime dateObj= LocalDateTime.now();
        DateTimeFormatter formatDate=DateTimeFormatter.ofPattern("dd-MM-yyyy"); //HH:mm:ss we can add

        return dateObj.format(formatDate);
    }
}
