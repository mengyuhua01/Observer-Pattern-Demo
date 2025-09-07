package org.fruitstore.subscriber;

public class WeChatSubscriber implements AnnouncementSubscriber{
    private String username;

    public WeChatSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void getAnnouncement(String storeName, String announcementType, String announcement) {
        String wechatMessage = String.format(
                "[%s]: %s!\nHi %s!\n%s", storeName, announcementType, username, announcement
        );
        System.out.println("==============================");
        System.out.println(wechatMessage);
        System.out.println("==============================\n");
    }
}
