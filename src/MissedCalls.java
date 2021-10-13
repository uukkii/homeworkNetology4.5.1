import java.time.LocalDateTime;
import java.util.*;

public class MissedCalls extends Contacts {

    protected static Map<LocalDateTime, String> missedCalls;

    public MissedCalls() {
        missedCalls = new TreeMap<>();
    }

    public static void addMissedCall(String phone) {
        missedCalls.put(LocalDateTime.now(), phone);
    }

    public static List<MissedCallsItem> missedCallsToList() {
        List<MissedCallsItem> missedCallsList = new ArrayList<>();
        for (Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
            String phone = entry.getValue();
            for (Contact c : phonebook.values()) {
                if (c.getPhone().equals(phone)) {
                    phone = c.getNameAndSurname();
                }
            }
            MissedCallsItem newMissedCall = new MissedCallsItem(entry.getKey(), phone);
            missedCallsList.add(newMissedCall);
        }
        return missedCallsList;
    }

    public static void deleteAllMissedCalls() {
        missedCalls.clear();
        System.out.println("Журнал пропущенных вызовов очищен!\n");
    }
}
