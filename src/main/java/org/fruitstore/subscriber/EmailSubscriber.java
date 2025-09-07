package org.fruitstore.subscriber;

public class EmailSubscriber implements AnnouncementSubscriber {
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void getAnnouncement(String storeName, String announcementType, String announcement) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append(String.format("To: %s", email)).append('\n')
            .append(String.format("From: %s", storeName)).append('\n')
            .append(String.format("Subject: %s", announcement)).append('\n')
            .append(String.format(
                "Dear customer:\n\n%s\n\nBest regards,\n%s",  announcement, storeName));
        System.out.println("==============================");
        System.out.println(emailMessage);
        System.out.println("==============================\n");
    }
}
