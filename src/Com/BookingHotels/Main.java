package Com.BookingHotels;

import com.bookinghotels.State;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BookingService bookingService;

    public static void main(String[] args) {
        List<Acomodation> accommodations = createAccommodations();
        bookingService = new BookingService(accommodations, new ArrayList<>());

        Acomodation accommodation = findAccommodationByName("Day of Sun");
        if (accommodation != null) {
            showAccommodationDetails(accommodation);
        } else {
            System.out.println("Alojamiento no encontrado.");
        }

        while (true) {
            showMenu();
            int option = getUserOption();
            handleMenuOption(option);
        }
    }

    private static List<Acomodation> createAccommodations() {
        List<Acomodation> accommodations = new ArrayList<>();
        accommodations.add(createHotel());
        accommodations.add(createDayOfSun());
        accommodations.add(createApartment());
        accommodations.add(createState());
        return accommodations;
    }

    private static Hotel createHotel() {
        List<Room> rooms = List.of(
            new Room("Simple", 60000, "1 cama, con aire acondicionado", true),
            new Room("Doble", 120000, "2 camas, aire acondicionado y baño privado", true),
            new Room("Suite", 180000, "1 cama king, jacuzzi, aire acondicionado", true),
            new Room("Triple", 140000, "3 camas, aire acondicionado y baño privado", true),
            new Room("Familiar", 200000, "4 camas, aire acondicionado y baño privado", true)
        );
        return new Hotel("Hotel Cartagena", "Cartagena", 5, rooms, "Hotel", 0);
    }

    private static DayOfSun createDayOfSun() {
        List<Room> rooms = List.of(new Room("Cabaña", 50000, "Vista a la represa, piscina privada", true));
        List<String> activities = List.of("Natación", "Tenis", "Spa");
        return new DayOfSun("Club Sol y Arena", "Peñol", 4, rooms, activities, true, "DayOfSun", 0);
    }

    private static Apartmenth createApartment() {
        List<Room> rooms = List.of(new Room("Estudio", 80000, "1 cama, cocina, aire acondicionado", true));
        return new Apartmenth("Apartamento Medellín", "Medellín", 4, rooms, "Apartamento", 0);
    }

    private static Acomodation createState() {
        List<Room> rooms = List.of(
                new Room("Habitación Principal", 150000, "1 cama king, aire acondicionado, baño privado", true),
                new Room("Habitación Secundaria", 100000, "2 camas, aire acondicionado, baño compartido", true)
        );

        return new State("Finca La Esperanza", "Armenia", 5, rooms, "Finca", 0);
    }

    private static void showMenu() {
        System.out.println("--- Menú de reserva ---");
        System.out.println("1. Mostrar alojamiento");
        System.out.println("2. Buscar alojamiento");
        System.out.println("3. Confirmar habitaciones disponibles");
        System.out.println("4. Hacer una reserva");
        System.out.println("5. Actualizar reserva");
        System.out.println("6. Cancelar reserva");
        System.out.println("7. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static int getUserOption() {
        int option = 0;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Opción no válida. Por favor, ingrese un número.");
            scanner.nextLine(); // limpiar el buffer de entrada
        }
        return option;
    }

    private static void handleMenuOption(int option) {
        switch (option) {
            case 1 -> showAccommodations();
            case 2 -> searchAccommodation();
            case 3 -> confirmAvailableRooms();
            case 4 -> makeReservation();
            case 5 -> updateReservation();
            case 6 -> cancelReservation();
            case 7 -> exitProgram();
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void showAccommodations() {
        for (Acomodation accommodation : bookingService.getAccommodations()) {
            accommodation.showDetails();
            System.out.println("****************************");
        }
    }

    private static void searchAccommodation() {
        String city = getInput("Ciudad: ");
        String type = getInput("Tipo de alojamiento (Hotel, DiaDeSol): ");
        LocalDate start = LocalDate.parse(getInput("Fecha de inicio (YYYY-MM-DD): ").trim());
        LocalDate end = LocalDate.parse(getInput("Fecha de fin (YYYY-MM-DD): ").trim());
        int rooms = Integer.parseInt(getInput("Número de habitaciones: ").trim());

        List<Acomodation> results = bookingService.searchForAccomodation(city, type, start, end, rooms);
        System.out.println("Resultados de búsqueda:");
        for (Acomodation accommodation : results) {
            accommodation.showDetails();
            System.out.println("Precio total: $" + accommodation.getCalculatedPrice());
            System.out.println("****************************");
        }
    }

    private static void confirmAvailableRooms() {
        String accommodationName = getInput("Nombre del alojamiento: ");
        Acomodation selectedAccommodation = findAccommodationByName(accommodationName);

        if (selectedAccommodation == null) {
            System.out.println("Alojamiento no encontrado.");
            return;
        }

        LocalDate start = LocalDate.parse(getInput("Fecha de inicio (YYYY-MM-DD): ").trim());
        LocalDate end = LocalDate.parse(getInput("Fecha de fin (YYYY-MM-DD): ").trim());
        int numberOfRooms = Integer.parseInt(getInput("Número de habitaciones: ").trim());

        List<Room> availableRooms = bookingService.confirmRooms(selectedAccommodation, start, end, numberOfRooms, 1, 0);
        System.out.println("Habitaciones disponibles:");
        for (Room room : availableRooms) {
            System.out.println(room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics());
        }
    }

    private static void makeReservation() {
        String clientFirstName = getInput("Nombre del cliente: ");
        String clientLastName = getInput("Apellido: ");
        String clientEmail = getInput("Email: ");
        String clientNationality = getInput("Nacionalidad: ");
        String clientPhone = getInput("Teléfono: ");
        String clientArrivalTime = getInput("Hora de llegada: ");

        String accommodationName = getInput("Nombre del alojamiento: ");
        Acomodation selectedAccommodation = findAccommodationByName(accommodationName);

        if (selectedAccommodation == null) {
            System.out.println("Alojamiento no encontrado.");
            return;
        }

        int numAdults = Integer.parseInt(getInput("Número de adultos: ").trim());
        int numChildren = Integer.parseInt(getInput("Número de niños: ").trim());

        LocalDate start = LocalDate.parse(getInput("Fecha de inicio (YYYY-MM-DD): ").trim());
        LocalDate end = LocalDate.parse(getInput("Fecha de fin (YYYY-MM-DD): ").trim());

        List<Room> availableRoomsForReservation = bookingService.confirmRooms(selectedAccommodation, start, end, 1, numAdults, numChildren);
        if (availableRoomsForReservation.isEmpty()) {
            System.out.println("No hay habitaciones disponibles.");
            return;
        }

        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < availableRoomsForReservation.size(); i++) {
            Room room = availableRoomsForReservation.get(i);
            System.out.println((i + 1) + ". " + room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics());
        }

        int roomChoice = Integer.parseInt(getInput("Seleccione el número de la habitación deseada: ").trim());

        if (isInvalidRoomChoice(roomChoice, availableRoomsForReservation)) {
            System.out.println("Selección inválida.");
            return;
        }

        Room selectedRoom = availableRoomsForReservation.get(roomChoice - 1);
        double price = bookingService.calculatePriceWithAdjustments(start, end, selectedRoom.getPrice(), 1); // Calcula el precio con ajustes
        System.out.println("Precio con ajuste: $" + price);
        String reservationResult = bookingService.makeReservation(clientFirstName, clientLastName, clientEmail, clientNationality, clientPhone, clientArrivalTime, selectedAccommodation, selectedRoom, start, end, price, numAdults, numChildren);
        System.out.println(reservationResult);
    }

    private static boolean isInvalidRoomChoice(int roomChoice, List<Room> availableRoomsForReservation) {
        return roomChoice < 1 || roomChoice > availableRoomsForReservation.size();
    }

    private static void updateReservation() {
        String email = getInput("Email del cliente: ");
        String name = getInput("Nombre del cliente: ");

        String accommodationName = getInput("Nombre del alojamiento: ");
        Acomodation selectedAccommodation = findAccommodationByName(accommodationName);

        if (selectedAccommodation == null) {
            System.out.println("Alojamiento no encontrado.");
            return;
        }

        Booking existingBooking = findBookingByEmailAndName(email, name, selectedAccommodation);

        if (existingBooking == null) {
            System.out.println("Reserva no encontrada.");
            return;
        }

        String change = getInput("¿Desea cambiar de habitación o de alojamiento? (habitacion/alojamiento): ");

        if (change.equalsIgnoreCase("habitacion")) {
            String newRoomName = getInput("Nombre de la nueva habitación: ");
            Room newRoom = findRoomByName(selectedAccommodation, newRoomName);

            if (newRoom == null) {
                System.out.println("Habitación no disponible.");
                return;
            }

            String updateResult = bookingService.updateReservation(email, name, existingBooking, newRoom);
            System.out.println(updateResult);
        } else if (change.equalsIgnoreCase("alojamiento")) {
            bookingService.getBookings().remove(existingBooking);
            System.out.println("Reserva eliminada. Por favor, cree una nueva reserva.");
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void cancelReservation() {
        String email = getInput("Email del cliente: ");
        String name = getInput("Nombre del cliente: ");

        String cancelResult = bookingService.cancelReservation(email, name);
        System.out.println(cancelResult);
    }

    private static void exitProgram() {
        System.out.println("Gracias por usar nuestro servicio, regresa pronto.");
        scanner.close();
        System.exit(0);
    }

    private static Acomodation findAccommodationByName(String name) {
        for (Acomodation accommodation : bookingService.getAccommodations()) {
            if (accommodation.getName().equalsIgnoreCase(name)) {
                return accommodation;
            }
        }
        return null;
    }

    private static Booking findBookingByEmailAndName(String email, String name, Acomodation accommodation) {
        for (Booking booking : bookingService.getBookings()) {
            if (booking.getEmail().equals(email) && booking.getNameClient().equals(name) && booking.getAlojamiento().equals(accommodation)) {
                return booking;
            }
        }
        return null;
    }

    private static Room findRoomByName(Acomodation accommodation, String roomName) {
        for (Room room : accommodation.getRooms()) {
            if (room.getRoomType().equalsIgnoreCase(roomName) && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }

    private static void showAccommodationDetails(Acomodation accommodation) {
        if (accommodation instanceof DayOfSun) {
            DayOfSun dayOfSun = (DayOfSun) accommodation;
            System.out.println("Includes Lunch: " + (dayOfSun.isIncludesLunch() ? "Yes" : "No"));
        }
        accommodation.showDetails();
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}