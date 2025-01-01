package Com.BookingHotels.Model;


import Com.BookingHotels.ICalculate;

import java.util.List;

// Clase para representar un hotel
public class Hotel extends Acomodation implements ICalculate {
    private String accommodationType; // Hotel, apartamento, finca

    public Hotel(String name, String city, int rating, List<Room> rooms, String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
        this.accommodationType = accommodationType;
    }

    @Override
    public String getAccommodationType() {
        return accommodationType;
    }

    @Override
    public void showDetails() {
        System.out.println("Hotel: " + getName());
        System.out.println("City: " + getCity());
        System.out.println("Rating: " + getRating());
        System.out.println("Type of hotel: " + getAccommodationType());
        System.out.println("Rooms: ");
        for (Room room : getRooms()) {
            System.out.println("Habitación: " + room.getRoomType() + ", Precio por noche: $" + room.getPrice());
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