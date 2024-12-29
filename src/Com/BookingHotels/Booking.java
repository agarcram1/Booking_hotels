package Com.BookingHotels;

import java.time.LocalDate;

public class Booking {
    private String nameClient; // Nombre del cliente
    private String lastNameClient; // Apellido del cliente
    private String email; // Correo electrónico
    private String nacionality; // Nacionalidad
    private String phone; // Teléfono
    private String arrivalTime; // Hora de llegada
    private Acomodation acomodation; // Alojamiento reservado
    private Room room; // Habitación reservada
    private LocalDate checkIn; // Fecha de llegada
    private LocalDate checkOut; // Fecha de salida
    private double price; // Precio total de la reserva
    private int numAdults; // Número de adultos
    private int numChildren; // Número de niños

    public Booking(String nameClient, String lastNameClient, String email, String nacionality, String phone, String arrivalTime, Acomodation acomodation, Room room, LocalDate checkIn, LocalDate checkOut, double price, int numAdults, int numChildren) {
        this.nameClient = nameClient;
        this.lastNameClient = lastNameClient;
        this.email = email;
        this.nacionality = nacionality;
        this.phone = phone;
        this.arrivalTime = arrivalTime;
        this.acomodation = acomodation;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
    }

    public void showDetails() {
        System.out.println("Name: " + nameClient + " " + lastNameClient);
        System.out.println("Email: " + email);
        System.out.println("Nacionality: " + nacionality);
        System.out.println("Phone: " + phone);
        System.out.println("Arrival time: " + arrivalTime);
        System.out.println("Accommodation: " + acomodation.getName());
        System.out.println("Room: " + room.getRoomType());
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Price: $" + price);
        System.out.println("Number of adults: " + numAdults);
        System.out.println("Number of children: " + numChildren);
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getEmail() {
        return email;
    }

    public Acomodation getAlojamiento() {
        return acomodation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }
}