package br.com.doctorplus.gerenciador.model.services;

import br.com.doctorplus.gerenciador.model.dtos.usuario.UsuarioEmailDTO;
import br.com.doctorplus.gerenciador.model.entities.Email;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final Configuration config;

    public void sendMail(UsuarioEmailDTO userEventDTO) throws MessagingException, TemplateException, IOException {
        Email email = new Email("claudio0190@hotmail.com",userEventDTO.email(),"Teste de reset de senha", "Reset de senha");
        MimeMessage message = javaMailSender.createMimeMessage();
        String htmlTemplate = createHtmlTemplate(userEventDTO);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
        mimeMessageHelper.setFrom(email.from());
        mimeMessageHelper.setTo(email.to());
        mimeMessageHelper.setSubject(email.subject());
        mimeMessageHelper.setText(htmlTemplate, true);
        javaMailSender.send(message);
    }

    private String createHtmlTemplate(UsuarioEmailDTO user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        config.getTemplate("mail.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
