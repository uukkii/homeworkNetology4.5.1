import java.time.LocalDateTime;

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
        return time + " - " + phone;
    }
}

