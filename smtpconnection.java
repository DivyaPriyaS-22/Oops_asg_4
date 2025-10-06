package smtpclient;

import java.io.*;
import java.net.*;

public class smtpconnection {
    private String server;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public smtpconnection(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void sendEmail(emailmsg email) {
        try {
            socket = new Socket(server, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            smtplogger.log(reader.readLine()); // Server greeting

            sendCommand("HELO localhost");
            sendCommand("MAIL FROM:<" + email.getFrom() + ">");
            sendCommand("RCPT TO:<" + email.getTo() + ">");
            sendCommand("DATA");
            writer.write("Subject: " + email.getSubject() + "\r\n");
            writer.write(email.getBody() + "\r\n.\r\n");
            writer.flush();
            smtplogger.log(reader.readLine());

            sendCommand("QUIT");

        } catch (IOException e) {
            smtplogger.log("Error: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                smtplogger.log("Cleanup Error: " + e.getMessage());
            }
        }
    }

    private void sendCommand(String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
        smtplogger.log(reader.readLine());
    }
}
