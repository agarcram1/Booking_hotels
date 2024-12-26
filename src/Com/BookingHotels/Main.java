package Com.BookingHotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Alojamiento> accommodations = new ArrayList<>();

        // Create accommodations
        List<Room> roomsHotel1 = new ArrayList<>();
        roomsHotel1.add(new Room("Simple", 60000, "1 cama, con aire acondicionado", true));
        roomsHotel1.add(new Room("Doble", 120000, "2 camas, aire acondicionado y baño privado", true));
        roomsHotel1.add(new Room("Suite", 180000, "1 cama king, jacuzzi, aire acondicionado", true));
        roomsHotel1.add(new Room("Triple", 140000, "3 camas, aire acondicionado y baño privado", true));
        roomsHotel1.add(new Room("Familair", 200000, "4 camas, aire acondicionado y baño privado", true));
        Hotel hotel1 = new Hotel("Hotel cartagena", "Cartagena", 5, roomsHotel1, "Hotel", 0);
        accommodations.add(hotel1);

        List<Room> roomsHotel2 = new ArrayList<>();
        roomsHotel2.add(new Room("Suite", 200000, "1 cama king, jacuzzi, aire acondicionado", true));
        roomsHotel2.add(new Room("Doble", 150000, "2 camas, aire acondicionado y baño privado", true));
        roomsHotel2.add(new Room("Sencilla", 80000, "1 cama, aire acondicionado y balcon", true));
        roomsHotel2.add(new Room("Familiar", 250000, "4 camas, aire acondicionado y baño privado", true));
        roomsHotel2.add(new Room("presidencial", 300000, "1  cama king , jacuzzi, aire acondicionado, sala de estar", true));
        Hotel hotel2 = new Hotel("Hotel bogota", "Bogota", 4, roomsHotel2, "Hotel", 0);
        accommodations.add(hotel2);

        List<Room> roomsDayOfSun = new ArrayList<>();
        roomsDayOfSun.add(new Room("Cabaña", 50000, "Vista a la represa, piscina privada", true));
        List<String> activities = List.of("Natacion", "Tennis", "Spa");
        DayOfSun dayOfSun = new DayOfSun("Club Sol y Arena", "Peñol", 4, roomsDayOfSun, activities, true, "DayOfSun", 0);
        accommodations.add(dayOfSun);

        BookingService bookingService = new BookingService(accommodations, new ArrayList<>());

        while (true) {
            System.out.println("\n--- Menu de reserva ---");
            System.out.println("1. Mostrar alojamiento");
            System.out.println("2. Buscar alojamiento");
            System.out.println("3. Confirmar habitaciones disponibles");
            System.out.println("4. Hacer una reserva");
            System.out.println("5. Actualizar reserva");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    for (Alojamiento accommodation : accommodations) {
                        accommodation.showDetails();
                        System.out.println("****************************");
                    }
                    break;

                case 2:
                    System.out.print("Ciudad: ");
                    String city = scanner.nextLine();
                    System.out.print("Tipo de alojamiento (Hotel, DiaDeSol): ");
                    String type = scanner.nextLine();
                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    LocalDate start = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    LocalDate end = LocalDate.parse(scanner.nextLine());
                    System.out.print("Numero de adultos: ");
                    int adults = scanner.nextInt();
                    System.out.print("Numero de niños: ");
                    int children = scanner.nextInt();
                    System.out.print("Numero de habitaciones: ");
                    int rooms = scanner.nextInt();
                    scanner.nextLine();

                    List<Alojamiento> results = bookingService.searchForAccomodation(city, type, start, end, adults, children, rooms);
                    System.out.println("Resultados de búsqueda:");
                    for (Alojamiento accommodation : results) {
                        accommodation.showDetails();
                        System.out.println("Precio total: $" + accommodation.getCalculatedPrice());
                        System.out.println("****************************");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del alojamiento: ");
                    String accommodationName = scanner.nextLine();
                    Alojamiento selectedAccommodation = null;
                    for (Alojamiento accommodation : accommodations) {
                        if (accommodation.getName().equalsIgnoreCase(accommodationName)) {
                            selectedAccommodation = accommodation;
                            break;
                        }
                    }

                    if (selectedAccommodation == null) {
                        System.out.println("Alojamiento no encontrado.");
                        break;
                    }

                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    start = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    end = LocalDate.parse(scanner.nextLine());
                    System.out.print("Numero de habitaciones: ");
                    int numberOfRooms = scanner.nextInt();
                    scanner.nextLine();

                    List<Room> availableRooms = bookingService.confirmRooms(selectedAccommodation, start, end, numberOfRooms);
                    System.out.println("Habitaciones disponibles:");
                    for (Room room : availableRooms) {
                        System.out.println(room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics());
                    }
                    break;

                case 4:
                    System.out.print("Nombre del cliente: ");
                    String clientFirstName = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String clientLastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String clientEmail = scanner.nextLine();
                    System.out.print("nacionalidad: ");
                    String clientNationality = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String clientPhone = scanner.nextLine();
                    System.out.print("Hora de llegada: ");
                    String clientArrivalTime = scanner.nextLine();

                    System.out.print("Nombre del alojamiento: ");
                    accommodationName = scanner.nextLine();
                    selectedAccommodation = null;
                    for (Alojamiento accommodation : accommodations) {
                        if (accommodation.getName().equalsIgnoreCase(accommodationName)) {
                            selectedAccommodation = accommodation;
                            break;
                        }
                    }

                    if (selectedAccommodation == null) {
                        System.out.println("Alojamiento no encontrado.");
                        break;
                    }

                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    start = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    end = LocalDate.parse(scanner.nextLine());

                    Room selectedRoom = null;
                    for (Room room : selectedAccommodation.getRooms()) {
                        if (room.isAvailable()) {
                            selectedRoom = room;
                            break;
                        }
                    }

                    if (selectedRoom == null) {
                        System.out.println("No hay habitaciones disponibles.");
                        break;
                    }

                    double price = bookingService.calculatePriceWithAdjustments(start, end, selectedRoom.getPrice(), 1); // Calcula el precio con ajustes
                    System.out.println("Precio con ajuste: $" + price);
                    String reservationResult = bookingService.makerReservation(clientFirstName, clientLastName, clientEmail, clientNationality, clientPhone, clientArrivalTime, selectedAccommodation, selectedRoom, start, end, price);
                    System.out.println(reservationResult);
                    break;

                case 5:
                    System.out.print("Email del cliente: ");
                    String email = scanner.nextLine();
                    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                    LocalDate birthDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Nombre del alojamiento: ");
                    accommodationName = scanner.nextLine();
                    selectedAccommodation = null;
                    for (Alojamiento accommodation : accommodations) {
                        if (accommodation.getName().equalsIgnoreCase(accommodationName)) {
                            selectedAccommodation = accommodation;
                            break;
                        }
                    }

                    if (selectedAccommodation == null) {
                        System.out.println("Alojamiento no encontrado.");
                        break;
                    }

                    Booking existingBooking = null;
                    for (Booking booking : bookingService.getBookings()) {
                        if (booking.getEmail().equals(email) && booking.getAlojamiento().equals(selectedAccommodation)) {
                            existingBooking = booking;
                            break;
                        }
                    }

                    if (existingBooking == null) {
                        System.out.println("Reserva no encontrada.");
                        break;
                    }

                    System.out.print("¿Desea cambiar de habitación o de alojamiento? (habitacion/alojamiento): ");
                    String change = scanner.nextLine();

                    if (change.equalsIgnoreCase("habitacion")) {
                        System.out.print("Nombre de la nueva habitación: ");
                        String newRoomName = scanner.nextLine();
                        Room newRoom = null;
                        for (Room room : selectedAccommodation.getRooms()) {
                            if (room.getRoomType().equalsIgnoreCase(newRoomName) && room.isAvailable()) {
                                newRoom = room;
                                break;
                            }
                        }

                        if (newRoom == null) {
                            System.out.println("habitación no disponible.");
                            break;
                        }

                        String updateResult = bookingService.updateReservation(email, birthDate.toString(), existingBooking, newRoom);
                        System.out.println(updateResult);
                    } else if (change.equalsIgnoreCase("Alojamiento")) {
                        bookingService.getBookings().remove(existingBooking);
                        System.out.println("Reserva eliminada. Por favor, cree una nueva reserva.");
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 6:
                    System.out.println("Gracias por usar nuestro servicio, regresa pronto.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}