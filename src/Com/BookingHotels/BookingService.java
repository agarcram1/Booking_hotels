package Com.BookingHotels;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    public List<Alojamiento> alojamientos; // Lista de alojamientos
    private List<Booking> bookings; // Lista de reservas

    public BookingService(List<Alojamiento> alojamientos, List<Booking> bookings) {
        this.alojamientos = alojamientos;
        this.bookings = new ArrayList<>();
    }

    public List<Alojamiento> searchForAccomodation(String ciudad, String tipo, LocalDate inicio, LocalDate fin, int adultos, int niños, int habitaciones) {
        List<Alojamiento> resultados = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCity().equalsIgnoreCase(ciudad) && alojamiento.getAccommodationType().equalsIgnoreCase(tipo)) {
                double precioTotal = calculatePriceWithAdjustments(inicio, fin, alojamiento.getRooms().get(0).getPrice(), habitaciones);
                alojamiento.setCalculatedPrice(precioTotal);
                resultados.add(alojamiento);
            }
        }
        return resultados;
    }

    public double calculatePriceWithAdjustments(LocalDate checkIn, LocalDate checkOut, double pricePerNight, int numberOfRooms) {
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = days * pricePerNight * numberOfRooms;

        boolean lastFiveDays = checkOut.getDayOfMonth() > 25;
        boolean between10and15 = checkIn.getDayOfMonth() <= 15 && checkOut.getDayOfMonth() >= 10;
        boolean between5and10 = checkIn.getDayOfMonth() <= 10 && checkOut.getDayOfMonth() >= 5;

        if (lastFiveDays) {
            totalPrice *= 1.15; // Incremento de 15%
        } else if (between10and15) {
            totalPrice *= 1.10; // Incremento de 10%
        } else if (between5and10) {
            totalPrice *= 0.92; // Descuento de 8%
        }

        return totalPrice;
    }

    public List<Room> confirmRooms(Alojamiento alojamiento, LocalDate checkIn, LocalDate checkOut, int numberOfRooms) {
        List<Room> isAvailable = new ArrayList<>();
        for (Room room : alojamiento.getRooms()) {
            if (room.isAvailable()) {
                isAvailable.add(room);
            }
        }
        return isAvailable;
    }

    public String makerReservation(String nameClient, String lastNameClient, String email, String nacionality, String phone, String arrivalTime, Alojamiento alojamiento, Room room, LocalDate checkIn, LocalDate checkOut, double price) {
        Booking booking = new Booking(nameClient, lastNameClient, email, nacionality, phone, arrivalTime, alojamiento, room, checkIn, checkOut, price);
        bookings.add(booking);
        room.setAvailable(false); // Reduce la disponibilidad de la habitación
        return "Se ha realizado la reserva con éxito.";
    }

    public String updateReservation(String email, Booking existingBooking, Room newRoom) {
        if (existingBooking.getEmail().equals(email)) {
            existingBooking.showDetails();
            newRoom.setAvailable(false); // Restaura la disponibilidad de la habitación
            existingBooking.getRoom().setAvailable(true); // Reduce la disponibilidad de la habitación
            existingBooking.setRoom(newRoom);
            return "Reserva actualizada con éxito.";
        }
        return "No se ha podido actualizar la reserva.";
    }
}