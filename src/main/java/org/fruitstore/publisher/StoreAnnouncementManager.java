package org.fruitstore.publisher;

import org.fruitstore.subscriber.AnnouncementSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreAnnouncementManager {
    public static final String CANNOT_FIND_CORRESPONDING_ANNOUNCEMENT_TYPE = "Cannot find corresponding announcement type";

    Map<String, List<AnnouncementSubscriber>> announcementToSubscribers = new HashMap<>();

    public StoreAnnouncementManager(List<String> announcementTypes) {
        announcementTypes.forEach(announcementType ->
                this.announcementToSubscribers.put(announcementType, new ArrayList<>())
        );
    }

    public void subscribe(String announcementType, AnnouncementSubscriber subscriber) {
        List<AnnouncementSubscriber> subscribers = announcementToSubscribers.get(announcementType);
        if(subscribers == null) {
            throw new NullPointerException(CANNOT_FIND_CORRESPONDING_ANNOUNCEMENT_TYPE);
        }
        if(subscribers.contains(subscriber)) {return;}
        subscribers.add(subscriber);
    }

    public void unsubscribe(String announcementType, AnnouncementSubscriber subscriber) {
        List<AnnouncementSubscriber> subscribers = announcementToSubscribers.get(announcementType);
        if(subscribers == null) {
            throw new NullPointerException(CANNOT_FIND_CORRESPONDING_ANNOUNCEMENT_TYPE);
        }
        if(!subscribers.contains(subscriber)) {return;}
        subscribers.remove(subscriber);
    }

    public void notify(String storeName, String announcementType, String announcement) {
        List<AnnouncementSubscriber> subscribers = announcementToSubscribers.get(announcementType);
        for(AnnouncementSubscriber subscriber: subscribers){
            subscriber.getAnnouncement(storeName, announcementType, announcement);
        }
    }
}
