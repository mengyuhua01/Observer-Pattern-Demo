package org.fruitstore.publisher;

import java.time.LocalDate;
import java.util.List;

public class FruitStore {
    public static final String NEW_ARRIVAL = "New Arrival";
    public static final String DISCOUNT = "Discount";

    public StoreAnnouncementManager storeAnnouncements;
    private final String storeName;

    public FruitStore(String storeName) {
        this.storeName = storeName;
        this.storeAnnouncements = new StoreAnnouncementManager(List.of(NEW_ARRIVAL, DISCOUNT));
    }

    public void newArrival(String fruitType) {
        storeAnnouncements.notify(storeName, NEW_ARRIVAL,
                String.format("%s has arrived the store at %s!", fruitType, LocalDate.now()));
    }

    public void discount(Integer discountPercentage) {
        storeAnnouncements.notify(storeName, "Discount",
                String.format("The store now has a %d discount. Come and buy more!", discountPercentage));
    }
}
