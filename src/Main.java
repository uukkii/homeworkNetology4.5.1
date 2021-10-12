import java.util.Scanner;

public class Main {

    private static final Scanner console = new Scanner(System.in);
    private static final String defaultSwitchAnswer = "Неправильный ввод!";

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
                                4. Показать все контакты.
                                Или введите '0', чтобы вернуться в главное меню.""");
                        int input1 = console.nextInt();
                        switch (input1) {
                            case 1 -> Contact.addContact(Contact.phonebook);
                            case 2 -> Contact.removeContact(Contact.phonebook);
                            case 3 -> Contact.searchContactByNumber(Contact.phonebook);
                            case 4 -> Contact.showAllContacts(Contact.phonebook);
                            case 0 -> {
                                break loopIt;
                            }
                            default -> System.out.println(defaultSwitchAnswer);
                        }
                        }
                    }
                    case 2 -> MissedCalls.addMissedCall(MissedCalls.missedCalls);
                    case 3 -> MissedCalls.showAllMissedCalls(MissedCalls.missedCalls);
                    case 4 -> MissedCalls.deleteAllMissedCalls(MissedCalls.missedCalls);
                    case 0 -> {
                        System.out.println("Спасибо за использование!");
                        break loop;
                    }
                    default -> System.out.println(defaultSwitchAnswer);
                }
            }
        }
    }
