package Com.BookingHotels;

// Clase para representar una habitación
public class Room {
    private String roomType; // Tipo de habitación
    private double price; // Precio por noche
    private String caracteristics; // Características de la habitación
    private int numberOfRooms; // Número de habitaciones
    private boolean isAvailable; // Indica si la habitación está disponible

    public Room(String roomType, double price, String caracteristics, int numberOfRooms, boolean isAvailable) {
        this.roomType = roomType;
        this.price = price;
        this.caracteristics = caracteristics;
        this.numberOfRooms = numberOfRooms;
        this.isAvailable = isAvailable;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCaracteristics() {
        return caracteristics;
    }

    public void setCaracteristics(String caracteristics) {
        this.caracteristics = caracteristics;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
