package Com.BookingHotels;

import java.util.List;

public class Apartamento extends Alojamiento {
    public Apartamento(String name, String city, int rating, List<Room> rooms, String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
    }

    @Override
    public void showDetails() {
        System.out.println("Apartment: " + getName());
        System.out.println("City: " + getCity());
        System.out.println("Rating: " + getRating());
        System.out.println("Type of apartment: " + getAccommodationType());
        System.out.println("Rooms: ");
        for (Room room : getRooms()) {
            System.out.println("Room: " + room.getRoomType() + ", Price per night: $" + room.getPrice());
        }
        System.out.println("Calculated Price: $" + getCalculatedPrice());
    }
}
