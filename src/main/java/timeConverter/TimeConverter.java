package timeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {

    LocalDateTime localDateTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LocalDateTime convertTime(String time) {
        String timeReplace = time.replace("T", " ");
        localDateTime = LocalDateTime.parse(timeReplace, formatter);
        return localDateTime;
    }
}
