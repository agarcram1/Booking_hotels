package com.BookingHotels;

import com.BookingHotels.Controller.InputHandler;
import Com.BookingHotels.Controller.MenuHandler;
import com.BookingHotels.Controller.Messages;
import Com.BookingHotels.Repository.BookingRepository;
import Com.BookingHotels.Service.BookingService;

public class Main {
    private static BookingRepository bookingRepository;
    private static MenuHandler menuHandler;

    public static void main(String[] args) {
        bookingRepository = BookingRepository.getInstance();
        BookingService bookingService = new BookingService(bookingRepository);
        menuHandler = new MenuHandler(bookingService);

        while (true) {
            Messages.showMenu();
            int option = InputHandler.getUserOption();
            menuHandler.handleMenuOption(option);
        }
    }
}