import java.util.*;

public class TravelBookingSystem {

    static class Flight {
        int id;
        String from, to, date, airline;
        double price;

        Flight(int id, String from, String to, String date, String airline, double price) {
            this.id = id; this.from = from; this.to = to;
            this.date = date; this.airline = airline; this.price = price;
        }

        public String toString() {
            return "[" + id + "] " + airline + " | " + from + " -> " + to +
                   " | " + date + " | R" + price;
        }
    }

    static class Hotel {
        int id;
        String name, location, checkIn, checkOut;
        double pricePerNight;

        Hotel(int id, String name, String location, String checkIn, String checkOut, double pricePerNight) {
            this.id = id; this.name = name; this.location = location;
            this.checkIn = checkIn; this.checkOut = checkOut; this.pricePerNight = pricePerNight;
        }

        public String toString() {
            return "[" + id + "] " + name + " | " + location +
                   " | Check-in: " + checkIn + " | Check-out: " + checkOut +
                   " | R" + pricePerNight + "/night";
        }
    }

    static class Transport {
        int id;
        String type, from, to, date;
        double price;

        Transport(int id, String type, String from, String to, String date, double price) {
            this.id = id; this.type = type; this.from = from;
            this.to = to; this.date = date; this.price = price;
        }

        public String toString() {
            return "[" + id + "] " + type + " | " + from + " -> " + to +
                   " | " + date + " | R" + price;
        }
    }

    static class Booking {
        int bookingId;
        String customerName, type, details;
        double totalPrice;

        Booking(int bookingId, String customerName, String type, String details, double totalPrice) {
            this.bookingId = bookingId; this.customerName = customerName;
            this.type = type; this.details = details; this.totalPrice = totalPrice;
        }

        public String toString() {
            return "Booking #" + bookingId + " | " + customerName +
                   " | " + type + " | " + details + " | Total: R" + totalPrice;
        }
    }

    static List<Flight>    flights    = new ArrayList<>();
    static List<Hotel>     hotels     = new ArrayList<>();
    static List<Transport> transports = new ArrayList<>();
    static List<Booking>   bookings   = new ArrayList<>();
    static int bookingCounter = 1;

    static void loadSampleData() {
        flights.add(new Flight(1, "Johannesburg", "Cape Town",          "2025-06-10", "FlySA",   1200.00));
        flights.add(new Flight(2, "Durban",        "Johannesburg",       "2025-06-12", "AirLink",  850.00));
        flights.add(new Flight(3, "Cape Town",     "Durban",             "2025-06-15", "Mango",    950.00));

        hotels.add(new Hotel(1, "Sun City Resort",  "Rustenburg",  "2025-06-10", "2025-06-14", 1500.00));
        hotels.add(new Hotel(2, "Cape Grace Hotel", "Cape Town",   "2025-06-10", "2025-06-13", 2200.00));
        hotels.add(new Hotel(3, "Garden Court",     "Durban",      "2025-06-12", "2025-06-15",  900.00));

        transports.add(new Transport(1, "Bus",   "Johannesburg", "Pretoria",          "2025-06-10", 150.00));
        transports.add(new Transport(2, "Taxi",  "Cape Town",    "Stellenbosch",      "2025-06-11", 300.00));
        transports.add(new Transport(3, "Train", "Durban",       "Pietermaritzburg",  "2025-06-13", 200.00));
    }

    static void viewFlights() {
        System.out.println("\n--- Available Flights ---");
        if (flights.isEmpty()) { System.out.println("No flights available."); return; }
        for (Flight f : flights) System.out.println(f);
    }

    static void bookFlight(Scanner sc) {
        viewFlights();
        System.out.print("Enter Flight ID to book: ");
        int id = sc.nextInt(); sc.nextLine();
        Flight selected = null;
        for (Flight f : flights) if (f.id == id) { selected = f; break; }
        if (selected == null) { System.out.println("Flight not found."); return; }
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Booking b = new Booking(bookingCounter++, name, "FLIGHT", selected.toString(), selected.price);
        bookings.add(b);
        System.out.println("Flight booked! " + b);
    }

    static void viewHotels() {
        System.out.println("\n--- Available Hotels ---");
        if (hotels.isEmpty()) { System.out.println("No hotels available."); return; }
        for (Hotel h : hotels) System.out.println(h);
    }

    static void bookHotel(Scanner sc) {
        viewHotels();
        System.out.print("Enter Hotel ID to book: ");
        int id = sc.nextInt(); sc.nextLine();
        Hotel selected = null;
        for (Hotel h : hotels) if (h.id == id) { selected = h; break; }
        if (selected == null) { System.out.println("Hotel not found."); return; }
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Number of nights: ");
        int nights = sc.nextInt(); sc.nextLine();
        double total = selected.pricePerNight * nights;
        Booking b = new Booking(bookingCounter++, name, "HOTEL", selected.toString(), total);
        bookings.add(b);
        System.out.println("Hotel booked! " + b);
    }

    static void viewTransport() {
        System.out.println("\n--- Available Transport ---");
        if (transports.isEmpty()) { System.out.println("No transport available."); return; }
        for (Transport t : transports) System.out.println(t);
    }

    static void bookTransport(Scanner sc) {
        viewTransport();
        System.out.print("Enter Transport ID to book: ");
        int id = sc.nextInt(); sc.nextLine();
        Transport selected = null;
        for (Transport t : transports) if (t.id == id) { selected = t; break; }
        if (selected == null) { System.out.println("Transport not found."); return; }
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Booking b = new Booking(bookingCounter++, name, "TRANSPORT", selected.toString(), selected.price);
        bookings.add(b);
        System.out.println("Transport booked! " + b);
    }

    static void viewMyBookings(Scanner sc) {
        System.out.print("Enter your name to view bookings: ");
        String name = sc.nextLine();
        boolean found = false;
        double total = 0;
        System.out.println("\n--- Your Bookings ---");
        for (Booking b : bookings) {
            if (b.customerName.equalsIgnoreCase(name)) {
                System.out.println(b);
                total += b.totalPrice;
                found = true;
            }
        }
        if (!found) System.out.println("No bookings found for " + name);
        else System.out.println("Grand Total: R" + total);
    }

    static void cancelBooking(Scanner sc) {
        System.out.print("Enter Booking # to cancel: ");
        int id = sc.nextInt(); sc.nextLine();
        Booking toRemove = null;
        for (Booking b : bookings) if (b.bookingId == id) { toRemove = b; break; }
        if (toRemove == null) { System.out.println("Booking not found."); return; }
        bookings.remove(toRemove);
        System.out.println("Booking #" + id + " cancelled.");
    }

    static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) { System.out.println("No bookings yet."); return; }
        for (Booking b : bookings) System.out.println(b);
    }

    public static void main(String[] args) {
        loadSampleData();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== TRAVEL BOOKING SYSTEM =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. View Hotels");
            System.out.println("4. Book a Hotel");
            System.out.println("5. View Transport");
            System.out.println("6. Book Transport");
            System.out.println("7. View My Bookings");
            System.out.println("8. Cancel a Booking");
            System.out.println("9. View All Bookings");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: viewFlights();      break;
                case 2: bookFlight(sc);     break;
                case 3: viewHotels();       break;
                case 4: bookHotel(sc);      break;
                case 5: viewTransport();    break;
                case 6: bookTransport(sc);  break;
                case 7: viewMyBookings(sc); break;
                case 8: cancelBooking(sc);  break;
                case 9: viewAllBookings();  break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}