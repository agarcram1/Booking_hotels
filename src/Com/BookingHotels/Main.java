package Com.BookingHotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Alojamiento> alojamientos = new ArrayList<>();

        // Crear alojamientos
        List<Room> roomsHotel1 = new ArrayList<>();
        roomsHotel1.add(new Room("Sencilla", 60000, "1 cama, aire acondicionado", true));
        roomsHotel1.add(new Room("Doble", 120000, "2 camas, aire acondicionado y baño privado", true));
        roomsHotel1.add(new Room("Suite", 180000, "1 cama king, jacuzzi, aire acondicionado", true));
        roomsHotel1.add(new Room("Triple", 140000, "3 camas, aire acondicionado y baño privado", true));
        roomsHotel1.add(new Room("Familiar", 200000, "4 camas, aire acondicionado y baño privado", true));
        Hotel hotel1 = new Hotel("Hotel Paraíso", "Cartagena", 5, roomsHotel1, "Hotel",0 );
        alojamientos.add(hotel1);

        List<Room> roomsHotel2 = new ArrayList<>();
        roomsHotel2.add(new Room("Suite", 200000, "1 cama king, jacuzzi, aire acondicionado", true));
        roomsHotel2.add(new Room("Doble", 150000, "2 camas, aire acondicionado y baño privado", true));
        roomsHotel2.add(new Room("Sencilla", 80000, "1 cama, aire acondicionado", true));
        roomsHotel2.add(new Room("Familiar", 250000, "4 camas, aire acondicionado y baño privado", true));
        roomsHotel2.add(new Room("Presidencial", 300000, "1 cama king, jacuzzi, aire acondicionado, sala de estar", true));
        Hotel hotel2 = new Hotel("Hotel Maravilla", "Bogotá", 4, roomsHotel2, "Hotel",0);
        alojamientos.add(hotel2);

        List<Room> roomsDayOfSun = new ArrayList<>();
        roomsDayOfSun.add(new Room("Cabaña", 50000, "Vista al mar, piscina privada", true));
        List<String> activities = List.of("Natación", "Tenis", "Spa");
        DayOfSun diaDeSol = new DayOfSun("Club Sol y Arena", "Santa Marta", 4, roomsDayOfSun, activities, true, "DiaDeSol",0);
        alojamientos.add(diaDeSol);

        BookingService bookingService = new BookingService(alojamientos, new ArrayList<>());

        while (true) {
            System.out.println("\n--- Menú de Booking ---");
            System.out.println("1. Mostrar alojamientos");
            System.out.println("2. Buscar alojamiento");
            System.out.println("3. Confirmar habitaciones disponibles");
            System.out.println("4. Realizar reserva");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    for (Alojamiento alojamiento : alojamientos) {
                        alojamiento.showDetails();
                        System.out.println("-----------------------------");
                    }
                    break;

                case 2:
                    System.out.print("Ciudad: ");
                    String ciudad = scanner.nextLine();
                    System.out.print("Tipo de alojamiento (Hotel, DiaDeSol): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    LocalDate inicio = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    LocalDate fin = LocalDate.parse(scanner.nextLine());
                    System.out.print("Cantidad de adultos: ");
                    int adultos = scanner.nextInt();
                    System.out.print("Cantidad de niños: ");
                    int niños = scanner.nextInt();
                    System.out.print("Cantidad de habitaciones: ");
                    int habitaciones = scanner.nextInt();
                    scanner.nextLine();

                    List<Alojamiento> resultados = bookingService.searchForAccomodation(ciudad, tipo, inicio, fin, adultos, niños, habitaciones);
                    System.out.println("Resultados de búsqueda:");
                    for (Alojamiento alojamiento : resultados) {
                        alojamiento.showDetails();
                        System.out.println("-----------------------------");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del alojamiento: ");
                    String nombreAlojamiento = scanner.nextLine();
                    Alojamiento alojamientoSeleccionado = null;
                    for (Alojamiento alojamiento : alojamientos) {
                        if (alojamiento.getName().equalsIgnoreCase(nombreAlojamiento)) {
                            alojamientoSeleccionado = alojamiento;
                            break;
                        }
                    }

                    if (alojamientoSeleccionado == null) {
                        System.out.println("Alojamiento no encontrado.");
                        break;
                    }

                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    inicio = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    fin = LocalDate.parse(scanner.nextLine());
                    System.out.print("Cantidad de habitaciones: ");
                    int cantidadHabitaciones = scanner.nextInt();
                    scanner.nextLine();

                    List<Room> disponibles = bookingService.confirmRooms(alojamientoSeleccionado, inicio, fin, cantidadHabitaciones);
                    System.out.println("Habitaciones disponibles:");
                    for (Room habitacion : disponibles) {
                        System.out.println(habitacion.getRoomType() + " - $" + habitacion.getPrice() + " - " + habitacion.getCaracteristics());
                    }
                    break;

                case 4:
                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellidoCliente = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailCliente = scanner.nextLine();
                    System.out.print("Nacionalidad: ");
                    String nacionalidadCliente = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefonoCliente = scanner.nextLine();
                    System.out.print("Hora de llegada: ");
                    String horaLlegadaCliente = scanner.nextLine();

                    System.out.print("Nombre del alojamiento: ");
                    nombreAlojamiento = scanner.nextLine();
                    alojamientoSeleccionado = null;
                    for (Alojamiento alojamiento : alojamientos) {
                        if (alojamiento.getName().equalsIgnoreCase(nombreAlojamiento)) {
                            alojamientoSeleccionado = alojamiento;
                            break;
                        }
                    }

                    if (alojamientoSeleccionado == null) {
                        System.out.println("Alojamiento no encontrado.");
                        break;
                    }

                    System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                    inicio = LocalDate.parse(scanner.nextLine());
                    System.out.print("Fecha de fin (YYYY-MM-DD): ");
                    fin = LocalDate.parse(scanner.nextLine());

                    Room habitacionSeleccionada = null;
                    for (Room habitacion : alojamientoSeleccionado.getRooms()) {
                        if (habitacion.isAvailable()) {
                            habitacionSeleccionada = habitacion;
                            break;
                        }
                    }

                    if (habitacionSeleccionada == null) {
                        System.out.println("No hay habitaciones disponibles.");
                        break;
                    }

                    double precio = bookingService.calculatePriceWithAdjustments(inicio, fin, habitacionSeleccionada.getPrice(), 1);
                    String resultadoReserva = bookingService.makerReservation(nombreCliente, apellidoCliente, emailCliente, nacionalidadCliente, telefonoCliente, horaLlegadaCliente, alojamientoSeleccionado, habitacionSeleccionada, inicio, fin, precio);
                    System.out.println(resultadoReserva);
                    break;

                case 5:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}