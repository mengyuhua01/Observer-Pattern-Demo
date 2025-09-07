package org.fruitstore;

import org.fruitstore.publisher.FruitStore;
import org.fruitstore.subscriber.AnnouncementSubscriber;
import org.fruitstore.subscriber.EmailSubscriber;
import org.fruitstore.subscriber.WeChatSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CLIDemo {
    private static FruitStore store;
    private static final Map<String, AnnouncementSubscriber> subscribers = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Fruit Store Management System!");
        System.out.print("Please enter the store name: ");
        String storeName = scanner.nextLine();
        store = new FruitStore(storeName);
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    handleSubscriberMenu();
                    break;
                case 2:
                    handleUnsubscribeMenu();
                    break;
                case 3:
                    handleAnnouncementMenu();
                    break;
                default:
                    System.out.println("Exiting the program. Goodbye!");
                    running = false;
                    break;
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n===== " + store.getClass().getSimpleName() + " Management System =====");
        System.out.println("1. Add subscriber to store announcement");
        System.out.println("2. Remove subscriber from store announcement");
        System.out.println("3. Make announcements");
        System.out.println("[Any Other Number]. Exit");
    }

    private static void handleSubscriberMenu() {
        System.out.println("\n===== Subscriber Management =====");
        System.out.println("1. Add new subscriber");
        System.out.println("2. Add existing subscriber to new announcement type");
        System.out.println("[Any Other Number]. Back to main menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewSubscriber();
                break;
            case 2:
                addExistingSubscriberToNewType();
                break;
            default:
                return;
        }
    }
    
    private static void handleUnsubscribeMenu() {
        System.out.println("\n===== Unsubscribe Management =====");
        System.out.println("1. Remove existing subscriber from an announcement type");
        System.out.println("[Any Other Number]. Back to main menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                removeSubscriberFromAnnouncementType();
                break;
            default:
                return;
        }
    }
    
    private static void handleAnnouncementMenu() {
        System.out.println("\n===== Announcement Management =====");
        System.out.println("1. Make new fruit arrival announcement");
        System.out.println("2. Make new discount announcement");
        System.out.println("[Any Other Number]. Back to main menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                makeNewArrivalAnnouncement();
                break;
            case 2:
                makeDiscountAnnouncement();
                break;
            default:
                return;
        }
    }
    
    private static void addNewSubscriber() {
        System.out.println("\n===== Add New Subscriber =====");
        System.out.println("Select subscriber type:");
        System.out.println("1. Email Subscriber");
        System.out.println("2. WeChat Subscriber");
        
        int type = getIntInput("Enter subscriber type: ");
        
        if (type != 1 && type != 2) {
            System.out.println("Invalid subscriber type.");
            return;
        }
        
        System.out.print("Enter subscriber name (used to identify this subscriber):");
        String subscriberId = scanner.nextLine();
        
        if (subscribers.containsKey(subscriberId)) {
            System.out.println("Subscriber ID already exists. Please use a unique ID.");
            return;
        }
        
        AnnouncementSubscriber subscriber = null;
        
        if (type == 1) {
            System.out.print("Enter email address: ");
            String email = scanner.nextLine();
            subscriber = new EmailSubscriber(email);
        } else {
            System.out.print("Enter WeChat username: ");
            String username = scanner.nextLine();
            subscriber = new WeChatSubscriber(username);
        }
        
        subscribers.put(subscriberId, subscriber);
        
        System.out.println("Select announcement type to subscribe to:");
        System.out.println("1. New Arrival");
        System.out.println("2. Discount");
        
        int announcementType = getIntInput("Enter your choice: ");
        
        try {
            if (announcementType == 1) {
                store.storeAnnouncements.subscribe(FruitStore.NEW_ARRIVAL, subscriber);
                System.out.println("Subscribed to New Arrival announcements.");
            }
            
            if (announcementType == 2) {
                store.storeAnnouncements.subscribe(FruitStore.DISCOUNT, subscriber);
                System.out.println("Subscribed to Discount announcements.");
            }
            System.out.println("Subscriber added successfully!");
        } catch (Exception e) {
            System.out.println("Error subscribing: " + e.getMessage());
        }
    }
    
    private static void addExistingSubscriberToNewType() {
        System.out.println("\n===== Add Existing Subscriber to New Announcement Type =====");
        
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers available. Please add a subscriber first.");
            return;
        }
        
        System.out.println("Available subscribers:");

        for (String id : subscribers.keySet()) {
            System.out.println("- " + id);
        }
        
        System.out.print("Enter subscriber ID: ");
        String subscriberId = scanner.nextLine();
        
        AnnouncementSubscriber subscriber = subscribers.get(subscriberId);
        if (subscriber == null) {
            System.out.println("Subscriber not found.");
            return;
        }
        
        System.out.println("Select announcement type to subscribe to:");
        System.out.println("1. New Arrival");
        System.out.println("2. Discount");
        
        int announcementType = getIntInput("Enter your choice: ");
        
        try {
            if (announcementType == 1) {
                store.storeAnnouncements.subscribe(FruitStore.NEW_ARRIVAL, subscriber);
                System.out.println("Subscribed to New Arrival announcements.");
            } else if (announcementType == 2) {
                store.storeAnnouncements.subscribe(FruitStore.DISCOUNT, subscriber);
                System.out.println("Subscribed to Discount announcements.");
            } else {
                System.out.println("Invalid announcement type.");
            }
        } catch (Exception e) {
            System.out.println("Error subscribing: " + e.getMessage());
        }
    }
    
    private static void removeSubscriberFromAnnouncementType() {
        System.out.println("\n===== Remove Subscriber from Announcement Type =====");
        
        if (subscribers.isEmpty()) {
            System.out.println("No subscribers available.");
            return;
        }
        
        System.out.println("Available subscribers:");
        for (String id : subscribers.keySet()) {
            System.out.println("- " + id);
        }
        
        System.out.print("Enter subscriber ID: ");
        String subscriberId = scanner.nextLine();
        
        AnnouncementSubscriber subscriber = subscribers.get(subscriberId);
        if (subscriber == null) {
            System.out.println("Subscriber not found.");
            return;
        }
        
        System.out.println("Select announcement type to unsubscribe from:");
        System.out.println("1. New Arrival");
        System.out.println("2. Discount");
        
        int announcementType = getIntInput("Enter your choice: ");
        
        try {
            if (announcementType == 1) {
                store.storeAnnouncements.unsubscribe(FruitStore.NEW_ARRIVAL, subscriber);
                System.out.println("Unsubscribed from New Arrival announcements.");
            } else if (announcementType == 2) {
                store.storeAnnouncements.unsubscribe(FruitStore.DISCOUNT, subscriber);
                System.out.println("Unsubscribed from Discount announcements.");
            } else {
                System.out.println("Invalid announcement type.");
            }
        } catch (Exception e) {
            System.out.println("Error unsubscribing: " + e.getMessage());
        }
    }
    
    private static void makeNewArrivalAnnouncement() {
        System.out.println("\n===== Make New Arrival Announcement =====");
        System.out.print("Enter fruit type: ");
        String fruitType = scanner.nextLine();
        
        store.newArrival(fruitType);
        System.out.println("New arrival announcement made successfully!");
    }
    
    private static void makeDiscountAnnouncement() {
        System.out.println("\n===== Make Discount Announcement =====");
        int percentage = getIntInput("Enter discount percentage (0-100): ");
        
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid percentage. Must be between 0 and 100.");
            return;
        }
        
        store.discount(percentage);
        System.out.println("Discount announcement made successfully!");
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
