import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class MissedCallsItem {

    private LocalDateTime time;
    private String phone;

    public MissedCallsItem(LocalDateTime time, String phone) {
        this.time = time;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd2");
        return time.format(formatter) + " - " + phone;
    }
}

