package org.fruitstore.normal;

public class SmsSubscriber {
    private final String phoneNumber;

    public SmsSubscriber(String phoneNumber) {
        this.phoneNumber= phoneNumber;
    }
    public void getAnnouncement(String storeName, String announcementType, String announcement) {
        String smsMessage = String.format(
                "[%s]: %s!\nHi %s!\n%s", storeName, announcementType, phoneNumber, announcement
        );
        System.out.println("==============================");
        System.out.println(smsMessage);
        System.out.println("==============================\n");
    }
}
