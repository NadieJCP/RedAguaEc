/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public final class LeerMails extends Mail {

    String host, port;
    Store store;

    public LeerMails() throws GeneralSecurityException, NoSuchProviderException, MessagingException {
        System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jre1.8.0_111\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        setHost();
        setPort();
        propiedades();
    }

    public Message[] leer(String carpeta) {
        Message[] mensajes = null;
        Folder folder;
        try {
            folder = store.getFolder(carpeta);
            folder.open(Folder.READ_ONLY);
            mensajes = folder.getMessages();
        } catch (MessagingException ex) {
            Logger.getLogger(LeerMails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajes;
    }

    @Override
    public void propiedades() {
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", port);
        prop.setProperty("mail.pop3.socketFactory.port", port);
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(LeerMails.class.getName()).log(Level.SEVERE, null, ex);
        }
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.socketFactory", sf);
        Session sesion = Session.getInstance(prop);
        try {
            store = sesion.getStore("pop3");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(LeerMails.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            store.connect(host, emisor, password);
        } catch (MessagingException ex) {
            Logger.getLogger(LeerMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setHost() {
        host = "pop.gmail.com";
    }

    @Override
    public void setPort() {
        port = "995"; //465
    }
}
