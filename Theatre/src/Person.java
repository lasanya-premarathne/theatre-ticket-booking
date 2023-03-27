public class Person {
    static String name, surname, email;
    public Person(String name, String surname,String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    //getters
    public static String getName() {
        return name;
    }
    public static String getSurname() {
        return surname;
    }
    public static String getEmail() {
        return email;
    }
}
