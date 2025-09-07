package org.fruitstore;

import org.fruitstore.publisher.FruitStore;
import org.fruitstore.subscriber.EmailSubscriber;
import org.fruitstore.subscriber.WeChatSubscriber;

public class Demo {
    public static void main(String[] args) {
        FruitStore store = new FruitStore("Sunshine Fruit Store");
        EmailSubscriber jonnyEmail = new EmailSubscriber("johnny@hotmail.com");
        store.storeAnnouncements.subscribe("New Arrival", jonnyEmail);
        WeChatSubscriber jackyMaWeChat = new WeChatSubscriber("Jacky Ma");
        store.storeAnnouncements.subscribe("Discount", jackyMaWeChat);
        WeChatSubscriber amyWangWeChat = new WeChatSubscriber("Amy Wang");
        store.storeAnnouncements.subscribe("Discount", amyWangWeChat);

        store.newArrival("Watermelon");
        store.discount(30);

        store.storeAnnouncements.unsubscribe("Discount", jackyMaWeChat);
        store.newArrival("Grape");
        store.discount(20);
    }
}
