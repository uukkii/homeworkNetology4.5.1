import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner console = new Scanner(System.in);
    private static final String defaultSwitchAnswer = "Неправильный ввод!";

    public static void main(String[] args) {
        Contacts contacts = new Contacts();
        MissedCalls missedCalls = new MissedCalls();

        System.out.println("Добро пожаловать в программу 'Телефонная книга'!");
        loop:
        while (true) {
            System.out.println("""
                    Введите номер нужной команды:\s
                    1. Телефонная книга.
                    2. Добавить пропущенный вызов.
                    3. Вывести все пропущенные вызовы.
                    4. Отчистить пропущенные вызовы.
                    Или введите '0', чтобы выйти из программы.""");
            int input = console.nextInt();
            switch (input) {
                case 1 -> {
                    loopIt:
                    while (true) {
                        System.out.println("""
                                Введите номер нужной команды:\s
                                1. Добавить контакт.
                                2. Удалить контакт.
                                3. Найти контакт.
                                4. Показать все контакты.
                                Или введите '0', чтобы вернуться в главное меню.""");
                        int input1 = console.nextInt();
                        switch (input1) {
                            case 1 -> addContact(contacts);
                            case 2 -> removeContact(contacts);
                            case 3 -> searchContact(contacts);
                            case 4 -> showAllContacts(contacts);
                            case 0 -> {
                                break loopIt;
                            }
                            default -> System.out.println(defaultSwitchAnswer);
                        }
                    }
                }
                case 2 -> addMissedCall(missedCalls);
                case 3 -> showAllMissedCalls(missedCalls);
                case 4 -> deleteAllMissedCalls(missedCalls);
                case 0 -> {
                    System.out.println("Спасибо за использование!");
                    break loop;
                }
                default -> System.out.println(defaultSwitchAnswer);
            }
        }
    }

    public static void addContact(Contacts contacts) {
        console.nextLine();
        System.out.println("Введите имя и фамилию контакта через пробел (Пример: Иван Иванов):");
        String[] nameAndSurname = console.nextLine().split(" ");
        String name = nameAndSurname[0], surname = nameAndSurname[1];

        System.out.println("Введите номер телефона контакта:");
        String phone = console.nextLine();

        System.out.println("В какую группу добавить контакт? (Семья/Друзья/Работа)");
        String groupIn = console.nextLine();
        if ("Семья".equals(groupIn)) {
            groupIn = "FAMILY";
        } else if ("Работа".equals(groupIn)) {
            groupIn = "WORK";
        } else if ("Друзья".equals(groupIn)) {
            groupIn = "FRIENDS";
        } else {
            System.out.println("Такой группы нет!\n");
        }
        Group group = Group.valueOf(groupIn);

        Contact contact = new Contact(name, surname, phone, group);
        contacts.addContact(contact);
    }

    public static void removeContact(Contacts contacts) {
        contacts.showAllContacts();
        console.nextLine();
        System.out.println("Введите имя и фамилию контакта, которого необходимо удалить (Пример: Иван Иванов):");
        String[] input = console.nextLine().split(" ");
        String name = input[0], surname = input[1];
        contacts.removeContactByNameAndSurname(name, surname);
    }

    public static void searchContact(Contacts contacts) {
        console.nextLine();
        System.out.println("Введите номер телефона контакта, которого необходимо найти:");
        String phone = console.nextLine();
        contacts.searchContactByNumber(phone);
    }

    public static void showAllContacts(Contacts contacts) {
        contacts.showAllContacts();
    }

    public static void addMissedCall(MissedCalls missedCalls) {
        console.nextLine();
        System.out.println("Введите номер абонента, от которого получен пропущенный звонок:");
        String phone = console.nextLine();
        missedCalls.addMissedCall(phone);
    }

    public static void deleteAllMissedCalls(MissedCalls missedCalls) {
        missedCalls.deleteAllMissedCalls();
    }

    public static void showAllMissedCalls(MissedCalls missedCalls) {
        List<MissedCallsItem> missedCallsItemList = missedCalls.missedCallsToList();
        if (missedCallsItemList.isEmpty()) {
            System.out.println("Пропущенных вызовов нет!\n");
        } else for (MissedCallsItem m : missedCallsItemList) {
            System.out.println(m);
        }
        System.out.println();
    }
}
