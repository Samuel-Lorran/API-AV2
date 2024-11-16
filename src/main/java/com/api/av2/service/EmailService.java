package com.api.av2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailDeBoasVindas(String email, String nome) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Bem-vindo ao sistema!");
        message.setText("Olá " + nome + ",\n\nBem-vindo ao nosso sistema! Esperamos que você tenha uma ótima experiência.\n\nAtenciosamente,\nEquipe do Sistema");
        mailSender.send(message);
    }
}

