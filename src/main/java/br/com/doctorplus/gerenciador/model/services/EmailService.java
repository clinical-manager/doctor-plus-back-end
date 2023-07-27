package br.com.doctorplus.gerenciador.model.services;

import br.com.doctorplus.gerenciador.model.dtos.email.UsuarioEmailDTO;
import br.com.doctorplus.gerenciador.model.entities.Email;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.enums.EmailTypeEnum;
import br.com.doctorplus.gerenciador.model.exception.GeneralException;
import br.com.doctorplus.gerenciador.model.mapper.UsuarioMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final Configuration config;
    private final UsuarioMapper mapper;
    @Value("${email.host}")
    private String host;

    public void enviarEmailConfirmarCadastro(Usuario usuario) {
        try {
            UsuarioEmailDTO usuarioEmailDTO = mapper.toUsuarioEmail(usuario);
            usuarioEmailDTO.setHost(montarHostCodigoVerificacao(usuarioEmailDTO));
            Email email = new Email("doctorplusmanagement@gmail.com", usuarioEmailDTO.getEmail(), "Confirmar cadastro no Doctor Plus");
            enviarEmailFluxoUsuario(usuarioEmailDTO, email, EmailTypeEnum.CONFIRMAR_CADASTRO);
        } catch (MessagingException | TemplateException | IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    public void enviarEmailEsqueciSenha(Usuario usuario, String jwt) {
        try {
            UsuarioEmailDTO usuarioEmailDTO = mapper.toUsuarioEmail(usuario);
            usuarioEmailDTO.setJwt(jwt);
            usuarioEmailDTO.setHost(montarHostEsqueciSenha(usuarioEmailDTO));
            Email email = new Email("doctorplusmanagement@gmail.com", usuarioEmailDTO.getEmail(), "Esqueci senha no Doctor Plus");
            enviarEmailFluxoUsuario(usuarioEmailDTO, email, EmailTypeEnum.ESQUECI_SENHA);
        } catch (MessagingException | TemplateException | IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }


    public void enviarTrocaSenhaSucesso(Usuario usuario) {
        try {
            UsuarioEmailDTO usuarioEmailDTO = new UsuarioEmailDTO();
            usuarioEmailDTO.setEmail(usuario.getEmail());
            usuarioEmailDTO.setNome(usuario.getNome());
            Email email = new Email("doctorplusmanagement@gmail.com", usuarioEmailDTO.getEmail(), "Esqueci senha no Doctor Plus");
            enviarEmailFluxoUsuario(usuarioEmailDTO, email, EmailTypeEnum.MUDAR_SENHA);
        } catch (MessagingException | TemplateException | IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    private String montarHostCodigoVerificacao(UsuarioEmailDTO usuario) {
        return this.host + "verificar?" + "email=" + usuario.getEmail();
    }

    private String montarHostEsqueciSenha(UsuarioEmailDTO usuario) {
        return this.host + "jwt?" + usuario.getJwt();
    }

    private void enviarEmailFluxoUsuario(UsuarioEmailDTO userEventDTO, Email email, EmailTypeEnum emailType) throws MessagingException, TemplateException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setFrom(email.de());
        mimeMessageHelper.setTo(email.para());
        mimeMessageHelper.setSubject(email.subject());
        adicionarImagem(mimeMessageHelper);
        String template = createHtmlTemplateConfirmarCadastro(userEventDTO, emailType);
        mimeMessageHelper.setText(template, true);
        javaMailSender.send(message);
    }

    private String createHtmlTemplateConfirmarCadastro(UsuarioEmailDTO usuario, EmailTypeEnum emailType) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        Template template = config.getTemplate(emailType.getName());
        template.process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    private void adicionarImagem(MimeMessageHelper messageHelper) throws MessagingException {
        messageHelper.addInline("logo-icone", new ClassPathResource("images/logo.png"));
    }
}
