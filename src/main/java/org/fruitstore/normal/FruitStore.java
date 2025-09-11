package org.fruitstore.normal;

import org.fruitstore.subscriber.EmailSubscriber;
import org.fruitstore.subscriber.WeChatSubscriber;

import java.time.LocalDate;


public class FruitStore {

    private final EmailSubscriber emailSubscriber;
    private final WeChatSubscriber weChatSubscriber;


    public static final String NEW_ARRIVAL = "New Arrival";
    public static final String DISCOUNT = "Discount";
    private  final String storeName;

    public FruitStore(String storeName) {
        this.storeName = storeName;
        emailSubscriber = new EmailSubscriber("12362@qq.com");
        weChatSubscriber = new WeChatSubscriber("Tom");
    }
    public void newArrival(String fruitType) {
        //...业务逻辑
        String arrivedMessage = String.format("%s has arrived the store at %s!", fruitType, LocalDate.now());
        emailSubscriber.getAnnouncement(storeName,NEW_ARRIVAL,arrivedMessage);
        weChatSubscriber.getAnnouncement(storeName,NEW_ARRIVAL,arrivedMessage);

    }

    public void discount(Integer discountPercentage) {
        //...业务逻辑
        String discountMessage= String.format("The store now has a %d discount. Come and buy more!", discountPercentage);
        emailSubscriber.getAnnouncement(storeName,DISCOUNT,discountMessage);
        weChatSubscriber.getAnnouncement(storeName,DISCOUNT,discountMessage);
    }
}
