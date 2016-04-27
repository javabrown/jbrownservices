package com.jbrown.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
  public static void sendEmail() {
    String msgBody = "Test Message";

    try {
      Properties props = new Properties();
      Session session = Session.getDefaultInstance(props, null);

      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("info@javabrown.com", "javabrown.com"));

      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
          "getrk@yahoo.com", "Mr. Raja Khan"));

      msg.setSubject("Your account has been activated");
      msg.setText(msgBody);
      Transport.send(msg);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
