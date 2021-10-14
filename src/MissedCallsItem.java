import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class MissedCallsItem {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd2");

    private LocalDateTime time;
    private String phone;

    public MissedCallsItem(LocalDateTime time, String phone) {
        this.time = time;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return time.format(formatter) + " - " + phone;
    }
}