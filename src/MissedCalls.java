import java.time.LocalDateTime;
import java.util.*;

public class MissedCalls extends Contacts {

    protected static Map<LocalDateTime, String> missedCalls;
    protected static List<MissedCallsItem> listOfMissedCalls = new ArrayList<>();

    public MissedCalls() { missedCalls = new TreeMap<>(); }

    public static void addMissedCall(String phone) {
        if (phonebook.containsKey(phone)) {
            phone = phonebook.get(phone).getNameAndSurname();
        }

        missedCalls.put(LocalDateTime.now(), phone);

        MissedCallsItem newMissedCall = new MissedCallsItem(LocalDateTime.now(), phone);
        listOfMissedCalls.add(newMissedCall);
    }

    public static void showAllMissedCalls() {
        if (missedCalls.isEmpty()) {
            System.out.println("Пропущенных вызовов нет!\n");
        } else for(Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
            System.out.println(entry.getKey().toString() + " - " + entry.getValue());
        }
    }

    public static void deleteAllMissedCalls() {
        missedCalls.clear();
        System.out.println("Журнал пропущенных вызовов очищен!\n");
    }
}
