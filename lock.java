import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class lock{
    public static void main(String[] args){
        DateTimeFormatter formate = DateTimeFormatter.ofPattern(" HH:mm");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime time = LocalTime.now();
        time = time.plusMinutes(20);
        System.out.println(time.format(formate));
        // gnome-screensaver-command -l
        
    }
}