package com.form.fiche.email;

//import jakarta.mail.MessagingException;


import jakarta.mail.MessagingException;

public interface EmailService {
    void send(String to , String email) throws MessagingException;
}
