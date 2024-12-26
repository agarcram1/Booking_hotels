package Com.BookingHotels;

import java.time.LocalDate;

public class Booking {
    private String nameClient; // Nombre del cliente
    private String lastNameClient; // Apellido del cliente
    private String email; // Correo electrónico
    private String nacionality; // Nacionalidad
    private String phone; // Teléfono
    private String arrivalTime; // Hora de llegada
    private Alojamiento alojamiento; // Alojamiento reservado
    private Room room; // Habitación reservada
    private LocalDate checkIn; // Fecha de llegada
    private LocalDate checkOut; // Fecha de salida
    private double price; // Precio total de la reserva

    public Booking(String nameClient, String lastNameClient, String email, String nacionality, String phone, String arrivalTime, Alojamiento alojamiento, Room room, LocalDate checkIn, LocalDate checkOut, double price) {
        this.nameClient = nameClient;
        this.lastNameClient = lastNameClient;
        this.email = email;
        this.nacionality = nacionality;
        this.phone = phone;
        this.arrivalTime = arrivalTime;
        this.alojamiento = alojamiento;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }

    public void showDetails() {
        System.out.println("Name: " + nameClient + " " + lastNameClient);
        System.out.println("Email: " + email);
        System.out.println("Nacionality: " + nacionality);
        System.out.println("Phone: " + phone);
        System.out.println("Arrival time: " + arrivalTime);
        System.out.println("Accommodation: " + alojamiento.getName());
        System.out.println("Room: " + room.getRoomType());
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Price: $" + price);
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public String getPhone() {
        return phone;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }
}