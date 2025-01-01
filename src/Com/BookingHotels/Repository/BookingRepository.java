package Com.BookingHotels.Repository;

import Com.BookingHotels.Model.*;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static BookingRepository instance;
    private List<Acomodation> accommodations;
    private List<Booking> bookings;

    private BookingRepository() {
        accommodations = new ArrayList<>();
        bookings = new ArrayList<>();
        List<Room> rooms = List.of(
                new Room("Simple", 60000, "1 cama, con aire acondicionado", true),
                new Room("Doble", 120000, "2 camas, aire acondicionado y baño privado", true),
                new Room("Suite", 180000, "1 cama king, jacuzzi, aire acondicionado", true),
                new Room("Triple", 140000, "3 camas, aire acondicionado y baño privado", true),
                new Room("Familiar", 200000, "4 camas, aire acondicionado y baño privado", true)
        );
        accommodations.add(new Hotel("Hotel Cartagena", "Cartagena", 5, rooms, "Hotel", 0));

        List<Room> dayOfSunRooms = List.of(
                new Room("Cabaña", 50000, "Vista a la represa, piscina privada", true));
        List<String> activities = List.of("Natación", "Tenis", "Spa");
        accommodations.add(new DayOfSun("Club Sol y Arena", "Peñol", 4, dayOfSunRooms, activities, true, "DayOfSun", 0));

        List<Room> apartmentRooms = List.of(new Room("Estudio", 80000, "1 cama, cocina, aire acondicionado", true));
        accommodations.add(new Apartmenth("Apartamento Medellín", "Medellín", 4, apartmentRooms, "Apartamento", 0));

        List<Room> stateRooms = List.of(
                new Room("Habitación Principal", 150000, "1 cama king, aire acondicionado, baño privado", true),
                new Room("Habitación Secundaria", 100000, "2 camas, aire acondicionado, baño compartido", true)
        );
        accommodations.add(new State("Finca La Esperanza", "Armenia", 5, stateRooms, "Finca", 0));
    }

    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }

    public List<Acomodation> getAccommodations() {
        return accommodations;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addAcomodation(Acomodation accommodation) {
        accommodations.add(accommodation);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
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
        long days = calculateDaysBetween(checkIn, checkOut);
        double basePrice = calculateBasePrice(days, pricePerNight, numberOfRooms);
        double adjustedPrice = applyAdjustments(basePrice, days);

        return adjustedPrice;
    }

    private long calculateDaysBetween(LocalDate checkIn, LocalDate checkOut) {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    private double calculateBasePrice(long days, double pricePerNight, int numberOfRooms) {
        return days * pricePerNight * numberOfRooms;
    }

    private double applyAdjustments(double basePrice, long days) {
        return switch ((int) days) {
            case 0, 1, 2, 3, 4, 5 -> basePrice * 1.15; // Incremento de 15%
            case 10, 11, 12, 13, 14, 15 -> basePrice * 1.10; // Incremento de 10%
            default -> (days > 5 && days < 10) ? basePrice * 0.92 : basePrice; // Descuento de 8% o sin ajuste
        };
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
            existingBooking.toString();
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

    public Acomodation findAccommodationByName(String name) {
        for (Acomodation accommodation : accommodations) {
            if (accommodation.getName().equalsIgnoreCase(name)) {
                return accommodation;
            }
        }
        return null;
    }

    public void showAccommodationDetails(Acomodation accommodation) {
        if (accommodation instanceof DayOfSun) {
            DayOfSun dayOfSun = (DayOfSun) accommodation;
            System.out.println("Includes Lunch: " + (dayOfSun.isIncludesLunch() ? "Yes" : "No"));
        }
        accommodation.showDetails();
    }

    public void showAccommodations() {
        for (Acomodation accommodation : accommodations) {
            accommodation.showDetails();
            System.out.println("****************************");
        }
    }
}