package external.service.email;

public class EmailServiceBra implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email to BR " + message);
    }
}
