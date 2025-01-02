package com.BookingHotels.Controller;

public class Messages {
    public static void showMenu() {
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

    public static void invalidOption() {
        System.out.println("Opción no válida. Por favor, ingrese un número.");
    }

    public static void accommodationNotFound() {
        System.out.println("Alojamiento no encontrado.");
    }

    public static void noRoomsAvailable() {
        System.out.println("No hay habitaciones disponibles.");
    }

    public static void invalidSelection() {
        System.out.println("Selección inválida.");
    }

    public static void reservationSuccess() {
        System.out.println("Se ha realizado la reserva con éxito.");
    }

    public static void reservationUpdateSuccess() {
        System.out.println("Reserva actualizada con éxito.");
    }

    public static void reservationUpdateFailure() {
        System.out.println("No se ha podido actualizar la reserva.");
    }

    public static void reservationCancelSuccess() {
        System.out.println("Reserva cancelada con éxito.");
    }

    public static void reservationCancelFailure() {
        System.out.println("Reserva no encontrada.");
    }

    public static void exitMessage() {
        System.out.println("Gracias por usar nuestro servicio, regresa pronto.");
    }
}