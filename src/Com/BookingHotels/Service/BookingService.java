package Com.BookingHotels.Service;

import Com.BookingHotels.Controller.Messages;
import Com.BookingHotels.Model.Acomodation;
import Com.BookingHotels.Model.Room;
import Com.BookingHotels.Repository.BookingRepository;

import java.time.LocalDate;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void showAccommodations() {
        bookingRepository.getAccommodations().forEach(accommodation -> {
            accommodation.showDetails();
            System.out.println("****************************");
        });
    }

    public void searchAccommodations(String city, String type, LocalDate start, LocalDate end, int rooms) {
        List<Acomodation> results = bookingRepository.searchForAccomodation(city, type, start, end, rooms);
        results.forEach(accommodation -> {
            accommodation.showDetails();
            System.out.println("Precio total: $" + accommodation.getCalculatedPrice());
            System.out.println("****************************");
        });
    }

    public void confirmAvailableRooms(String accommodationName, LocalDate start, LocalDate end, int numberOfRooms) {
        Acomodation accommodation = findAccommodationByName(accommodationName);
        if (accommodation == null) {
            Messages.accommodationNotFound();
            return;
        }

        List<Room> availableRooms = bookingRepository.confirmRooms(accommodation, start, end, numberOfRooms, 1, 0);
        availableRooms.forEach(room ->
                System.out.println(room.getRoomType() + " - $" + room.getPrice() + " - " + room.getCaracteristics())
        );
    }

    public void makeReservation(String[] clientDetails, String accommodationName, int[] guestCounts, LocalDate[] dates) {
        Acomodation accommodation = findAccommodationByName(accommodationName);
        if (accommodation == null) {
            Messages.accommodationNotFound();
            return;
        }

        List<Room> availableRooms = bookingRepository.confirmRooms(accommodation, dates[0], dates[1], 1, guestCounts[0], guestCounts[1]);
        if (availableRooms.isEmpty()) {
            Messages.noRoomsAvailable();
            return;
        }

        Room selectedRoom = availableRooms.get(0); // Puede incluir lógica para que el usuario elija la habitación.
        double price = bookingRepository.calculatePriceWithAdjustments(dates[0], dates[1], selectedRoom.getPrice(), 1);

        String result = bookingRepository.makeReservation(
                clientDetails[0], clientDetails[1], clientDetails[2], clientDetails[3], clientDetails[4], clientDetails[5],
                accommodation, selectedRoom, dates[0], dates[1], price, guestCounts[0], guestCounts[1]
        );
        System.out.println(result);
    }

    public void updateReservation(String email, String name, String accommodationName, String changeType, String newRoomName) {
        // Lógica similar a la anterior, pero delegada aquí.
    }

    public void cancelReservation(String email, String name) {
        String result = bookingRepository.cancelReservation(email, name);
        System.out.println(result);
    }

    private Acomodation findAccommodationByName(String name) {
        return bookingRepository.getAccommodations().stream()
                .filter(accommodation -> accommodation.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
