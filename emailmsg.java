package smtpclient;

public class emailmsg {
    private String from, to, subject, body;

    public emailmsg(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
}
