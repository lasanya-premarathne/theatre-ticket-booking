import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Theatre{
    static int[] row1 = {0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] row2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] row3 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int seat, row, option;
    static FileWriter writeFile;
    static String line;
    static Scanner scanner = new Scanner(System.in);
    // Task 12
    private static final ArrayList <Ticket> TICKETS = new ArrayList<>();
    public static void main(String[] args) {
        option = -1;
        while(option != 0) {
            //initializing program
            System.out.println("Welcome to the New Theatre!");
            System.out.println("-------------------------------------------------");
            System.out.println("Please select an option: ");
            System.out.println("1) Buy a ticket ");
            System.out.println("2) Print seating area ");
            System.out.println("3) Cancel ticket ");
            System.out.println("4) List available seats ");
            System.out.println("5) Save to file ");
            System.out.println("6) Load from file ");
            System.out.println("7) Print ticket information and total price ");
            System.out.println("8) Sort tickets by price ");
            System.out.println("     0) Quit ");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter option : ");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> buy_ticket();
                case 2 -> print_seating_area();
                case 3 -> cancel_ticket();
                case 4 -> {
                    System.out.print("\nSeats available in row 1: ");
                    show_available(row1, row1.length);
                    System.out.print("\n\nSeats available in row 2: ");
                    show_available(row2, row2.length);
                    System.out.print("\n\nSeats available in row 3: ");
                    show_available(row3, row3.length);
                }
                case 5 -> save();
                case 6 -> load();
                case 7 -> show_tickets_info();
                case 8 -> sort_tickets(TICKETS);
                case 0 -> {
                    System.out.println("Program quits");
                }
                default -> {
                    System.out.println("Input a valid number");
                    main(args);
                }
            }
            System.out.print("\n\n\n\n");
        }
    }
    public static void buy_ticket() {
        //for person
        System.out.print("\nEnter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();

        System.out.print("\nEnter row: ");
        row = scanner.nextInt();
        while (row > 3 || row < 1) {
            System.out.println("Invalid row");
            System.out.print("\nEnter row: ");
            row = scanner.nextInt();
        }
        int [] arrRow;
        //validating the row
        switch (row) {
            case 1 -> {
                arrRow = row1;
                method_seat(arrRow);
            }
            case 2 -> {
                arrRow = row2;
                method_seat(arrRow);
            }
            case 3 -> {
                arrRow = row3;
                method_seat(arrRow);
            }
        }
        System.out.print("   Row 1 : £10\n   Row 2 : £20\n   Row 3 : £30\nEnter price: ");
        double price = scanner.nextDouble();
        Ticket ticket = new Ticket (row, seat, price, new Person(name, surname, email));
        TICKETS.add(ticket);
        //to check if the arrays are getting updated
        System.out.println("\n"+Arrays.toString(row1));
        System.out.println(Arrays.toString(row2));
        System.out.println(Arrays.toString(row3));
    }
    public static void method_seat(int[] arrayRow){
        System.out.print("Enter seat: ");
        seat = scanner.nextInt();
        if (seat > arrayRow.length || seat < 1) {
            System.out.println("Invalid seat");
            buy_ticket();
        }
        if (arrayRow[seat-1] == 0){
            arrayRow[seat-1] = 1;
        }else{
            System.out.println("Seat taken");
            buy_ticket();
        }
    }
    public static void print_seating_area(){
        System.out.println("     ***********");
        System.out.println("     *  STAGE  *");
        System.out.println("     ***********");
        System.out.print("    ");
        conversions(row1.length, row1);
        System.out.print("\n");
        System.out.print("  ");
        conversions(row2.length, row2);
        System.out.print("\n");
        System.out.print("");
        conversions(row3.length, row3);
        System.out.print("\n");
    }
    public static void conversions(int rowLength, int[] rowCon){
        //converting 1s to x and 0s to O
        for (int i = 0; i < rowLength; i++){
            if (i == rowLength/2){
                System.out.print(" ");
            }
            if (rowCon[i] == 0) {
                System.out.print("O");
            }else{
                System.out.print("X");
            }
        }
    }
    public static void cancel_ticket() {
        System.out.print("Enter row: ");
        row = scanner.nextInt();
        int[] arrRowCancel=new int[0];
        //validating the row
        if (row > 3 || row < 1) {
            System.out.println("Invalid row\n");
            cancel_ticket();
        } else {
            switch (row) {
                case 1 -> arrRowCancel = row1;
                case 2 -> arrRowCancel = row2;
                case 3 -> arrRowCancel = row3;
            }
            cancel_seat(arrRowCancel);
        }
        //to check if the arrays are getting updated
        System.out.println(Arrays.toString(row1));
        System.out.println(Arrays.toString(row2));
        System.out.println(Arrays.toString(row3));
        for (int t=0; t<TICKETS.size();t++){
            if (t==row && t==seat){
                TICKETS.remove(t);
                System.out.println("Ticket cancelled");
                break;
            }
        }
    }
    public static void cancel_seat(int[] arrayRowCancel){
        System.out.print("Enter seat to be canceled: ");
        seat = scanner.nextInt();
        //validating the seat
        if (seat > arrayRowCancel.length || seat < 1) {
            System.out.println("Invalid seat\n");
            cancel_ticket();
        } else {
            if (arrayRowCancel[seat - 1] == 1) {
                arrayRowCancel[seat - 1] = 0;
            } else {
                System.out.println("Seat not taken\n");
                cancel_ticket();
            }
        }
    }
    public static void show_available(int[] rowAvailable, int rowLength){
        for (int a= 0; a < rowLength; a++){
            if (rowAvailable[a] == 0) {
                System.out.print(a+1);
                if (a != rowLength-1) {
                    System.out.print(", ");
                }
            }
        }
    }
    public static void save(){
        try {
            // Create a file
            File createFile = new File("save.txt");
            // Open the file for writing
            writeFile = new FileWriter(createFile);
            writeFile.write("Writing arrays on a file named 'save'");
            writeFile.write("\nRow 1 : ");
            save_row(row1, row1.length);
            writeFile.write("\nRow 2 : ");
            save_row(row2, row2.length);
            writeFile.write("\nRow 3 : ");
            save_row(row3, row3.length);
            // Save and close the file
            writeFile.flush();
            writeFile.close();
            System.out.println("File created successfully!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void save_row(int[] save_row, int rowLength){
        try {
            // Write the elements of an array to the file in a single line separated by commas
            for (int s = 0; s< rowLength; s++) {
                if (save_row[s]==0){
                    writeFile.write("0");
                }else{
                    writeFile.write("1");
                }
                if (s != rowLength - 1) {
                    writeFile.write(",");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void load(){
        String fileName = "save.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();// Skip first line
            line = br.readLine();// Process second line for array1
            line_to_array(row1.length, row1);
            line = br.readLine();// Process third line for array2
            line_to_array(row2.length, row2);
            line = br.readLine();// Process fourth line for array2
            line_to_array(row3.length, row3);
        }catch (IOException e) {
            e.printStackTrace();
        }
        // printing the arrays in a character based way
        System.out.print("Availability in row 1 :");
        for (int j : row1) {
            System.out.print(j + " ");
        }
        System.out.print("\nAvailability in row 1 :");
        for (int k : row2) {
            System.out.print(k + " ");
        }
        System.out.print("\nAvailability in row 1 :");
        for (int j : row3) {
            System.out.print(j + " ");
        }
    }
    public static void line_to_array(int rowLength, int[] arrayLine){
        for (int l= 0; l < rowLength; l++) {
            int value = Character.getNumericValue(line.charAt(8 + (l * 2)));
            arrayLine[l] = value;
        }
    }
    public static void show_tickets_info() {
        double total = 0.0;
        for (Ticket tickets : TICKETS) {
            System.out.println("Ticket" + tickets+1+" : Row " + tickets.row + " Seat " + tickets.seat+ " (£" + tickets.price + ")");
            total += tickets.price;
        }

        System.out.println("Total price: £" + total);

    }
    private static ArrayList<Ticket> sort_tickets(ArrayList<Ticket>TICKETS) {
        ArrayList<Ticket> sortedTickets = new ArrayList<>(TICKETS);
        int minIndex;
        for (int step = 0; step < sortedTickets.size() - 1; step++) {
            minIndex = step;
            for (int i = step + 1; i <= sortedTickets.size() - 1; i++) {
                if (sortedTickets.get(i).getPrice() < sortedTickets.get(minIndex).getPrice()) {
                    minIndex = i;
                }
            }
            //puts min element in its correct position in the sorted list
            Ticket temp = sortedTickets.get(step);
            sortedTickets.set(step, sortedTickets.get(minIndex));
            sortedTickets.set(minIndex, temp);
        }
        //prints the sorted tickets info
        System.out.println("\nSorted tickets according to the price:\n");
        for (Ticket ticket : sortedTickets) {
            System.out.println("Row " + Ticket.getRow() + ", seat " + Ticket.getSeat() + ", Price:" + Ticket.getPrice() + ", Person:" + Ticket.getPerson().getName());
            System.out.print("\n");
        }
        return sortedTickets;
    }
}