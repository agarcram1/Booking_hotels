package Com.BookingHotels.Controller;

import Com.BookingHotels.Model.Acomodation;
import Com.BookingHotels.Model.Booking;
import Com.BookingHotels.Model.Room;
import Com.BookingHotels.Repository.BookingRepository;

import java.time.LocalDate;
import java.util.List;

public class MenuHandler {
    private BookingRepository bookingRepository;

    public MenuHandler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void handleMenuOption(int option) {
        switch (option) {
            case 1 -> showAccommodations();
            case 2 -> searchAccommodation();
            case 3 -> confirmAvailableRooms();
            case 4 -> makeReservation();
            case 5 -> updateReservation();
            case 6 -> cancelReservation();
            case 7 -> exitProgram();
            default -> Messages.invalidOption();
        }
    }

    private void showAccommodations() {
        for (Acomodation accommodation : bookingRepository.getAccommodations()) {
            accommodation.showDetails();
            System.out.println("****************************");
        }
    }

    private void searchAccommodation() {
        String city = InputHandler.getStringInput("Ciudad: ");
        String type = InputHandler.getStringInput("Tipo de alojamiento (Hotel, DiaDeSol): ");
        LocalDate start = InputHandler.getDateInput("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate end = InputHandler.getDateInput("Fecha de fin (YYYY-MM-DD): ");
        int rooms = InputHandler.getIntInput("Número de habitaciones: ");

        List<Acomodation> results = bookingRepository.searchForAccomodation(city, type, start, end, rooms);
        System.out.println("Resultados de búsqueda:");
        for (Acomodation accommodation : results) {
            accommodation.showDetails();
            System.out.println("Precio total: $" + accommodation.getCalculatedPrice());
            System.out.println("****************************");
        }
    }

    private void confirmAvailableRooms() {
        String accommodationName = InputHandler.getStringInput("Nombre del alojamiento: ");
        Acomodation selectedAccommodation = findAccommodationByName(accommodationName);

        if (selectedAccommodation == null) {
            Messages.accommodationNotFound();
            return;
        }

        LocalDate start = InputHandler.getDateInput("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate end = InputHandler.getDateInput("Fecha de fin (YYYY-MM-DD): ");
        int numberOfRooms = InputHandler.getIntInput("Número de habitaciones: ");

        List<Room> availableRooms = bookingRepository.confirmRooms(selectedAccommodation, start, end, numberOfRooms, 1, 0);
        System.out.println("Habitaciones disponibles:");
        for (Room room : availableRooms) {
            System.out.println(room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics());
        }
    }

private void makeReservation() {
    String[] clientDetails = getClientDetails();
    Acomodation selectedAccommodation = getAccommodation();

    if (selectedAccommodation == null) {
        Messages.accommodationNotFound();
        return;
    }

    int[] guestCounts = getGuestCounts();
    LocalDate[] dates = getReservationDates();

    List<Room> availableRooms = getAvailableRooms(selectedAccommodation, dates, guestCounts);
    if (availableRooms.isEmpty()) {
        Messages.noRoomsAvailable();
        return;
    }

    Room selectedRoom = selectRoom(availableRooms);
    if (selectedRoom == null) {
        Messages.invalidSelection();
        return;
    }

    double price = bookingRepository.calculatePriceWithAdjustments(dates[0], dates[1], selectedRoom.getPrice(), 1);
    System.out.println("Precio con ajuste: $" + price);

    String reservationResult = bookingRepository.makeReservation(
        clientDetails[0], clientDetails[1], clientDetails[2], clientDetails[3], clientDetails[4], clientDetails[5],
        selectedAccommodation, selectedRoom, dates[0], dates[1], price, guestCounts[0], guestCounts[1]
    );
    System.out.println(reservationResult);
}

private String[] getClientDetails() {
    return new String[]{
        InputHandler.getStringInput("Nombre del cliente: "),
        InputHandler.getStringInput("Apellido: "),
        InputHandler.getStringInput("Email: "),
        InputHandler.getStringInput("Nacionalidad: "),
        InputHandler.getStringInput("Teléfono: "),
        InputHandler.getStringInput("Hora de llegada: ")
    };
}

private Acomodation getAccommodation() {
    String accommodationName = InputHandler.getStringInput("Nombre del alojamiento: ");
    return findAccommodationByName(accommodationName);
}

private int[] getGuestCounts() {
    return new int[]{
        InputHandler.getIntInput("Número de adultos: "),
        InputHandler.getIntInput("Número de niños: ")
    };
}

private LocalDate[] getReservationDates() {
    return new LocalDate[]{
        InputHandler.getDateInput("Fecha de inicio (YYYY-MM-DD): "),
        InputHandler.getDateInput("Fecha de fin (YYYY-MM-DD): ")
    };
}

private List<Room> getAvailableRooms(Acomodation accommodation, LocalDate[] dates, int[] guestCounts) {
    return bookingRepository.confirmRooms(accommodation, dates[0], dates[1], 1, guestCounts[0], guestCounts[1]);
}

private Room selectRoom(List<Room> availableRooms) {
    System.out.println("Habitaciones disponibles:");
    for (int i = 0; i < availableRooms.size(); i++) {
        Room room = availableRooms.get(i);
        System.out.println((i + 1) + ". " + room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics());
    }

    int roomChoice = InputHandler.getIntInput("Seleccione el número de la habitación deseada: ");
    if (isInvalidRoomChoice(roomChoice, availableRooms)) {
        return null;
    }

    return availableRooms.get(roomChoice - 1);
}

    private boolean isInvalidRoomChoice(int roomChoice, List<Room> availableRoomsForReservation) {
        return roomChoice < 1 || roomChoice > availableRoomsForReservation.size();
    }

    private void updateReservation() {
        String email = InputHandler.getStringInput("Email del cliente: ");
        String name = InputHandler.getStringInput("Nombre del cliente: ");

        String accommodationName = InputHandler.getStringInput("Nombre del alojamiento: ");
        Acomodation selectedAccommodation = findAccommodationByName(accommodationName);

        if (selectedAccommodation == null) {
            Messages.accommodationNotFound();
            return;
        }

        Booking existingBooking = findBookingByEmailAndName(email, name, selectedAccommodation);

        if (existingBooking == null) {
            Messages.reservationCancelFailure();
            return;
        }

        String change = InputHandler.getStringInput("¿Desea cambiar de habitación o de alojamiento? (habitacion/alojamiento): ");

        if (change.equalsIgnoreCase("habitacion")) {
            String newRoomName = InputHandler.getStringInput("Nombre de la nueva habitación: ");
            Room newRoom = findRoomByName(selectedAccommodation, newRoomName);

            if (newRoom == null) {
                Messages.noRoomsAvailable();
                return;
            }

            String updateResult = bookingRepository.updateReservation(email, name, existingBooking, newRoom);
            System.out.println(updateResult);
        } else if (change.equalsIgnoreCase("alojamiento")) {
            bookingRepository.getBookings().remove(existingBooking);
            System.out.println("Reserva eliminada. Por favor, cree una nueva reserva.");
        } else {
            Messages.invalidOption();
        }
    }

    private void cancelReservation() {
        String email = InputHandler.getStringInput("Email del cliente: ");
        String name = InputHandler.getStringInput("Nombre del cliente: ");

        String cancelResult = bookingRepository.cancelReservation(email, name);
        System.out.println(cancelResult);
    }

    private void exitProgram() {
        Messages.exitMessage();
        System.exit(0);
    }

    private Acomodation findAccommodationByName(String name) {
        for (Acomodation accommodation : bookingRepository.getAccommodations()) {
            if (accommodation.getName().equalsIgnoreCase(name)) {
                return accommodation;
            }
        }
        return null;
    }

    private Booking findBookingByEmailAndName(String email, String name, Acomodation accommodation) {
        for (Booking booking : bookingRepository.getBookings()) {
            if (booking.getEmail().equals(email) && booking.getNameClient().equals(name) && booking.getAlojamiento().equals(accommodation)) {
                return booking;
            }
        }
        return null;
    }

    private Room findRoomByName(Acomodation accommodation, String roomName) {
        for (Room room : accommodation.getRooms()) {
            if (room.getRoomType().equalsIgnoreCase(roomName) && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }
}
