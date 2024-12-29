package Com.BookingHotels;

import java.util.List;

public abstract class Acomodation {
    private String name;
    private String city;
    private int rating; // Calificación de 1 a 5
    private List<Room> rooms; //lista de habitaciones
    private String accommodationType;
    private double calculatedPrice;


    public Acomodation(String name, String city, int rating, List<Room> rooms, String accommodationType, double calculatedPrice) {
        this.name = name;
        this.city = city;
        this.rating = rating;
        this.rooms = rooms;
        this.accommodationType = accommodationType;
        this.calculatedPrice = calculatedPrice;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public double getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public abstract void  showDetails(); //Método abstracto para mostrar detalles del alojamiento
}