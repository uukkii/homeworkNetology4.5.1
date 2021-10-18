import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final Scanner console = new Scanner(System.in);
    private static final String defaultSwitchAnswer = "Неправильный ввод!";
    private static final Contacts contacts = new Contacts();
    private static final MissedCalls missedCalls = new MissedCalls();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    public static void main(String[] args) {
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
                                4. Редактировать контакт.
                                5. Показать все контакты.
                                Или введите '0', чтобы вернуться в главное меню.""");
                        int input1 = console.nextInt();
                        switch (input1) {
                            case 1 -> addContact(contacts);
                            case 2 -> removeContact(contacts);
                            case 3 -> searchContact(contacts);
                            case 4 -> editContact(contacts);
                            case 5 -> showAllContacts(contacts);
                            case 0 -> {
                                break loopIt;
                            }
                            default -> System.out.println(defaultSwitchAnswer);
                        }
                    }
                }
                case 2 -> addMissedCall(missedCalls);
                case 3 -> showAllMissedCalls(missedCalls, contacts);
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
        Group group;
        if ("Семья".equals(groupIn)) {
            group = Group.FAMILY;
        } else if ("Работа".equals(groupIn)) {
            group = Group.WORK;
        } else if ("Друзья".equals(groupIn)) {
            group = Group.FRIENDS;
        } else {
            System.out.println("Такой группы нет!\n");
            group = Group.DEFAULT;
        }

        Contact contact = new Contact(name, surname, phone, group);
        contacts.addContact(contact);
    }

    public static void removeContact(Contacts contacts) {
        contacts.showAllContacts(contacts);
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
        Contact foundContact = contacts.searchContactByNumber(phone);
        if (foundContact == null) {
            System.out.println("Такого контакта в телефонной книге нет!");
        } else System.out.println("Результаты поиска:\nПо номеру телефона: " + phone + " найден контакт:\n" + foundContact + "\n");
    }

    public static void showAllContacts(Contacts contacts) {
        contacts.showAllContacts(contacts);
    }

    public static void editContact(Contacts contacts) {
        contacts.showAllContacts(contacts);
        console.nextLine();
        System.out.println("Введите имя и фамилию контакта, которого необходимо отредактировать (Пример: Иван Иванов):");
        String[] input = console.nextLine().split(" ");
        String name = input[0], surname = input[1];
        Contact editingContact = contacts.returnContact(name, surname);
        if (editingContact != null)
            loopEdition:
                    while (true) {
                        System.out.println("Информация о редактируемом контакте: " + editingContact);
                        System.out.println("""
                                Что необходимо изменить?:\s
                                1. Имя контакта.
                                2. Фамилию контакта.
                                3. Номер телефона контакта.
                                4. Группу принадлежности контакта.
                                Или введите '0' для возврата в прошлое меню.""");
                        int input2 = console.nextInt();
                        switch (input2) {
                            case 1 -> {
                                console.nextLine();
                                System.out.println("Введине новое имя контакта:");
                                String newName = console.nextLine();
                                editingContact.setName(newName);
                            }
                            case 2 -> {
                                console.nextLine();
                                System.out.println("Введине новую фамилию контакта:");
                                String newSurname = console.nextLine();
                                editingContact.setSurname(newSurname);
                            }
                            case 3 -> {
                                console.nextLine();
                                System.out.println("Введите новый номер телефона контакта:");
                                String newPhone = console.nextLine();
                                contacts.removeContact(editingContact);
                                editingContact.setPhone(newPhone);
                                contacts.addContact(editingContact);
                            }
                            case 4 -> {
                                console.nextLine();
                                System.out.println("Введите новую группу принадлежности контакта (Семья/Друзья/Работа):");
                                String groupIn = console.nextLine();
                                Group newGroup;
                                if ("Семья".equals(groupIn)) {
                                    newGroup = Group.FAMILY;
                                } else if ("Работа".equals(groupIn)) {
                                    newGroup = Group.WORK;
                                } else if ("Друзья".equals(groupIn)) {
                                    newGroup = Group.FRIENDS;
                                } else {
                                    System.out.println("Такой группы нет!\n");
                                    newGroup = Group.DEFAULT;
                                }
                                editingContact.setGroup(newGroup);
                            }
                            case 0 -> {
                                break loopEdition;
                            }
                            default -> System.out.println(defaultSwitchAnswer);
                        }
                    }
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

    public static void showAllMissedCalls(MissedCalls missedCalls, Contacts contacts) {
        List<MissedCallsItem> missedCallsItemList = missedCalls.missedCallsToList();
        if (missedCallsItemList.isEmpty()) {
            System.out.println("Пропущенных вызовов нет!");
        } else for (MissedCallsItem item : missedCallsItemList) {
            Contact comparedContact = contacts.searchContactByNumber(item.getPhone());
            if (comparedContact != null) {
                    System.out.println(item.getTime().format(formatter) + " - " + comparedContact);
                } else System.out.println(item.getTime().format(formatter) + " - " + item.getPhone());
            }
        System.out.println();
        }
    }