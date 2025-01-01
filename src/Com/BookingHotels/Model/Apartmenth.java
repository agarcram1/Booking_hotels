package Com.BookingHotels.Model;

import java.util.List;

public class Apartmenth extends Acomodation {
    public Apartmenth(String name, String city, int rating, List<Room> rooms, String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
    }

    @Override
    public void showDetails() {
        System.out.println("Apartamento: " + getName());
        System.out.println("Ciudad: " + getCity());
        System.out.println("Calificación: " + getRating());
        System.out.println("Tipo de apartamento: " + getAccommodationType());
        System.out.println("Habitaciones: ");
        for (Room room : getRooms()) {
            System.out.println("Habitación: " + room.getRoomType() + ", Precio por noche: $" + room.getPrice());
        }
        System.out.println("Precio calculado: $" + getCalculatedPrice());
    }
}