package Com.BookingHotels.Model;

import java.util.List;

// Clase para representar una finca
public class State extends Acomodation {
    private String accommodationType; // Finca

    public State(String name, String city, int rating, List<Room> rooms, String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
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
        System.out.println("Finca: " + getName());
        System.out.println("Ciudad: " + getCity());
        System.out.println("Calificación: " + getRating());
        System.out.println("Tipo de finca: " + getAccommodationType());
        System.out.println("Habitaciones: ");
        for (Room room : getRooms()) {
            System.out.println("Habitación: " + room.getRoomType() + ", Precio por noche: $" + room.getPrice());
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getCity() {
        return super.getCity();
    }

    @Override
    public void setCity(String city) {
        super.setCity(city);
    }

    @Override
    public int getRating() {
        return super.getRating();
    }

    @Override
    public void setRating(int rating) {
        super.setRating(rating);
    }

    @Override
    public List<Room> getRooms() {
        return super.getRooms();
    }

    @Override
    public void setRooms(List<Room> rooms) {
        super.setRooms(rooms);
    }

    @Override
    public double getCalculatedPrice() {
        return super.getCalculatedPrice();
    }

    @Override
    public void setCalculatedPrice(double calculatedPrice) {
        super.setCalculatedPrice(calculatedPrice);
    }
}