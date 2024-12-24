package Com.BookingHotels;

import java.util.List;

public class DayOfSun extends Alojamiento implements  ICalculate {
    private List<String> activities; // Lista de actividades
    private boolean includesLunch; // Indica si incluye almuerzo


    public DayOfSun(String name, String city, int rating, List<Room> rooms, List<String> activities, boolean includesLunch,String accommodationType, double calculatedPrice) {
        super(name, city, rating, rooms, accommodationType, calculatedPrice);
        this.activities = activities;
        this.includesLunch = includesLunch;
    }

    @Override
    public void showDetails() {
        System.out.println("Name the accomodation " + getName());
        System.out.println("City: " + getCity());
        System.out.println("Rating: " + +getRating() + " Stars");
        System.out.println("Activities: " + String.join(", ", activities));
        System.out.println("Includes lunch: " + (includesLunch ? "Yes" : "No"));

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
