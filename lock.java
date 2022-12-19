import java.time.format.*;
import java.util.zip.DataFormatException;
import java.time.LocalDateTime;
public class lock{
    public static void main(String[] args){
        DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        System.out.println(formate.format(time));
    }
}