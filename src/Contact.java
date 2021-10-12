import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Contact {

    private static final Scanner console = new Scanner(System.in);
    private final String name;
    private final String surname;
    private final String phone;
    private final Enum<Group> group;

    public static final Map<String, Contact> phonebook = new HashMap<>();

    Contact(String name, String surname, String phone, Enum<Group> group) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.group = group;
    }

    public static void addContact(Map<String, Contact> phonebook) {
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
        phonebook.put(phone, contact);
    }

    public static void removeContact(Map<String, Contact> phonebook) {
        System.out.println("Введите номер телефона контакта, которого необходимо удалить:");
        String key = console.nextLine();
        if (!phonebook.containsKey(key)) {
            System.out.println("Такого контакта нет!\n");
        } else phonebook.remove(key);
    }

    public static void searchContactByNumber(Map<String, Contact> phonebook) {
        System.out.println("Введите номер телефона контакта, которого необходимо найти:");
        String key = console.nextLine();
        if (!phonebook.containsKey(key)) {
            System.out.println("Такого контакта в книге нет!\n");
        } else System.out.println("Результаты поиска:\nПо номеру телефона: " + key + " найден контакт:\n" + phonebook.get(key) + "\n");
    }

    public static void showAllContacts(Map<String, Contact> phonebook) {
        System.out.println("Ваща телефонная книга:");
        for (Map.Entry<String, Contact> entry : phonebook.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println();
    }

    public String getContact() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + "[" + phone + "]" + "(" + group + ")";
    }

}
