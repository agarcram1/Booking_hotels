package Com.BookingHotels.Model;

import Com.BookingHotels.ICalculate;

import java.util.List;

public class DayOfSun extends Acomodation implements ICalculate {
    private List<String> activities; // Lista de actividades
    private boolean includesLunch; // Indica si incluye almuerzo o refrigerio

    public DayOfSun(String name, String city, int rating, List<Room> rooms, List<String> activities, boolean includesLunch, String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
        this.activities = activities;
        this.includesLunch = includesLunch;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public boolean isIncludesLunch() {
        return includesLunch;
    }

    public void setIncludesLunch(boolean includesLunch) {
        this.includesLunch = includesLunch;
    }

    @Override
    public void showDetails() {
        System.out.println("Day of Sun: " + getName());
        System.out.println("City: " + getCity());
        System.out.println("Rating: " + getRating());
        System.out.println("Activities: " + String.join(", ", activities));
        System.out.println("Includes Lunch: " + (includesLunch ? "Yes" : "No"));
        System.out.println("Rooms: ");
        for (Room room : getRooms()) {
            System.out.println("Room: " + room.getRoomType() + ", Price per night: $" + room.getPrice());
        }
        System.out.println("Calculated Price: $" + getCalculatedPrice());
    }

    @Override
    public double calculatePrice(int days, int numberOfRooms) {
        double totalPrice = 0;
        for (Room room : getRooms()) {
            totalPrice += room.getPrice() * days;
        }
        return totalPrice * numberOfRooms;
    }
}
