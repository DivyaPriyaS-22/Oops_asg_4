public class Main {
    public static void main(String[] args) {
        emailmsg email = new emailmsg("sender@example.com", "receiver@example.com", "Test Subject", "Hello from SMTP Client!");
        smtpconnection smtp = new smtpconnection("localhost", 25); // Replace with test server
        smtp.sendEmail(email);
    }
}
