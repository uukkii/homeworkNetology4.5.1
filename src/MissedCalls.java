import java.time.LocalDateTime;
import java.util.*;

public class MissedCalls {

    private Map<LocalDateTime, String> missedCalls;

    public MissedCalls() {
        missedCalls = new TreeMap<>();
    }

    public void addMissedCall(String phone) {
        missedCalls.put(LocalDateTime.now(), phone);
    }

    public List<MissedCallsItem> missedCallsToList() {
        List<MissedCallsItem> missedCallsList = new ArrayList<>();
        for (Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
            MissedCallsItem newMissedCall = new MissedCallsItem(entry.getKey(), entry.getValue());
            missedCallsList.add(newMissedCall);
        }
        return missedCallsList;
    }

    public void deleteAllMissedCalls() {
        missedCalls.clear();
        System.out.println("Журнал пропущенных вызовов очищен!\n");
    }
}