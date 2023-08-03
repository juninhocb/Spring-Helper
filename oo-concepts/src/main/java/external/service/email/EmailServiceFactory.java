package external.service.email;

public class EmailServiceFactory {
    private final EmailService emailService;

    public EmailServiceFactory(String serviceType) {
        if (serviceType.equals("USA")){
            this.emailService = new EmailServiceUsa();
        }else {
            this.emailService = new EmailServiceBra();
        }
    }

    public EmailService getEmailService() {
        return emailService;
    }
}
