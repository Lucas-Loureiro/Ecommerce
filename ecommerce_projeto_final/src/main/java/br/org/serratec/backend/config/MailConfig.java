package br.org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("lucas.vasconcellos@estudante.firjan.senai.br");
		mailMessage.setTo(para);
		mailMessage.setSubject(assunto);
		mailMessage.setText("Dados do Usuario:\n" + texto + "\n\n\n\n Serratec Residência de Software - 2021");
		javaMailSender.send(mailMessage);
	}
}
