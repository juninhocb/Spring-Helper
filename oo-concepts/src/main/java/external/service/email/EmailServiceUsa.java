package external.service.email;

public class EmailServiceUsa implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email to USA " + message);
    }
}
