package Com.BookingHotels;

import java.util.List;

// Clase para representar un hotel
public class Hotel extends Alojamiento implements Cloneable {
    private String accommodationType; // Hotel, apartamento, finca

    public Hotel(String name, String city, int rating, List<Room> rooms, String accommodationType) {
        super(name, city, rating, rooms);
        this.accommodationType = accommodationType;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    @Override
    public void showDetails() {
        System.out.println("Hotel: " + getName());
        System.out.println("City: " + getCity());
        System.out.println("Rating: " + getRating());
        System.out.println("Type of hotel: " + getAccommodationType());
        System.out.println("Rooms: ");
        for (Room room : getRooms()) {
            System.out.println(room.getRoomType() + " - " + room.getPrice() + " €/night");
        }
    }

    // Método para calcular el precio de la estancia en un hotel
    public double calculatePrice(int days, int numberOfRooms) {
        double totalPrice = 0;
        for (Room room : getRooms()) {
            totalPrice += room.getPrice() * days;
        }
        return totalPrice * numberOfRooms;
    }
}
