import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public final class MissedCalls extends Contact{

    private static final Scanner console = new Scanner(System.in);

    public static final Map<LocalDateTime, String> missedCalls = new TreeMap<>();

    MissedCalls(String name, String surname, String phone, Enum<Group> group) {
        super(name, surname, phone, group);
    }

    public static void addMissedCall(Map<LocalDateTime, String> missedCalls) {
        System.out.println("Введите номер абонента, от которого получен пропущенный звонок:");
        String number = console.nextLine();
        if (phonebook.containsKey(number)) {
            number = phonebook.get(number).getContact();
        }
        missedCalls.put(LocalDateTime.now(), number);
    }

    public static void showAllMissedCalls(Map<LocalDateTime, String> missedCalls) {
        if (missedCalls.isEmpty()) {
            System.out.println("Пропущенных вызовов нет!\n");
        } else for(Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
            System.out.println(entry.getKey().toString() + " - " + entry.getValue());
        }
    }

    public static void deleteAllMissedCalls(Map<LocalDateTime, String> missedCalls) {
        missedCalls.clear();
        System.out.println("Журнал пропущенных вызовов очищен!\n");
    }

}
