public final class Contact {

    private String name;
    private String surname;
    private String phone;
    private Enum<Group> group;

    public Contact(String name, String surname, String phone, Enum<Group> group) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.group = group;
    }

    public String getNameAndSurname() { return name + " " + surname; }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + " " + surname + " [" + phone + "] " + "(" + group + ")";
    }



}
