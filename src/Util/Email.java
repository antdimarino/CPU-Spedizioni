package Util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    private static String name = "*********"; //INSERT YOUR EMAIL
    private static String password = "******"; //INSERT YOUR PASSWORD

    public static void sendEmail(String[] destinatari, String code){
        String host = "smtp.gmail.com";

        Properties p = new Properties();
        p.put("mail.smtp.user",name);
        p.put("mail.smtp.host",host);
        p.put("mail.smtp.port","465");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");

        class SMTPAuthenticator extends javax.mail.Authenticator
        {
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(name, password);
            }
        }

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(p, auth);
            MimeMessage mess = new MimeMessage(session);
            mess.setFrom( new InternetAddress(name));

            InternetAddress[] toAddress = new InternetAddress[destinatari.length];

            for( int i = 0; i < destinatari.length; i++ ){
                toAddress[i] = new InternetAddress(destinatari[i]);
            }

            for (InternetAddress address : toAddress) {
                mess.addRecipient(Message.RecipientType.TO, address);
            }

            mess.setSubject("Conferma spedizione");
            String text = "Il codice per il Tracking della tua spedizione = " + code;
            mess.setContent(text, "text/html; charset=utf-8");
            Transport.send(mess);
        }
        catch (AddressException e){
            e.printStackTrace();
        }
        catch (MessagingException ae){
            ae.printStackTrace();
        }
    }
}
