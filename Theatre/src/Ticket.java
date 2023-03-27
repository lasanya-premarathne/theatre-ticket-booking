public class Ticket {
    public static int row, seat;
    public static double price;
    public static Person person;
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    //getters
    public static int getRow() {
        return row;
    }
    public static int getSeat() {
        return seat;
    }
    public static double getPrice() {
        return price;
    }
    public static Person getPerson(){
        return person;
    }
    public static void print() {
        System.out.println("Name : "+Person.getName());
        System.out.println("Surname : "+Person.getSurname());
        System.out.println("Email : "+Person.getEmail());
        System.out.println("Row : "+ row);
        System.out.println("Seat : "+seat);
        System.out.println("Price : "+price);
    }
}
