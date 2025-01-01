package Com.BookingHotels;

import Com.BookingHotels.Controller.InputHandler;
import Com.BookingHotels.Controller.MenuHandler;
import Com.BookingHotels.Controller.Messages;
import Com.BookingHotels.Repository.BookingRepository;

public class Main {
    private static BookingRepository bookingRepository;
    private static MenuHandler menuHandler;

    public static void main(String[] args) {
        bookingRepository = BookingRepository.getInstance();
        menuHandler = new MenuHandler(bookingRepository);

        while (true) {
            Messages.showMenu();
            int option = InputHandler.getUserOption();
            menuHandler.handleMenuOption(option);
        }
    }
}