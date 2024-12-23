package Com.BookingHotels;

import java.util.List;

public abstract class Alojamiento {
    private String name;
    private String city;
    private int rating; // Calificación de 1 a 5
    private List<Room> rooms; //lista de habitaciones


    public Alojamiento(String name, String city, int rating, List<Room> rooms) {
        this.name = name;
        this.city = city;
        this.rating = rating;
        this.rooms = rooms;
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