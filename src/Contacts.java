import java.util.HashMap;
import java.util.Map;

public class Contacts {

    private Map<String, Contact> phonebook;
    private final String noContactError = "Такого контакта нет!";

    protected Contacts() {
        phonebook = new HashMap<>();
    }

    public void addContact(Contact contact) {
        String phone = contact.getPhone();
        phonebook.put(phone, contact);
    }

    public void removeContactByNameAndSurname(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                phonebook.remove(c.getPhone());
            } else System.out.println(noContactError);
        }
    }

    public void removeContact(Contact contact) {
        if (phonebook.containsKey(contact.getPhone())) {
            phonebook.remove(contact.getPhone());
        } else System.out.println(noContactError);
    }

    public Contact searchContactByNumber(String phone) {
        return phonebook.getOrDefault(phone, null);
    }

    public Contact returnContact(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                return c;
            }
        }
        System.out.println(noContactError);
        return null;
    }

    public void showAllContacts(Contacts contacts) {
        System.out.println("Ваша телефонная книга:");
        if (phonebook.isEmpty()) {
            System.out.println("Телефонная книга пуста!\n");
        } else for (Map.Entry<String, Contact> entry : phonebook.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println();
    }
}