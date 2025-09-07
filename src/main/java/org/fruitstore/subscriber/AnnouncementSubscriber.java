package org.fruitstore.subscriber;

public interface AnnouncementSubscriber {
    void getAnnouncement(String storeName, String announcementType, String announcement);
}
