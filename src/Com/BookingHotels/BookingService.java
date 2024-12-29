package Com.BookingHotels;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Acomodation> accommodations;
    private List<Booking> bookings;

    public BookingService(List<Acomodation> accommodations, List<Booking> bookings) {
        this.accommodations = accommodations;
        this.bookings = bookings;
    }

    public List<Acomodation> searchForAccomodation(String city, String type, LocalDate start, LocalDate end, int rooms) {
        List<Acomodation> results = new ArrayList<>();
        for (Acomodation accommodation : accommodations) {
            if (accommodation.getCity().equalsIgnoreCase(city) && accommodation.getAccommodationType().equalsIgnoreCase(type)) {
                double totalPrice = calculatePriceWithAdjustments(start, end, accommodation.getRooms().get(0).getPrice(), rooms);
                accommodation.setCalculatedPrice(totalPrice);
                results.add(accommodation);
            }
        }
        return results;
    }

    public double calculatePriceWithAdjustments(LocalDate checkIn, LocalDate checkOut, double pricePerNight, int numberOfRooms) {
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = days * pricePerNight * numberOfRooms;

        if (days <= 5) {
            totalPrice *= 1.15; // Incremento de 15%
        } else if (days >= 10 && days <= 15) {
            totalPrice *= 1.10; // Incremento de 10%
        } else if (days > 5 && days < 10) {
            totalPrice *= 0.92; // Descuento de 8%
        }

        return totalPrice;
    }

    public List<Room> confirmRooms(Acomodation accommodation, LocalDate checkIn, LocalDate checkOut, int numberOfRooms, int adults, int children) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : accommodation.getRooms()) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public String makeReservation(String clientFirstName, String clientLastName, String email, String nationality, String phone, String arrivalTime, Acomodation accommodation, Room room, LocalDate checkIn, LocalDate checkOut, double price, int numAdults, int numChildren) {
        Booking booking = new Booking(clientFirstName, clientLastName, email, nationality, phone, arrivalTime, accommodation, room, checkIn, checkOut, price, numAdults, numChildren);
        bookings.add(booking);
        room.setAvailable(false); // Reduce la disponibilidad de la habitación
        return "Se ha realizado la reserva con éxito.";
    }

    public String updateReservation(String email, String name, Booking existingBooking, Room newRoom) {
        if (existingBooking.getEmail().equals(email) && existingBooking.getNameClient().equals(name)) {
            existingBooking.showDetails();
            newRoom.setAvailable(false); // Reduce la disponibilidad de la nueva habitación
            existingBooking.getRoom().setAvailable(true); // Restaura la disponibilidad de la habitación anterior
            existingBooking.setRoom(newRoom);
            return "Reserva actualizada con éxito.";
        }
        return "No se ha podido actualizar la reserva.";
    }

    public String cancelReservation(String email, String name) {
        for (Booking booking : bookings) {
            if (booking.getEmail().equals(email) && booking.getNameClient().equals(name)) {
                bookings.remove(booking);
                booking.getRoom().setAvailable(true); // Restaurar la disponibilidad de la habitación
                return "Reserva cancelada con éxito.";
            }
        }
        return "Reserva no encontrada.";
    }

    public List<Acomodation> getAccommodations() {
        return accommodations;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}