package modelo;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public final class EnviarMails extends Mail {

    String[] receptores;
    File file;
    String texto, asunto, host, port;
    private final MimeMessage message;
    boolean archivo;
    Session session;

    public EnviarMails() throws MessagingException, GeneralSecurityException {
        setHost();
        setPort();
        propiedades();
        session = Session.getInstance(prop);
        message = new MimeMessage(session);
    }

    public String enviarMsg(String t, String f, String a, String[] r) {
        texto = t;
        archivo = !f.isEmpty();
        if (archivo) {
            file = new File(f);
        }
        asunto = a;
        receptores = r;
        try {
            contenido();
            enviar();
        } catch (MessagingException | IOException ex) {
            return ex.getMessage();
        }
        limpiar();
        return "";
    }

    private void contenido() throws MessagingException, IOException {
        message.setFrom(new InternetAddress(emisor));
        for (String receptor1 : receptores) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor1));
        }
        message.setSentDate(new Date());
        message.setSubject(asunto);
        MimeBodyPart mbp1 = new MimeBodyPart();
        Multipart mp = new MimeMultipart();
        mbp1.setText(texto);
        mp.addBodyPart(mbp1);
        MimeBodyPart mbp2 = new MimeBodyPart();
        if (archivo) {
            mbp2.attachFile(file);
            mp.addBodyPart(mbp2);
        }
        message.setContent(mp);
    }

    private void enviar() throws MessagingException {
        Transport tr = session.getTransport("smtp");
        tr.connect(emisor, password);
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }

    private void limpiar() {
        texto = "";
        file = null;
        asunto = "";
        receptores = null;
    }

    @Override
    public void propiedades() {
        prop.setProperty("mail.smtp.host", host); // Nombre del host de correo, es smtp.gmail.com
        prop.setProperty("mail.smtp.starttls.enable", "true");  // TLS si está disponible
        prop.setProperty("mail.smtp.port", port);  // Puerto de gmail para envio de correos
        prop.setProperty("mail.smtp.auth", "true"); // Si requiere o no usuario y password para conectarse.
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException ex) {
            System.out.println("No se ha podido inicar el envio de mails correctamente");
        }
        prop.put("mail.smtp.ssl.socketFactory", sf);
    }

    @Override
    public void setHost() {
        host = "smtp.gmail.com";
    }

    @Override
    public void setPort() {
        port = "587"; //465
    }

}
